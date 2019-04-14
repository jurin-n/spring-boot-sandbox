package com.jurinn.web.demo.form;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
        priceForm01.setActivateFrom("");
        priceForm01.setActivateTo("");
        priceForm01.setAmount("");
        itemForm.setPrices(Arrays.asList(priceForm01, new PriceForm(), new PriceForm()));

        Set<ConstraintViolation<ItemForm>> vRet = validator.validate(itemForm);
        //デバッグ用
        //vRet.stream().forEach(r -> {
        //    System.out.println(r.getMessage() + "[" + r.getInvalidValue().toString() + "]");
        //});
        System.out.println("デバッグ");
        vRet.stream().forEach(r -> {
            System.out.println(r.getMessage());
        });
    }
}
