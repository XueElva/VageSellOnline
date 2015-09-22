package com.newage.vegetableonlinesell.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;
//���ױ�
public class CookBook extends BmobObject implements Serializable{
private String Name; //��������
private BmobFile Pic; //����ͼƬ
private ProductItem Needs; //�����ܹ��ṩ��ʳ��
private User Marked; //��ע���û�
private BmobRelation GroupTag; //��������
private String AllNeeds; //����ʳ��JSON��[{"material":"","weight":""},{"material":"","weight":""}]��
private String Steps; //����JSON  ��[{"step":""},{"step":""}]��
private String Introduction; //�����еļ��


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
