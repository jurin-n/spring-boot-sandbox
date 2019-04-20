package com.jurinn.web.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static List<Map<String, Integer>> checkApplicablePeriodOfPrices(List<Price> prices) {

        List<Map<String, Integer>> errors = new ArrayList<>();
        int size = prices.size();
        if (size < 1) {
            return errors;
        }

        for (int i = 1; i < size; i++) {
            int toIndex = i - 1;
            int fromIndex = i;
            LocalDateTime to = prices.get(toIndex).getActivateTo();
            LocalDateTime from = prices.get(fromIndex).getActivateFrom();
            if (!from.isAfter(to)) {
                Map<String, Integer> map = new HashMap<>();
                map.put("to", toIndex);
                map.put("from", fromIndex);
                errors.add(map);
            }
        }

        return errors;
    }

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
