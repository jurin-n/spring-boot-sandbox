package com.jurinn.web.demo.form;

import javax.validation.constraints.Digits;

import com.jurinn.web.demo.form.validation.PriceFormMustInputAllFields;
import com.jurinn.web.demo.form.validation.LocalDateTimeFormat;
import com.jurinn.web.demo.form.validation.PriceAmountFormat;

@PriceFormMustInputAllFields
public class PriceForm {
    @Digits(integer = 4, fraction = 0)
    private String version;
    @LocalDateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private String activateFrom;
    @LocalDateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private String activateTo;
    @PriceAmountFormat(integer = 10, fraction = 10)
    private String amount;

    public PriceForm(String version) {
        this.version = version;
    }

    public PriceForm() {
    }

    public String getVersion() {
        return version;
    }

    public String getActivateFrom() {
        return activateFrom;
    }

    public String getActivateTo() {
        return activateTo;
    }

    public String getAmount() {
        return amount;
    }

    public void setVersion(String version) {
        this.version = version;
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
