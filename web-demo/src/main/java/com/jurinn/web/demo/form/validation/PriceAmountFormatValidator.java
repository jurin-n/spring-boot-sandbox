package com.jurinn.web.demo.form.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceAmountFormatValidator implements ConstraintValidator<PriceAmountFormat, String> {
    private Integer integer;
    private Integer fraction;

    public void initialize(PriceAmountFormat constraintAnnotaion) {
        this.integer = constraintAnnotaion.integer();
        this.fraction = constraintAnnotaion.fraction();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }
        try {
            Double.valueOf(value);
        } catch (NumberFormatException e) {
            return false;
        }

        BigDecimal bd = new BigDecimal(value);
        int precision = bd.precision(); // 精度。 例)10.123の場合、5。
        int scale = bd.scale(); // スケール。 例)10.123の場合、3。
        if ((precision - scale > integer) || (scale > fraction)) {
            return false;
        }
        return true;
    }
}
