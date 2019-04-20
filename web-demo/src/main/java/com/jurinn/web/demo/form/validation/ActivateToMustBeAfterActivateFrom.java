package com.jurinn.web.demo.form.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { ActivateToMustBeAfterActivateFromValidator.class })
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivateToMustBeAfterActivateFrom {
    String message() default "{com.jurinn.web.demo.form.validation.ActivateToMustBeAfterActivateFrom}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
