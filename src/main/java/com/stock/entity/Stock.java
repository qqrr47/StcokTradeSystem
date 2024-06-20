
package com.stock.entity;

public class Stock {
	private String stock_id;
	private String stock_name;
	private Float stock_price;

	public Stock() {
		super();
	}

	public Stock(String stock_id, String stock_name, Float stock_price) {
		this.stock_id = stock_id;
		this.stock_name = stock_name;
		this.stock_price = stock_price;
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

	public Float getStock_price() {
		return stock_price;
	}

	public void setStock_price(Float stock_price) {
		this.stock_price = stock_price;
	}
}