package com.jurinn.web.demo;

import java.io.Serializable;

public class Information implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;

    public Information(Integer id, String title) {
        super();
        this.id = id;
        this.title = title;
    }

    public Information() {
        // TODO Auto-generated constructor stub
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
