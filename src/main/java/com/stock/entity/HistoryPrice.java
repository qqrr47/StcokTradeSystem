package com.stock.entity;

public class HistoryPrice {
    private String stockid;
    private Float stockPrice;
    private Integer stockSequance;

    public HistoryPrice() {
        super();
    }

    public HistoryPrice(String stockid, Float stockPrice, Integer stockSequance) {
        this.stockid = stockid;
        this.stockPrice = stockPrice;
        this.stockSequance = stockSequance;
    }

    public String getStockid() {
        return stockid;
    }

    public void setStockid(String stockid) {
        this.stockid = stockid;
    }

    public Float getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Float stockPrice) {
        this.stockPrice = stockPrice;
    }

    public Integer getStockSequance() {
        return stockSequance;
    }

    public void setStockSequance(Integer stockSequance) {
        this.stockSequance = stockSequance;
    }
}
