package com.example.alarstudios.models;

import java.util.List;

public class Data {
    private String status;
    private String page;
    private List<Human> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Human> getData() {
        return data;
    }

    public void setData(List<Human> data) {
        this.data = data;
    }
}
