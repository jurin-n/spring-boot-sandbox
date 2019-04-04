package com.jurinn.web.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MenuService {
    public List<MenuItem> getMenuItems() {
        return Arrays.asList(
                new MenuItem("メニュー０１","/addInfo"),
                new MenuItem("メニュー０２","/deleteInfo"),
                new MenuItem("メニュー０３","/list"),
                new MenuItem("メニュー０４","/search")
                );
    }
}
