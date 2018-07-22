package com.example.shop.exception;

public class CodeNotFoundExeption extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String redirectUrl;

    public CodeNotFoundExeption(String redirectUrl) {
        super();
        this.redirectUrl = redirectUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
}
