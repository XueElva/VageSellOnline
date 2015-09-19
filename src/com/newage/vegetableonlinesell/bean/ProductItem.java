package com.newage.vegetableonlinesell.bean;

import java.io.Serializable;

import android.provider.ContactsContract.CommonDataKinds.Relation;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

//��Ʒ��
public class ProductItem extends BmobObject implements Serializable{
	private String Name; // ��Ʒ����
	private String PicUrl; // ��ƷͼƬ����
	private BmobRelation GroupTag; // ��Ʒ�������Թ���
	private String ProDetails; //��Ʒ���
	
	
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
