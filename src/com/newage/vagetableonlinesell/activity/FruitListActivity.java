package com.newage.vagetableonlinesell.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.xu.activity.XuBaseActivity;

//水果列表
public class FruitListActivity extends XuBaseActivity implements OnClickListener{
	LinearLayout mBack;
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_fruit);
		mBack = (LinearLayout) findViewById(R.id.back);
		
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
