package com.newage.vagetableonlinesell.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;

import com.newage.vegetableonlinesell.bean.Constant;
import com.newage.vegetableonlinesell.bean.User;
import com.xu.activity.XuBaseActivity;

public class SettingActivity extends XuBaseActivity implements OnClickListener {
	TextView mLogout;
	RelativeLayout mBack;
	boolean logout;

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_setting);
		mLogout = (TextView) findViewById(R.id.logout);
		mBack = (RelativeLayout) findViewById(R.id.back);
		mLogout.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.logout:
			User currentUser = BmobUser.getCurrentUser(SettingActivity.this,
					User.class);
			currentUser.logOut(SettingActivity.this);
			Toast.makeText(getApplicationContext(),
					getResources().getString(R.string.logoutSucceed), 1).show();
			logout = true;
			finish();
			break;
		case R.id.back:
			finish();
			break;
		default:
			break;
		}

	}

	@Override
	public void finish() {
		if (logout) {
			setResult(Constant.LOGOUT);
		}
		super.finish();
	}
}
