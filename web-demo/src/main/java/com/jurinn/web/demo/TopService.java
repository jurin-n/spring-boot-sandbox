package com.jurinn.web.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TopService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    //NamedParameterJdbcTemplate jdbcTemplate;
    
    public List<TopInfo> getTopInfoList() {
        List<TopInfo> topInfo = jdbcTemplate.query("SELECT id, title, datetime FROM top_info ORDER BY id",
                (rs, row) -> new TopInfo(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3))
            );
        return topInfo;
    }
}
