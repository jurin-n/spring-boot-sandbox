package com.jurinn.web.demo.form;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class PriceForm {
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime activateFrom;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime activateTo;
    private Double price;

    public LocalDateTime getActivateFrom() {
        return activateFrom;
    }

    public LocalDateTime getActivateTo() {
        return activateTo;
    }

    public Double getPrice() {
        return price;
    }

    public void setActivateFrom(LocalDateTime activateFrom) {
        this.activateFrom = activateFrom;
    }

    public void setActivateTo(LocalDateTime activateTo) {
        this.activateTo = activateTo;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
