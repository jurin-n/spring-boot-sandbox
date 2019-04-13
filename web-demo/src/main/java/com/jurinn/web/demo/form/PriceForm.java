package com.jurinn.web.demo.form;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class PriceForm {
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime activateFrom;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime activateTo;
    private Double amount;

    public LocalDateTime getActivateFrom() {
        return activateFrom;
    }

    public LocalDateTime getActivateTo() {
        return activateTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setActivateFrom(LocalDateTime activateFrom) {
        this.activateFrom = activateFrom;
    }

    public void setActivateTo(LocalDateTime activateTo) {
        this.activateTo = activateTo;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
