package com.newage.vagetableonlinesell.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.xu.activity.XuBaseActivity;

public class LoginActivity extends XuBaseActivity implements OnClickListener {
	private EditText mAccount, mPassword;
	private CheckBox mRememberPassword;
	private TextView mRegister, mForgetPass;
	private Button mLogin;

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_login);
		mAccount = (EditText) findViewById(R.id.account);
		mPassword = (EditText) findViewById(R.id.password);
		mRememberPassword = (CheckBox) findViewById(R.id.rememberPassword);
		mRegister = (TextView) findViewById(R.id.register);
		mLogin = (Button) findViewById(R.id.login);

		mLogin.setOnClickListener(this);
		mRegister.setOnClickListener(this);
		mForgetPass.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:

			break;
		case R.id.register:
			Intent intent = new Intent(LoginActivity.this,
					RegisterActivity.class);
			startActivityForResult(intent, 10);
			
			break;
		case R.id.forgetPassword:

			break;

		default:
			break;
		}

	}

}
