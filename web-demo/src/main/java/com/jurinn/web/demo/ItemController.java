package com.jurinn.web.demo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemService itemService;
    @Autowired
    MenuService menuService;

    @ModelAttribute
    ItemForm setUpForm() {
        return new ItemForm();
    }

    @GetMapping
    String getItems(Model model) {
        List<MenuItem> menuItems = menuService.getMenuItems();
        model.addAttribute("menuItems", menuItems);
        model.addAttribute("items", itemService.getItems());
        return "item/list";
    }
    
    @GetMapping("/add")
    String getItemAddPage(Model model) {
        List<MenuItem> menuItems = menuService.getMenuItems();
        model.addAttribute("menuItems", menuItems);
        return "item/add";
    }
 
    @PostMapping("/add")
    String addItem(@Validated ItemForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return getItemAddPage(model);
        }
        // TODO: BeanUtilsまたは、Dozer、ModelMapper検討。はじめてのSpring Boot p100 参照。
        LocalDateTime now = LocalDateTime.now(ZoneId.of("GMT"));
        Item item = new Item(form.getItemId(),form.getName(),form.getDescription(),now);
        Price price = new Price(form.getActivateFrom(),form.getActivateTo(),form.getPrice(),now);
        
        itemService.add(item, price);
        return "redirect:/items/add";
    }
}
