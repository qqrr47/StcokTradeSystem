package com.stock.entity;

public class User {
	private String userid;
	private String password;
	private String name;
	private Float accountBalance;

	public User() {
		super();
	}

	public User(String userid, String password, String name, Float accountBalance) {
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.accountBalance = accountBalance;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Float accountBalance) {
		this.accountBalance = accountBalance;
	}
}