package com.jurinn.web.demo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class PriceTest {

    LocalDateTime editStrintToLocalDateTime(String value) {
        return LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").parse(value));
    }

    @Test
    public void test() {
        LocalDateTime now = LocalDateTime.now();
        Price p1 = new Price(1, editStrintToLocalDateTime("2020/04/01 00:00:00"),
                editStrintToLocalDateTime("2021/03/31 23:59:59"), 200.0, now);
        Price p2 = new Price(2, editStrintToLocalDateTime("2021/04/01 00:00:00"),
                editStrintToLocalDateTime("2022/03/31 23:59:59"), 150.0, now);
        Price p3 = new Price(3, editStrintToLocalDateTime("2022/04/01 00:00:00"),
                editStrintToLocalDateTime("2023/03/31 23:59:59"), 250.0, now);
        List<Price> prices = Arrays.asList(p1, p2, p3);

        List<Map<String, Integer>> result = Price.checkApplicablePeriodOfPrices(prices);

        assertThat(result.size(), is(0));
    }

    @Test
    public void test2() {
        LocalDateTime now = LocalDateTime.now();
        Price p1 = new Price(1, editStrintToLocalDateTime("2020/04/01 00:00:00"),
                editStrintToLocalDateTime("2021/04/01 00:00:00"), 200.0, now);
        Price p2 = new Price(2, editStrintToLocalDateTime("2021/04/01 00:00:00"),
                editStrintToLocalDateTime("2022/04/01 00:00:00"), 150.0, now);
        Price p3 = new Price(3, editStrintToLocalDateTime("2022/04/01 00:00:00"),
                editStrintToLocalDateTime("2023/03/31 23:59:59"), 250.0, now);
        List<Price> prices = Arrays.asList(p1, p2, p3);

        List<Map<String, Integer>> result = Price.checkApplicablePeriodOfPrices(prices);

        assertThat(result.size(), is(2));
        assertThat(result.get(0).get("to"), is(0));
        assertThat(result.get(0).get("from"), is(1));
        assertThat(result.get(1).get("to"), is(1));
        assertThat(result.get(1).get("from"), is(2));
    }
}
