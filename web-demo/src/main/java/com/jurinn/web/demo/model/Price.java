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
    private Double price;
    private LocalDateTime dateTime;

    public Price(LocalDateTime activateFrom, LocalDateTime activateTo, Double price, LocalDateTime dateTime) {
        super();
        this.activateFrom = activateFrom;
        this.activateTo = activateTo;
        this.price = price;
        this.dateTime = dateTime;
    }

    public Price() {
        // TODO Auto-generated constructor stub
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
