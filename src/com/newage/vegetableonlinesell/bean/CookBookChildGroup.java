package com.newage.vegetableonlinesell.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobPointer;

//�ӷ���
public class CookBookChildGroup extends BmobObject implements Serializable{
private String GroupName;  //����
private CookBookGroup ParentGroup; //������
public String getGroupName() {
	return GroupName;
}
public void setGroupName(String groupName) {
	GroupName = groupName;
}
public CookBookGroup getParentGroup() {
	return ParentGroup;
}
public void setParentGroup(CookBookGroup parentGroup) {
	ParentGroup = parentGroup;
}


}
