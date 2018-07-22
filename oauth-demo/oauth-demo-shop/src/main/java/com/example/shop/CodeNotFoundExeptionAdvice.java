package com.example.shop;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.shop.exception.CodeNotFoundExeption;

@RestControllerAdvice
public class CodeNotFoundExeptionAdvice {
	@ExceptionHandler(CodeNotFoundExeption.class)
	@ResponseStatus(HttpStatus.FOUND)
	public void handleException(CodeNotFoundExeption e, HttpServletResponse response)
			throws UnsupportedEncodingException {
		System.out.println(e);
		String redirectUrl = "https://login.microsoftonline.com/e2b7f1e1-c064-4787-be57-ad512132ee23/oauth2/authorize?client_id=059f036d-4676-4c75-9932-62e0bfd88cdc&response_type=code&redirect_uri="
				+ URLEncoder.encode(e.getRedirectUrl(), "UTF-8") + "&response_mode=query&state=12345";
		response.setHeader("Location", redirectUrl);
	}
}
