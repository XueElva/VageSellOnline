package com.newage.vegetableonlinesell.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;
//菜谱表
public class CookBook extends BmobObject implements Serializable{
private String Name; //菜谱名称
private BmobFile Pic; //菜谱图片
private ProductItem Needs; //店铺能够提供的食材
private User Marked; //关注的用户
private BmobRelation GroupTag; //所属分类
private String AllNeeds; //所有食材JSON（[{"material":"","weight":""},{"material":"","weight":""}]）
private String Steps; //步骤JSON  （[{"step":""},{"step":""}]）
private String Introduction; //详情中的简介


public String getIntroduction() {
	return Introduction;
}
public void setIntroduction(String introduction) {
	Introduction = introduction;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public BmobFile getPic() {
	return Pic;
}
public void setPic(BmobFile pic) {
	Pic = pic;
}
public ProductItem getNeeds() {
	return Needs;
}
public void setNeeds(ProductItem needs) {
	Needs = needs;
}
public User getMarked() {
	return Marked;
}
public void setMarked(User marked) {
	Marked = marked;
}
public BmobRelation getGroupTag() {
	return GroupTag;
}
public void setGroupTag(BmobRelation groupTag) {
	GroupTag = groupTag;
}
public String getAllNeeds() {
	return AllNeeds;
}
public void setAllNeeds(String allNeeds) {
	AllNeeds = allNeeds;
}
public String getSteps() {
	return Steps;
}
public void setSteps(String steps) {
	Steps = steps;
}

}
