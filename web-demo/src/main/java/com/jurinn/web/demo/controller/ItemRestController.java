package com.jurinn.web.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jurinn.web.demo.model.Item;
import com.jurinn.web.demo.model.Price;
import com.jurinn.web.demo.repository.ItemRepository;
import com.jurinn.web.demo.service.ItemService;

@RestController
@RequestMapping("api/items")
public class ItemRestController {
    @Autowired
    ItemService itemService;

    LocalDateTime editStrintToLocalDateTime(String value) {
        return LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").parse(value));
    }

    @GetMapping
    public Map<String, List<Item>> getItems() {
        // TODO:ロジックを組み込む
        LocalDateTime now = LocalDateTime.now();
        Item item = new Item("item001", "商品001", "説明", now);

        Price price1 = new Price(1, editStrintToLocalDateTime("2020/04/01 00:00:00"),
                editStrintToLocalDateTime("2021/04/01 00:00:00"), 100.0, now);
        Price price2 = new Price(2, editStrintToLocalDateTime("2021/04/01 00:00:00"),
                editStrintToLocalDateTime("2022/03/31 23:59:59"), 100.0, now);
        Price price3 = new Price(3, editStrintToLocalDateTime("2021/04/01 00:00:00"),
                editStrintToLocalDateTime("2022/03/31 23:59:59"), 100.0, now);
        item.setPrices(Arrays.asList(price1, price2, price3));
        Map<String, List<Item>> res = new HashMap<>();
        res.put("items", Arrays.asList(item, item, item));
        return res;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Item> postItems(@Validated @RequestBody Map<String, Item> postedItem, BindingResult result) {
        if (result.hasErrors()) {
            // バリデーションエラー
            // TODO:どうするか考え中
            throw new RuntimeException("エラー");
        }
        itemService.add(postedItem.get("item"));
        return postedItem;
    }
}
