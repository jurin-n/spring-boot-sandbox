package com.jurin_n.csvprocessing;

public class CsvContent {
    private String title;
    private String description;

    public CsvContent(String title, String description) {
        super();
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CsvContent [title=" + title + ", description=" + description + "]";
    }
}
