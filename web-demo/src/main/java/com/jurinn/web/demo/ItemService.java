package com.jurinn.web.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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

    /*
    @Transactional
    public void add(Item information) {
        // TODO:SqlParameterSourceにマッピングした結果をinsertできない原因調査。
        // SqlParameterSource param = new BeanPropertySqlParameterSource(information);
        
        jdbcTemplate.update(
                "INSERT INTO top_info(id, title) VALUES(?, ?)",
                information.getId(),
                information.getTitle()
        );
    }
    */
}
