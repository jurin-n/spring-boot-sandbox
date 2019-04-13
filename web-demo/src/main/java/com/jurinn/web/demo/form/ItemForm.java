package com.jurinn.web.demo.form;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemForm {
    @NotNull
    @Size(min = 1, max = 64)
    private String itemId;
    @NotNull
    @Size(min = 1, max = 256)
    private String name;
    @Size(min = 1, max = 2048)
    private String description;
    @Valid
    private List<PriceForm> prices;

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<PriceForm> getPrices() {
        return prices;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrices(List<PriceForm> prices) {
        this.prices = prices;
    }
}
