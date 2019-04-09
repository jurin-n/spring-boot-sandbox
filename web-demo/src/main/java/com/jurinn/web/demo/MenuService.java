package com.jurinn.web.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MenuService {
    public List<MenuItem> getMenuItems() {
        return Arrays.asList(
                new MenuItem("トップページ","/"),
                new MenuItem("商品一覧","/items"),
                new MenuItem("商品追加","/items/add")
                );
    }
}
