package com.example.shop;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.shop.exception.IdTokenNotFoundExeption;

@RestControllerAdvice
public class IdTokenNotFoundExeptionAdvice {
    @Autowired
    Oauth2AzureProperties oauth2AzureProperties;

    @ExceptionHandler(IdTokenNotFoundExeption.class)
    @ResponseStatus(HttpStatus.FOUND)
    public void handleException(IdTokenNotFoundExeption e, HttpServletResponse response)
            throws UnsupportedEncodingException {
        final String state = UUID.randomUUID().toString();
        final String nonce = UUID.randomUUID().toString();

        UriComponents uriComponents = UriComponentsBuilder.fromUri(oauth2AzureProperties.getAuthorizeEndpoint())
                .queryParam("client_id", oauth2AzureProperties.getClientId()).queryParam("response_type", "id_token")
                .queryParam("redirect_uri", URLEncoder.encode(e.getRedirectUrl(), "UTF-8"))
                .queryParam("response_mode", "form_post").queryParam("state", state).queryParam("nonce", nonce).build();
        response.setHeader("Location", uriComponents.toUriString());
    }
}
