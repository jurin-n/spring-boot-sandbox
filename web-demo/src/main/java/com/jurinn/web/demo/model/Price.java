package com.jurinn.web.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Price implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer version;
    private LocalDateTime activateFrom;
    private LocalDateTime activateTo;
    private Double amount;
    private LocalDateTime dateTime;

    public Price(Integer version, LocalDateTime activateFrom, LocalDateTime activateTo, Double amount,
            LocalDateTime dateTime) {
        super();
        this.version = version;
        this.activateFrom = activateFrom;
        this.activateTo = activateTo;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public Integer getVersion() {
        return version;
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

    public void setVersion(Integer version) {
        this.version = version;
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
