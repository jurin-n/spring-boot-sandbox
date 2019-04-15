package com.jurinn.web.demo.form.validation;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class PriceAmountFormatTest {

    class Price {

        @PriceAmountFormat(integer = 2, fraction = 1)
        private String amount;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    private ValidatorFactory vf;
    private Validator validator;

    @Before
    public void setUp() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    Iterator<ConstraintViolation<Price>> getValidResult(Price sut) {
        Set<ConstraintViolation<Price>> vRet = validator.validate(sut);
        return vRet.iterator();
    }

    @Test
    public void test01() {
        Price sut = new Price();

        sut.setAmount("10.1");
        Iterator<ConstraintViolation<Price>> v1 = getValidResult(sut);

        assertFalse(v1.hasNext());
    }

    @Test
    public void test02() {
        Price sut = new Price();
        sut.setAmount("100.1");

        Iterator<ConstraintViolation<Price>> v1 = getValidResult(sut);
        ConstraintViolation<Price> r = v1.next();

        assertThat(r.getMessage(), is(containsString("numeric value out of bounds")));
        assertThat(r.getInvalidValue().toString(), is("100.1"));
    }

    @Test
    public void test03() {
        Price sut = new Price();
        sut.setAmount("10.12");

        Iterator<ConstraintViolation<Price>> v1 = getValidResult(sut);
        ConstraintViolation<Price> r = v1.next();

        assertThat(r.getMessage(), is(containsString("numeric value out of bounds")));
        assertThat(r.getInvalidValue().toString(), is("10.12"));
    }
}
