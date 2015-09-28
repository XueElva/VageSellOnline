package com.newage.vegetableonlinesell.bean;

import cn.bmob.v3.BmobObject;

public class Area extends BmobObject{
private String name;
private Integer level; //1.省2.市3.区4.小区
private String parentArea; //上一级地区ID
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getLevel() {
	return level;
}
public void setLevel(Integer level) {
	this.level = level;
}
public String getParentArea() {
	return parentArea;
}
public void setParentArea(String parentArea) {
	this.parentArea = parentArea;
}


}
