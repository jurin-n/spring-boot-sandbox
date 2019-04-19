package com.jurinn.web.demo.form.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jurinn.web.demo.form.PriceForm;

public class PriceFormMustInputAllFieldsValidator
        implements ConstraintValidator<PriceFormMustInputAllFields, PriceForm> {
    private String message;

    @Override
    public void initialize(PriceFormMustInputAllFields form) {
        message = form.message();
    }

    private boolean isBlankOrNull(String value) {
        if (value == null) {
            return true;
        }
        if (value.isBlank()) {
            return true;
        }
        if (value.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isValid(PriceForm priceForm, ConstraintValidatorContext context) {
        if (isBlankOrNull(priceForm.getActivateFrom()) && isBlankOrNull(priceForm.getActivateTo())
                && isBlankOrNull(priceForm.getAmount())) {
            // 全て未入力の場合、エラーなし
            return true;
        }

        if (priceForm.getVersion().length() > 0 && priceForm.getActivateFrom().length() > 0
                && priceForm.getActivateTo().length() > 0 && priceForm.getAmount().length() > 0) {
            // 全て入力の場合、エラーなし
            return true;
        }
        // 全て未入力 または、全て入力じゃない場合、エラー。
        context.disableDefaultConstraintViolation();
        if (isBlankOrNull(priceForm.getActivateFrom())) {
            context.buildConstraintViolationWithTemplate(message).addPropertyNode("activateFrom")
                    .addConstraintViolation();
        }
        if (isBlankOrNull(priceForm.getActivateTo())) {
            context.buildConstraintViolationWithTemplate(message).addPropertyNode("activateTo")
                    .addConstraintViolation();
        }
        if (isBlankOrNull(priceForm.getAmount())) {
            context.buildConstraintViolationWithTemplate(message).addPropertyNode("amount").addConstraintViolation();
        }
        return false;
    }
}
