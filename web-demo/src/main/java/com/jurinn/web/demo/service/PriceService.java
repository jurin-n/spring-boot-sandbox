package com.jurinn.web.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jurinn.web.demo.model.Price;

@Service
public class PriceService {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public List<Price> find(String itemId) {
        String sql = "SELECT activate_from,activate_to,price,datetime FROM prices WHERE item_id = :itemId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);

        List<Price> prices = jdbcTemplate.query(sql, param,
                (rs, row) -> new Price(
                                rs.getTimestamp("activate_from").toLocalDateTime(),
                                rs.getTimestamp("activate_to").toLocalDateTime(),
                                rs.getDouble("price"),
                                rs.getTimestamp("datetime").toLocalDateTime()
                        )
                );

        return prices;
    }

    @Transactional
    public void update(String itemId, Price price) {
        String sql = "UPDATE prices SET price=:price,datetime=:datetime WHERE item_id=:itemId AND activate_from=:activateFrom AND activate_to=:activateTo";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("price", price.getPrice())
                .addValue("datetime", price.getDateTime())
                .addValue("itemId", itemId)
                .addValue("activateFrom", price.getActivateFrom())
                .addValue("activateTo", price.getActivateTo());

        jdbcTemplate.update(sql, param);
    }
}
