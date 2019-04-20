package com.jurinn.web.demo.form;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class PriceFormTest {
    private ValidatorFactory vf;
    private Validator validator;

    @Before
    public void setUp() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @Test
    public void test全て未入力の場合_バリデーションエラーにならない() {
        PriceForm priceForm = new PriceForm();

        Set<ConstraintViolation<PriceForm>> vRet = validator.validate(priceForm);

        assertTrue(vRet.isEmpty());
    }

    @Test
    public void test全て入力の場合_バリデーションエラーにならない() {
        PriceForm priceForm = new PriceForm();
        priceForm.setVersion("1");
        priceForm.setActivateFrom("2020/04/01 00:00:00");
        priceForm.setActivateTo("2021/03/31 23:59:59");
        priceForm.setAmount("100");

        Set<ConstraintViolation<PriceForm>> vRet = validator.validate(priceForm);

        assertTrue(vRet.isEmpty());
    }

    private String getFiledName(Set<ConstraintViolation<PriceForm>> vRet) {
        return vRet.iterator().next().getPropertyPath().iterator().next().getName();
    }

    @Test
    public void test全て入力じゃないまたは全て入力じゃない場合_バリデーションエラーになる() {
        Set<ConstraintViolation<PriceForm>> vRet;

        PriceForm priceForm01 = new PriceForm();
        priceForm01.setVersion("1");
        priceForm01.setActivateFrom("");
        priceForm01.setActivateTo("2021/03/31 23:59:59");
        priceForm01.setAmount("100");

        vRet = validator.validate(priceForm01);
        assertFalse(vRet.isEmpty());
        assertThat(vRet.size(), is(1));
        assertThat(getFiledName(vRet), is("activateFrom"));

        PriceForm priceForm02 = new PriceForm();
        priceForm02.setVersion("1");
        priceForm02.setActivateFrom("2020/04/01 00:00:00");
        priceForm02.setActivateTo("");
        priceForm02.setAmount("100");

        vRet = validator.validate(priceForm02);
        assertFalse(vRet.isEmpty());
        assertThat(vRet.size(), is(1));
        assertThat(getFiledName(vRet), is("activateTo"));

        PriceForm priceForm03 = new PriceForm();
        priceForm03.setVersion("1");
        priceForm03.setActivateFrom("2020/04/01 00:00:00");
        priceForm03.setActivateTo("2021/03/31 23:59:59");
        priceForm03.setAmount("");

        vRet = validator.validate(priceForm03);
        assertFalse(vRet.isEmpty());
        assertThat(vRet.size(), is(1));
        assertThat(getFiledName(vRet), is("amount"));
    }
    
    @Test
    public void testActivateToはActivateFromより過去日になる場合エラー() {
        Set<ConstraintViolation<PriceForm>> vRet;

        PriceForm priceForm01 = new PriceForm();
        priceForm01.setVersion("1");
        priceForm01.setActivateFrom("2020/04/01 00:00:00");
        priceForm01.setActivateTo("2020/04/01 00:00:01");
        priceForm01.setAmount("100");

        vRet = validator.validate(priceForm01);
        assertTrue(vRet.isEmpty());

        PriceForm priceForm02 = new PriceForm();
        priceForm02.setVersion("1");
        priceForm02.setActivateFrom("2020/04/01 00:00:00");
        priceForm02.setActivateTo("2020/03/31 23:59:59");
        priceForm02.setAmount("100");

        vRet = validator.validate(priceForm02);
        assertFalse(vRet.isEmpty());
        assertThat(vRet.size(), is(1));
        assertThat(getFiledName(vRet), is("activateTo"));
    }
}
