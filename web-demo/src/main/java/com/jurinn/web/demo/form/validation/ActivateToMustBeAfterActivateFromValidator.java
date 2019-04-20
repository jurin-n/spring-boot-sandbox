package com.jurinn.web.demo.form.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jurinn.web.demo.form.PriceForm;

public class ActivateToMustBeAfterActivateFromValidator
        implements ConstraintValidator<ActivateToMustBeAfterActivateFrom, PriceForm> {
    private String message;

    @Override
    public void initialize(ActivateToMustBeAfterActivateFrom constraintAnnotation) {
        message = constraintAnnotation.message();
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
    public boolean isValid(PriceForm value, ConstraintValidatorContext context) {
        String activateFrom = value.getActivateFrom();
        String activateTo = value.getActivateTo();

        if (isBlankOrNull(activateFrom) || isBlankOrNull(activateTo)) {
            // activateFromとactivateToどちらか一方未入力の場合、バリデートしない。
            return true;
        }

        LocalDateTime from = LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").parse(activateFrom));
        LocalDateTime to = LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").parse(activateTo));

        if (to.isAfter(from)) {
            // toがfromの後なので正常
            return true;
        }

        // バリデーションエラー
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addPropertyNode("activateTo").addConstraintViolation();
        return false;
    }
}
