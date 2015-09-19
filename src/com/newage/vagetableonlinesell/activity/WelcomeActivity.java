
package com.newage.vagetableonlinesell.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.newage.vegetableonlinesell.activity.R;
import com.xu.activity.XuBaseActivity;

public class WelcomeActivity extends XuBaseActivity {

	TextView mWelcome;
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_welcome);
		mWelcome=(TextView) findViewById(R.id.welcome);
		mWelcome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(WelcomeActivity.this,SelectAddressActivity.class));
				finish();
			}
		});
		
	}

}
