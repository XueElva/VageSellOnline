package com.newage.vagetableonlinesell.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;
import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.Constant;
import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.xu.activity.XuBaseActivity;

public class LoginActivity extends XuBaseActivity implements OnClickListener {
	TextView mBack;
	EditText mAccount, mPassword;
	Button mLogin;
	CheckBox mRememberPassword;
	TextView mRegister, mForgetPass;

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_login);

		mBack = (TextView) findViewById(R.id.back);
		mAccount = (EditText) findViewById(R.id.account);
		mPassword = (EditText) findViewById(R.id.password);
		mLogin = (Button) findViewById(R.id.login);
		mRememberPassword = (CheckBox) findViewById(R.id.rememberPassword);
		mRegister = (TextView) findViewById(R.id.register);
		mForgetPass = (TextView) findViewById(R.id.forgetPassword);

		mLogin.setOnClickListener(this);
		mBack.setOnClickListener(this);
		mRegister.setOnClickListener(this);
		mForgetPass.setOnClickListener(this);

		SharedPreferences userSetting = getSharedPreferences(
				Constant.USER_SETTING, Activity.MODE_PRIVATE);
		mAccount.setText(userSetting.getString(Constant.USER_NAME, ""));

		if (userSetting.getBoolean(Constant.REMEMBER_PASS, false)) {
			mRememberPassword.setChecked(true);
			mPassword.setText(userSetting.getString(Constant.PASSWORD, ""));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.login:
			final String account = mAccount.getText().toString();
			final String pass = mPassword.getText().toString();
			if (account.equals("")) {
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.inputAccount), 1)
						.show();
			} else if (pass.equals("")) {
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.inputPass), 1).show();
			} else {
				CommonTools.createLoadingDialog(LoginActivity.this);
				User user = new User();
				user.setUsername(account);
				user.setPassword(pass);
				user.login(LoginActivity.this, new SaveListener() {

					@Override
					public void onSuccess() {
						Editor editor = getSharedPreferences(
								Constant.USER_SETTING, Activity.MODE_PRIVATE)
								.edit();
						editor.putString(Constant.USER_NAME, account);
						if (mRememberPassword.isChecked()) {
							editor.putString(Constant.PASSWORD, pass);
							editor.putBoolean(Constant.REMEMBER_PASS, true);
						} else {
							editor.putBoolean(Constant.REMEMBER_PASS, false);
						}
						editor.commit();
						CommonTools.cancleDialog();
						Toast.makeText(
								getApplicationContext(),
								getResources().getString(R.string.loginSucceed),
								1).show();
						startActivity(new Intent(LoginActivity.this,
								MainActivity.class));
						finish();
					}

					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub
						CommonTools.cancleDialog();
						Toast.makeText(
								LoginActivity.this,
								getResources().getString(R.string.loginFail)
										+ arg1, 1).show();
					}
				});
			}
			break;
		case R.id.register:
			startActivityForResult(new Intent(LoginActivity.this,
					RegisterActivity.class), 1);
			break;
		case R.id.forgetPassword:

			break;
		default:
			break;
		}

	}

}
