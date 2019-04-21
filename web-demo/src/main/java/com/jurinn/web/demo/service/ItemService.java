package com.jurinn.web.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jurinn.web.demo.model.Item;
import com.jurinn.web.demo.model.Price;
import com.jurinn.web.demo.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public void add(Item item) {
        // 商品ID重複チェック
        if (itemRepository.findOne(item.getItemId()) != null) {
            // TODO:例外どうするか考え中
            throw new RuntimeException("商品ID重複チェックエラー");
        }

        // 価格の適用期間のチェック
        List<Map<String, Integer>> errors = Price.checkApplicablePeriodOfPrices(item.getPrices());
        if (errors.size() > 0) {
            String errorMessages = "";
            for (Map<String, Integer> error : errors) {
                Integer to = error.get("to");
                Integer from = error.get("from");
                // TODO:エラーメッセージ考える。
                String errorMessage = "{prices[" + to + "].activateTo" + item.getPrices().get(to).getActivateTo() + ","
                        + "prices[" + from + "].activateFrom" + item.getPrices().get(from).getActivateFrom() + "}";
                errorMessages += errorMessage;
            }
            // TODO:例外どうするか考え中
            throw new RuntimeException("価格の適用期間のチェックエラー。" + errorMessages);
        }

        itemRepository.add(item, item.getPrices());
    }
}
