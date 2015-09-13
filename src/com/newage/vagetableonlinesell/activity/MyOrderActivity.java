package com.newage.vagetableonlinesell.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xu.activity.XuBaseActivity;

public class MyOrderActivity extends XuBaseActivity implements OnClickListener{
	ImageView mBack;
	LinearLayout mProcessing,mDone;
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_myorder);
		mBack = (ImageView) findViewById(R.id.back);
		mProcessing=(LinearLayout) findViewById(R.id.tab_processing);
		mDone=(LinearLayout) findViewById(R.id.tab_done);
		
		mProcessing.getChildAt(0).setSelected(true);
		mProcessing.getChildAt(1).setSelected(true);
		mBack.setOnClickListener(this);
		mProcessing.setOnClickListener(this);
		mDone.setOnClickListener(this);
	}
	
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.tab_processing: //未完成
			mProcessing.getChildAt(0).setSelected(true);
			mProcessing.getChildAt(1).setSelected(true);
			mDone.getChildAt(0).setSelected(false);
			mDone.getChildAt(1).setSelected(false);
			break;
		case R.id.tab_done:  //已完成
			mProcessing.getChildAt(0).setSelected(false);
			mProcessing.getChildAt(1).setSelected(false);
			mDone.getChildAt(0).setSelected(true);
			mDone.getChildAt(1).setSelected(true);
			break;
		default:
			break;
		}
		
	}

}
