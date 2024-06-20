package com.stock.entity;

public class Inventory {
    private String user_id;
    private String stock_id;
    private String stock_name;
    private Integer stock_num;
    private Float stock_price;

    public Inventory(String user_id, String stock_id, String stock_name, Integer stock_num, Float stock_price) {
        this.user_id = user_id;
        this.stock_id = stock_id;
        this.stock_name = stock_name;
        this.stock_num = stock_num;
        this.stock_price = stock_price;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public Integer getStock_num() {
        return stock_num;
    }

    public void setStock_num(Integer stock_num) {
        this.stock_num = stock_num;
    }

    public Float getStock_price() {
        return stock_price;
    }

    public void setStock_price(Float stock_price) {
        this.stock_price = stock_price;
    }
}
