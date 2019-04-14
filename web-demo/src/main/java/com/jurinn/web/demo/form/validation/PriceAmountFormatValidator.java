package com.jurinn.web.demo.form.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceAmountFormatValidator implements ConstraintValidator<PriceAmountFormat, String> {
    private Integer integer;
    private Integer fraction;
    
    public void initialize(PriceAmountFormat constraintAnnotaion) {
        this.integer = constraintAnnotaion.integer();
        this.integer = constraintAnnotaion.fraction();
    }
 
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }
        try {
            Double d = Double.valueOf(value);
            // TODO:整数桁、小数桁のチェックを追加
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

}
