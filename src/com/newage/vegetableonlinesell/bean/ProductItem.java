package com.newage.vegetableonlinesell.bean;

import java.io.Serializable;

import android.provider.ContactsContract.CommonDataKinds.Relation;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

//商品表
public class ProductItem extends BmobObject implements Serializable{
	private String Name; // 商品名称
	private String PicUrl; // 商品图片链接
	private BmobRelation GroupTag; // 商品分类属性关联
	private String ProDetails; //商品简介
	
	
	public String getProDetails() {
		return ProDetails;
	}
	public void setProDetails(String proDetails) {
		ProDetails = proDetails;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public BmobRelation getGroupTag() {
		return GroupTag;
	}
	public void setGroupTag(BmobRelation groupTag) {
		GroupTag = groupTag;
	}
	
	
}
