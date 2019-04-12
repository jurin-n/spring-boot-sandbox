package com.jurinn.web.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jurinn.web.demo.model.MenuItem;
import com.jurinn.web.demo.service.MenuService;

@Controller
@RequestMapping("/")
public class TopPageController {
    @Autowired
    MenuService menuService;

    @GetMapping
    String top(Model model) {
        List<MenuItem> menuItems = menuService.getMenuItems();
        model.addAttribute("menuItems", menuItems);
        return "top/index";
    }
}
