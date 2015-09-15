package com.newage.vagetableonlinesell.activity;

import java.io.File;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import cn.bmob.v3.datatype.BmobFile;

import com.xu.activity.XuBaseActivity;

//水果列表
public class FruitListActivity extends XuBaseActivity implements
		OnClickListener {
	LinearLayout mBack;

	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_fruit);
		mBack = (LinearLayout) findViewById(R.id.back);
		// File file=new File(uri)
		mBack.setOnClickListener(this);
	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		default:
			break;
		}

	}

}
