package com.jurinn.web.demo.form;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Set;

public class ItemFormTest {
    private ValidatorFactory vf;
    private Validator validator;

    @Before
    public void setUp() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @Test
    public void test() {
        ItemForm itemForm = new ItemForm();
        itemForm.setItemId("item001");
        itemForm.setName("商品001");
        PriceForm priceForm01 = new PriceForm();
        priceForm01.setVersion("1");
        priceForm01.setActivateFrom("");
        priceForm01.setActivateTo("");
        priceForm01.setAmount("");
        itemForm.setPrices(Arrays.asList(priceForm01, new PriceForm("2"), new PriceForm("3")));

        Set<ConstraintViolation<ItemForm>> vRet = validator.validate(itemForm);
        // デバッグ用
        // vRet.stream().forEach(r -> {
        // System.out.println(r.getMessage() + "[" + r.getInvalidValue().toString() +
        // "]");
        // });
        System.out.println("デバッグ");
        vRet.stream().forEach(r -> {
            System.out.println(r.getMessage());
        });
    }

    @Test
    public void test2() {
        ItemForm itemForm = new ItemForm();
        itemForm.setItemId("item001");
        itemForm.setName("商品001");

        PriceForm priceForm01 = new PriceForm();
        priceForm01.setVersion("1");
        priceForm01.setActivateFrom("2020/04/01 00:00:00");
        priceForm01.setActivateTo("2021/04/01 00:00:00");
        priceForm01.setAmount("100");

        PriceForm priceForm02 = new PriceForm();
        priceForm02.setVersion("2");
        priceForm02.setActivateFrom("2021/04/01 00:00:00");
        priceForm02.setActivateTo("2022/03/31 23:59:59");
        priceForm02.setAmount("100");

        itemForm.setPrices(Arrays.asList(priceForm01, priceForm02));

        Set<ConstraintViolation<ItemForm>> vRet = validator.validate(itemForm);

        assertFalse(vRet.isEmpty());
        assertThat(vRet.size(), is(1));
    }
}
