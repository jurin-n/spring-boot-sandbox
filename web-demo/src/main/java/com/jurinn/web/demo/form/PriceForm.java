package com.jurinn.web.demo.form;

import javax.validation.constraints.Digits;

import com.jurinn.web.demo.form.validation.LocalDateTimeFormat;

public class PriceForm {
    @LocalDateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private String activateFrom;
    @LocalDateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private String activateTo;
    @Digits(integer = 10, fraction = 10)
    private String amount;

    public String getActivateFrom() {
        return activateFrom;
    }

    public String getActivateTo() {
        return activateTo;
    }

    public String getAmount() {
        return amount;
    }

    public void setActivateFrom(String activateFrom) {
        this.activateFrom = activateFrom;
    }

    public void setActivateTo(String activateTo) {
        this.activateTo = activateTo;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
