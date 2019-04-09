package com.jurinn.web.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    
    @GetMapping("/addInfo")
    String getAddInfo(Model model) {
        List<MenuItem> menuItems = menuService.getMenuItems();
        model.addAttribute("menuItems", menuItems);

        return "addinfo/index";
    }

    /*
    @PostMapping("/addInfo")
    String addInfo(@Validated InformationForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return getAddInfo(model);
        }
        Item information = new Item();
        // TODO: BeanUtilsまたは、Dozer、ModelMapper検討。はじめてのSpring Boot p100 参照。
        information.setId(Integer.valueOf(form.getId()));
        information.setTitle(form.getTitle());

        informationService.add(information);
        return "redirect:/addInfo";
    }
    */
}
