package com.jurinn.web.demo.form;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.Matchers.*;

public class CustomValidatorTest {
    private ValidatorFactory vf;
    private Validator validator;

    @Before
    public void setUp() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @Test
    public void testLocalDateTimeFormatアノテーションでバリデートできる() {
        PriceForm priceForm = new PriceForm();
        priceForm.setActivateFrom("2020/04/01 00:00:0");

        Set<ConstraintViolation<PriceForm>> vRet = validator.validate(priceForm);
        Iterator<ConstraintViolation<PriceForm>> i = vRet.iterator();
        ConstraintViolation<PriceForm> v1 = i.next();

        assertThat(v1.getMessage(), is("format must be yyyy/MM/dd HH:mm:ss"));
        assertThat(v1.getInvalidValue().toString(), is("2020/04/01 00:00:0"));

        //デバッグ用
        //vRet.stream().forEach(r -> {
        //    System.out.println(r.getMessage() + "[" + r.getInvalidValue().toString() + "]");
        //});
    }

    @Test
    public void testDigitsアノテーションでバリデートできる_非数値項目() {
        PriceForm priceForm = new PriceForm();
        priceForm.setAmount("1abc");

        Set<ConstraintViolation<PriceForm>> vRet = validator.validate(priceForm);
        Iterator<ConstraintViolation<PriceForm>> i = vRet.iterator();
        ConstraintViolation<PriceForm> v1 = i.next();

        assertThat(v1.getMessage(), is(containsString("numeric value out of bounds")));
        assertThat(v1.getInvalidValue().toString(), is("1abc"));
    }


    @Test
    public void testDigitsアノテーションでバリデートできる_数値項目() {
        PriceForm priceForm = new PriceForm();
        priceForm.setAmount("1234567890.1234567891");

        Set<ConstraintViolation<PriceForm>> vRet = validator.validate(priceForm);

        assertTrue(vRet.isEmpty());
    }
}
