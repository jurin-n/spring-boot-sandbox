package com.jurinn.web.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jurinn.web.demo.model.Item;
import com.jurinn.web.demo.model.Price;

@Service
public class ItemService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    // NamedParameterJdbcTemplate jdbcTemplate;

    public List<Item> getItems() {
        List<Item> items = jdbcTemplate.query("SELECT item_id, name, datetime FROM items ORDER BY datetime",
                (rs, row) -> new Item(rs.getString("item_id"), rs.getString("name"), rs.getTimestamp("datetime")));
        return items;
    }

    @Transactional
    public void add(Item item, Price price) {
        // TODO:SqlParameterSourceにマッピングした結果をinsertできない原因調査。
        // SqlParameterSource param = new BeanPropertySqlParameterSource(information);

        jdbcTemplate.update(
                "INSERT INTO items(item_id, name, description, datetime) VALUES(?, ?, ?, ?)",
                item.getItemId(),
                item.getName(),
                item.getDescription(),
                item.getDateTime()
        );

        jdbcTemplate.update(
                "INSERT INTO prices(item_id,activate_from, activate_to, price,  datetime) VALUES(?, ?, ?, ?, ?)",
                item.getItemId(),
                price.getActivateFrom(),
                price.getActivateTo(),
                price.getPrice(),
                item.getDateTime()
        ); 
    }
}
