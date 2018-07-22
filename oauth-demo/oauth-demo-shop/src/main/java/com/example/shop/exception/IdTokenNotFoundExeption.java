package com.example.shop.exception;

public class IdTokenNotFoundExeption extends RuntimeException {	
	private static final long serialVersionUID = 1L;
	private String redirectUrl;

	public IdTokenNotFoundExeption(String redirectUrl) {
		super();
		this.redirectUrl = redirectUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}
}
