package com.newage.vegetableonlinesell.bean;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
