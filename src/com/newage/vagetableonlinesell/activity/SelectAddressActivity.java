package com.newage.vagetableonlinesell.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.newage.vegetableonlinesell.activity.R;
import com.xu.activity.XuBaseActivity;

public class SelectAddressActivity extends XuBaseActivity{
TextView mEnter;
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_selectaddress);
		mEnter = (TextView) findViewById(R.id.enter);
		mEnter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent (SelectAddressActivity.this,MainActivity.class));
				finish();
				
			}
		});
	}


}
