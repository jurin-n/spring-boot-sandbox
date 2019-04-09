package com.jurinn.web.demo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Item implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String itemId;
    private String name;
    private String description;
    private LocalDateTime dateTime;

    public Item(String itemId, String name, String description, LocalDateTime dateTime) {
        super();
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
    }

    public Item(String itemId, String name, Timestamp timestamp) {
        super();
        this.itemId = itemId;
        this.name = name;
        this.dateTime = timestamp.toLocalDateTime();
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
