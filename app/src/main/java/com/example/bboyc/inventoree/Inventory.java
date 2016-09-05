package com.example.bboyc.inventoree;

public final class Inventory {

    int id;
    String name;
    String detail;
    public Inventory() {
    }

    public Inventory(int id, String name, String detail) {
        this.id = id;
        this.name = name;
        this.detail = detail;
    }

    public Inventory(String name, String detail) {
        this.name = name;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


}

