package com.jurinn.web.demo;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemForm {
    @NotNull
    @Digits(integer = 2, fraction = 0)
    private String id;
    @NotNull
    @Size(min = 1, max = 127)
    private String title;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
