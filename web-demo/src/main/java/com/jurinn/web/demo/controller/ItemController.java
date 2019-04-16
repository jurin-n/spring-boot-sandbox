package com.jurinn.web.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.jurinn.web.demo.service.ItemService;
import com.jurinn.web.demo.service.MenuService;
import com.jurinn.web.demo.service.PriceService;

@Controller
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemService itemService;
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
        //商品情報
        form.setItemId(itemId.get());
        form.setName(item.getName());
        form.setDescription(item.getDescription());

        //価格情報
        Price price = prices.get(0);
        PriceForm priceForm = new PriceForm();
        priceForm.setAmount(String.valueOf(price.getAmount()));
        priceForm.setActivateFrom(
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(price.getActivateFrom()));
        priceForm.setActivateTo(
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(price.getActivateTo()));
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
        Price price = new Price(
                LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").parse(priceForm.getActivateFrom())),
                LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").parse(priceForm.getActivateTo())),
                Double.valueOf(priceForm.getAmount()),
                now);

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
    String getItemAddPage(Model model, ItemForm form) {
        List<MenuItem> menuItems = menuService.getMenuItems();
        model.addAttribute("menuItems", menuItems);

        form.setPrices(Arrays.asList(new PriceForm(), new PriceForm(), new PriceForm()));
        return "item/add";
    }

    @PostMapping(path = "add")
    String addItem(@Validated ItemForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return getItemAddPage(model, form);
        }

        LocalDateTime now = dateAndTimeService.now();
        // TODO: BeanUtilsまたは、Dozer、ModelMapper検討。はじめてのSpring Boot p100 参照。
        Item item = new Item(form.getItemId(), form.getName(), form.getDescription(), now);

        List<Price> prices = new ArrayList<>();
        for (PriceForm priceForm : form.getPrices()) {
            if (!priceForm.getActivateFrom().isBlank() && !priceForm.getActivateTo().isBlank()) {
                Price price = new Price(
                        LocalDateTime.from(
                                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").parse(priceForm.getActivateFrom())),
                        LocalDateTime.from(
                                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").parse(priceForm.getActivateTo())),
                        Double.valueOf(priceForm.getAmount()), now);
                prices.add(price);
            }
        }

        itemService.add(item, prices);
        return "redirect:/items/add";
    }
}
