package com.example.shop.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.exception.IdTokenNotFoundExeption;
import com.example.shop.model.Book;
import com.example.shop.model.Token;
import com.example.shop.service.Oauth2Service;

@RestController
@RequestMapping("/t/shop")
@CrossOrigin(origins = "*")
public class ShopUsingTokenController {

    private final Token token;
    private final Oauth2Service oauth2Service;

    public ShopUsingTokenController(Token token, Oauth2Service oauth2Service) {
        this.token = token;
        this.oauth2Service = oauth2Service;
    }

    @GetMapping("/login")
    public String getLogin(HttpServletRequest request) {
        if (token.getIdToken() == null) {
            // リダイレクト
            String redirectUrl = request.getRequestURL().toString();
            throw new IdTokenNotFoundExeption(redirectUrl);
        }
        return "login complete.";
    }

    @PostMapping("/login")
    public void postLogin(HttpServletResponse response,
            @RequestParam(value = "id_token", defaultValue = "") String idToken) {
        // JWT検証
        String verifiedIdToken = oauth2Service.verifyJwt(idToken);

        // JWTをセッションに格納
        token.setIdToken(verifiedIdToken);
        token.setCreatedAt(new Date());
    }

    @GetMapping
    public Book getBook(HttpServletRequest request) {
        if (token.getIdToken() == null) {
            // リダイレクト
            String redirectUrl = request.getRequestURL().toString();
            throw new IdTokenNotFoundExeption(redirectUrl);
        }
        return new Book("book001");
    }
}
