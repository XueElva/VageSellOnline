package com.newage.vegetableonlinesell.bean;

import java.io.File;
import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

//���׸�����
public class CookBookGroup extends BmobObject implements Serializable{
private String GroupName; //����
private BmobFile GroupPic; //��ͼƬ
public String getGroupName() {
	return GroupName;
}
public void setGroupName(String groupName) {
	GroupName = groupName;
}
public BmobFile getGroupPic() {
	return GroupPic;
}
public void setGroupPic(BmobFile groupPic) {
	GroupPic = groupPic;
}

}
