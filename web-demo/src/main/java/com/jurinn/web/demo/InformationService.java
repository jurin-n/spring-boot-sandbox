package com.jurinn.web.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InformationService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    // NamedParameterJdbcTemplate jdbcTemplate;

    public List<TopInfo> getTopInfoList() {
        List<TopInfo> topInfo = jdbcTemplate.query("SELECT id, title, datetime FROM top_info ORDER BY id",
                (rs, row) -> new TopInfo(rs.getInt(1), rs.getString(2), rs.getString(3)));
        return topInfo;
    }

    @Transactional
    public void add(Information information) {
        // TODO:SqlParameterSourceにマッピングした結果をinsertできない原因調査。
        // SqlParameterSource param = new BeanPropertySqlParameterSource(information);
        
        jdbcTemplate.update(
                "INSERT INTO top_info(id, title) VALUES(?, ?)",
                information.getId(),
                information.getTitle()
        );
    }
}
