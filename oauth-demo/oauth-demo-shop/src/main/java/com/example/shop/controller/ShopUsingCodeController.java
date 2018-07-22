package com.example.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.exception.CodeNotFoundExeption;
import com.example.shop.model.Book;

@RestController
@RequestMapping("/c/shop")
@CrossOrigin(origins = "*")
public class ShopUsingCodeController {
    @GetMapping
    public Book getBook(HttpServletRequest request, @RequestParam(value = "code", defaultValue = "") String code) {
        if (code.equals("")) {
            // リダイレクト
            String redirectUrl = request.getRequestURL().toString();
            throw new CodeNotFoundExeption(redirectUrl);
        }
        return new Book("book001");
    }
}
