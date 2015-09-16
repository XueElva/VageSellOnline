package com.newage.vagetableonlinesell.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newage.vegetableonlinesell.bean.Constant;
import com.xu.activity.XuBaseActivity;

public class MyAddressActivity extends XuBaseActivity implements OnClickListener{
RelativeLayout mBack;
TextView mAddAddress;
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_myaddress);
		mBack=(RelativeLayout) findViewById(R.id.back);
		mAddAddress=(TextView) findViewById(R.id.addAddress);
		mBack.setOnClickListener(this);
		mAddAddress.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.addAddress:
			startActivityForResult(new Intent(MyAddressActivity.this,AddNewAddressDialog.class), Constant.ADD_NEWADDRESS);
			break;
		default:
			break;
		}
		
		
	}
	
	@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			if(resultCode==Constant.ADD_NEWADDRESS){
				
			}
			super.onActivityResult(requestCode, resultCode, data);
		}

}
