package com.newage.vagetableonlinesell.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.newage.vegetableonlinesell.activity.R;
import com.xu.activity.XuBaseActivity;

public class RegisterActivity extends XuBaseActivity implements OnClickListener{
TextView mBack;
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_register);
		mBack=(TextView) findViewById(R.id.back);
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
