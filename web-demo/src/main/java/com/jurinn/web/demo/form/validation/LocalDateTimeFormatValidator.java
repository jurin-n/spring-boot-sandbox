package com.jurinn.web.demo.form.validation;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LocalDateTimeFormatValidator implements ConstraintValidator<LocalDateTimeFormat, String> {

    private String pattern;

    public void initialize(LocalDateTimeFormat constraintAnnotaion) {
        this.pattern = constraintAnnotaion.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }
        try {
            DateTimeFormatter.ofPattern(pattern).parse(value);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

}
