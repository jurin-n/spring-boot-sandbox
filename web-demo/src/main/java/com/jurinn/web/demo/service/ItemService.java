package com.jurinn.web.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jurinn.web.demo.model.Item;
import com.jurinn.web.demo.model.Price;

@Service
public class ItemService {
    // TODO:JdbcTemplateで作ったコードをNamedParameterJdbcTemplateに移行。
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate nJdbcTemplate;

    public List<Item> getItems() {
        List<Item> items = jdbcTemplate.query("SELECT item_id, name, datetime FROM items ORDER BY datetime",
                (rs, row) -> new Item(rs.getString("item_id"), rs.getString("name"), rs.getTimestamp("datetime")));
        return items;
    }

    @Transactional
    public void add(Item item, Price price) {
        // TODO:SqlParameterSourceにマッピングした結果をinsertできない原因調査。
        // SqlParameterSource param = new BeanPropertySqlParameterSource(information);

        jdbcTemplate.update("INSERT INTO items(item_id, name, description, datetime) VALUES(?, ?, ?, ?)",
                item.getItemId(), item.getName(), item.getDescription(), item.getDateTime());

        jdbcTemplate.update(
                "INSERT INTO prices(item_id,activate_from, activate_to, amount,  datetime) VALUES(?, ?, ?, ?, ?)",
                item.getItemId(), price.getActivateFrom(), price.getActivateTo(), price.getAmount(), item.getDateTime());
    }

    public Item findOne(String itemId) {
        String sql = "SELECT item_id, name, description, datetime FROM items WHERE item_id=:itemId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);

        Item item = nJdbcTemplate.queryForObject(sql, param,
                (rs, row) -> new Item(rs.getString("item_id"), rs.getString("name"), rs.getString("description"),
                        rs.getTimestamp("datetime").toLocalDateTime()));
        return item;
    }

    @Transactional
    public void update(Item item) {
        String sql = "UPDATE items SET name=:name,description=:description,datetime=:datetime WHERE item_id=:itemId";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", item.getName())
                .addValue("description", item.getDescription())
                .addValue("datetime", item.getDateTime())
                .addValue("itemId", item.getItemId());

        nJdbcTemplate.update(sql, param);   
    }
}
