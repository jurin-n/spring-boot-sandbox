package com.jurinn.web.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Price implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private LocalDateTime activateFrom;
    private LocalDateTime activateTo;
    private Double amount;
    private LocalDateTime dateTime;

    public Price(LocalDateTime activateFrom, LocalDateTime activateTo, Double amount, LocalDateTime dateTime) {
        super();
        this.activateFrom = activateFrom;
        this.activateTo = activateTo;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public LocalDateTime getActivateFrom() {
        return activateFrom;
    }

    public LocalDateTime getActivateTo() {
        return activateTo;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
