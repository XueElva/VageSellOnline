package com.newage.vegetableonlinesell.bean;

import cn.bmob.v3.BmobObject;
//商品分类表
public class ProGroup extends BmobObject{
private String GroupName; //分类名称

public String getGroupName() {
	return GroupName;
}

public void setGroupName(String groupName) {
	GroupName = groupName;
}



}
