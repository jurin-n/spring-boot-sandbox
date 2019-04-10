package com.jurinn.web.demo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Price implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String priceId;
    private LocalDateTime activateFrom;
    private LocalDateTime activateTo;
    private Double price;
    private LocalDateTime dateTime;

    public Price(String priceId, LocalDateTime activateFrom, LocalDateTime activateTo, Double price,
            LocalDateTime dateTime) {
        super();
        this.priceId = priceId;
        this.activateFrom = activateFrom;
        this.activateTo = activateTo;
        this.price = price;
        this.dateTime = dateTime;
    }

    public String getPriceId() {
        return priceId;
    }

    public LocalDateTime getActivateFrom() {
        return activateFrom;
    }

    public LocalDateTime getActivateTo() {
        return activateTo;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
