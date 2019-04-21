package com.jurinn.web.demo.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jurinn.web.demo.model.Item;
import com.jurinn.web.demo.model.Price;

@Service
public class ItemRepository {
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
    public void add(Item item, List<Price> prices) {
        // TODO:SqlParameterSourceにマッピングした結果をinsertできない原因調査。
        // SqlParameterSource param = new BeanPropertySqlParameterSource(information);

        jdbcTemplate.update("INSERT INTO items(item_id, name, description, datetime) VALUES(?, ?, ?, ?)",
                item.getItemId(), item.getName(), item.getDescription(), item.getDateTime());

        jdbcTemplate.batchUpdate(
                "INSERT INTO prices(item_id, version, activate_from, activate_to, amount,  datetime) VALUES(?, ?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Price price = prices.get(i);
                        ps.setString(1, item.getItemId());
                        ps.setInt(2, price.getVersion());
                        ps.setTimestamp(3, Timestamp.valueOf(price.getActivateFrom()));
                        ps.setTimestamp(4, Timestamp.valueOf(price.getActivateTo()));
                        ps.setDouble(5, price.getAmount());
                        ps.setTimestamp(6, Timestamp.valueOf(item.getDateTime()));
                    }

                    @Override
                    public int getBatchSize() {
                        return prices.size();
                    }
                });
    }

    public Item findOne(String itemId) {
        String sql = "SELECT item_id, name, description, datetime FROM items WHERE item_id=:itemId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);

        try {
            Item item = nJdbcTemplate.queryForObject(sql, param, (rs, row) -> new Item(rs.getString("item_id"),
                    rs.getString("name"), rs.getString("description"), rs.getTimestamp("datetime").toLocalDateTime()));
            return item;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Transactional
    public void update(Item item) {
        String sql = "UPDATE items SET name=:name,description=:description,datetime=:datetime WHERE item_id=:itemId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("name", item.getName())
                .addValue("description", item.getDescription()).addValue("datetime", item.getDateTime())
                .addValue("itemId", item.getItemId());

        nJdbcTemplate.update(sql, param);
    }
}
