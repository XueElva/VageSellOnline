package com.newage.vagetableonlinesell.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.xu.activity.XuBaseActivity;

public class AddNewAddressDialog extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_newaddress);
	}

}