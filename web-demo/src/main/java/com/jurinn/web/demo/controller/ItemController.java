package com.jurinn.web.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jurinn.web.demo.form.ItemForm;
import com.jurinn.web.demo.form.PriceForm;
import com.jurinn.web.demo.model.Item;
import com.jurinn.web.demo.model.MenuItem;
import com.jurinn.web.demo.model.Price;
import com.jurinn.web.demo.service.DateAndTimeService;
import com.jurinn.web.demo.repository.ItemRepository;
import com.jurinn.web.demo.service.MenuService;
import com.jurinn.web.demo.service.PriceService;

@Controller
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemRepository itemService;
    @Autowired
    PriceService priceService;
    @Autowired
    MenuService menuService;
    @Autowired
    DateAndTimeService dateAndTimeService;

    @ModelAttribute
    ItemForm setUpForm() {
        return new ItemForm();
    }

    @GetMapping(path = "edit")
    String editForm(@RequestParam(name = "id") Optional<String> itemId, ItemForm form, Model model) {
        if (itemId.isEmpty()) {
            // TODO:どうするか考え中。
            throw new RuntimeException("フォーム表示エラー");
        }

        List<MenuItem> menuItems = menuService.getMenuItems();
        model.addAttribute("menuItems", menuItems);

        Item item = itemService.findOne(itemId.get());
        List<Price> prices = priceService.find(itemId.get());

        /* form設定 */
        // 商品情報
        form.setItemId(itemId.get());
        form.setName(item.getName());
        form.setDescription(item.getDescription());

        // 価格情報
        Price price = prices.get(0);
        PriceForm priceForm = new PriceForm();
        priceForm.setAmount(String.valueOf(price.getAmount()));
        priceForm.setActivateFrom(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(price.getActivateFrom()));
        priceForm.setActivateTo(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(price.getActivateTo()));
        form.setPrices(Arrays.asList(priceForm));

        return "item/edit";
    }

    @PostMapping(path = "edit")
    String edit(@RequestParam(name = "id") Optional<String> itemId, @Validated ItemForm form, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return editForm(itemId, form, model);
        }
        LocalDateTime now = dateAndTimeService.now();
        // TODO: BeanUtilsまたは、Dozer、ModelMapper検討。はじめてのSpring Boot p100 参照。
        Item item = new Item(form.getItemId(), form.getName(), form.getDescription(), now);
        PriceForm priceForm = form.getPrices().get(0);
        Price price = new Price(Integer.valueOf(priceForm.getVersion()),
                LocalDateTime
                        .from(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").parse(priceForm.getActivateFrom())),
                LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").parse(priceForm.getActivateTo())),
                Double.valueOf(priceForm.getAmount()), now);

        // TODO:ItemとPriceが別トランザクションでupdateされてるので、これを１つのトランザクションで処理するようにする。
        itemService.update(item);
        priceService.update(item.getItemId(), price);
        return "redirect:/items";
    }

    @GetMapping
    String getItems(Model model) {
        List<MenuItem> menuItems = menuService.getMenuItems();
        model.addAttribute("menuItems", menuItems);
        model.addAttribute("items", itemService.getItems());
        return "item/list";
    }

    @GetMapping(path = "add")
    String getItemAddPage(Model model, ItemForm form, boolean error) {
        List<MenuItem> menuItems = menuService.getMenuItems();
        model.addAttribute("menuItems", menuItems);
        if (error == false) {
            form.setPrices(Arrays.asList(new PriceForm("1"), new PriceForm("2"), new PriceForm("3")));
        }
        return "item/add";
    }

    @PostMapping(path = "add")
    String addItem(@Validated ItemForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // バリデーションエラー
            return getItemAddPage(model, form, true);
        }

        LocalDateTime now = dateAndTimeService.now();
        // TODO: BeanUtilsまたは、Dozer、ModelMapper検討。はじめてのSpring Boot p100 参照。
        Item item = new Item(form.getItemId(), form.getName(), form.getDescription(), now);

        List<Price> prices = new ArrayList<>();
        for (PriceForm priceForm : form.getPrices()) {
            if (!priceForm.getActivateFrom().isBlank() && !priceForm.getActivateTo().isBlank()) {
                Price price = new Price(Integer.valueOf(priceForm.getVersion()),
                        LocalDateTime.from(
                                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").parse(priceForm.getActivateFrom())),
                        LocalDateTime.from(
                                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").parse(priceForm.getActivateTo())),
                        Double.valueOf(priceForm.getAmount()), now);
                prices.add(price);
            }
        }

        // 商品ID重複チェック
        if (itemService.findOne(item.getItemId()) != null) {
            // 商品ID重複エラー
            FieldError error = new FieldError("itemForm", "itemId", form.getItemId(), false, null, null, "登録ずみ");
            result.addError(error);
            return getItemAddPage(model, form, true);
        }

        // 価格の適用期間のチェック
        List<Map<String, Integer>> errors = Price.checkApplicablePeriodOfPrices(prices);
        if (errors.size() > 0) {
            for (Map<String, Integer> error : errors) {
                Integer to = error.get("to");
                Integer from = error.get("from");
                // TODO:エラーメッセージ考える。
                result.addError(new FieldError("itemForm", "prices[" + to + "].activateTo",
                        form.getPrices().get(to).getActivateTo(), false, null, null, "activateToエラー"));
                result.addError(new FieldError("itemForm", "prices[" + from + "].activateFrom",
                        form.getPrices().get(from).getActivateFrom(), false, null, null, "activateFromエラー"));
            }
            return getItemAddPage(model, form, true);
        }

        itemService.add(item, prices);
        return "redirect:/items/add";
    }
}
