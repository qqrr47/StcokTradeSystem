
package com.stock.entity;

public class TradeRecord {
	private String user_id;
	private String stock_id;
	private String stock_name;
	private Integer trade_num;
	private Float trade_price;
	private String trade_time;
	private Integer trade_type;
	private Integer status;

	public TradeRecord() {
		super();
	}

	public TradeRecord(String user_id, String stock_id, String stock_name, Integer trade_num, Float trade_price, String trade_time, Integer trade_type, Integer status) {
		this.user_id = user_id;
		this.stock_id = stock_id;
		this.stock_name = stock_name;
		this.trade_num = trade_num;
		this.trade_price = trade_price;
		this.trade_time = trade_time;
		this.trade_type = trade_type;
		this.status = status;
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

	public Integer getTrade_num() {
		return trade_num;
	}

	public void setTrade_num(Integer trade_num) {
		this.trade_num = trade_num;
	}

	public Float getTrade_price() {
		return trade_price;
	}

	public void setTrade_price(Float trade_price) {
		this.trade_price = trade_price;
	}

	public String getTrade_time() {
		return trade_time;
	}

	public void setTrade_time(String trade_time) {
		this.trade_time = trade_time;
	}

	public Integer getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(Integer trade_type) {
		this.trade_type = trade_type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}