package com.jurinn.web.demo.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

@Service
public class DateAndTimeService {
    public LocalDateTime now() {
        return LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
    }
}
