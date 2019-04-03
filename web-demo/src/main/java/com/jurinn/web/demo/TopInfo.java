package com.jurinn.web.demo;

public class TopInfo {
    private Integer id;
    private String title;
    private String dateTime;

    public TopInfo(Integer id, String title, String dateTime) {
        super();
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
