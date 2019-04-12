package com.jurinn.web.demo.model;

public class MenuItem {
    private String dispName;
    private String path;

    public MenuItem(String dispName, String path) {
        super();
        this.dispName = dispName;
        this.path = path;
    }

    public String getDispName() {
        return dispName;
    }

    public String getPath() {
        return path;
    }

    public void setDispName(String dispName) {
        this.dispName = dispName;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
