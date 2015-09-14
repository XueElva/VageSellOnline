package com.newage.vagetableonlinesell.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.xu.activity.XuBaseActivity;

public class UserActivity extends XuBaseActivity implements OnClickListener {
	ImageView mBack;
	TextView mMyOrder,mMyShoppingCart, mMyAddr, mCallServer;

	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_user);
		mBack = (ImageView) findViewById(R.id.back);
		mMyOrder = (TextView) findViewById(R.id.myOrder);
		mMyShoppingCart=(TextView) findViewById(R.id.myShoppingCart);
		mMyAddr = (TextView) findViewById(R.id.myAddr);
		mCallServer = (TextView) findViewById(R.id.callServer);
		
		mBack.setOnClickListener(this);
		mMyAddr.setOnClickListener(this);
		mMyOrder.setOnClickListener(this);
		mMyShoppingCart.setOnClickListener(this);
		mCallServer.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.myShoppingCart:
			Intent intent1 = new Intent(UserActivity.this, ShoppingCartActivity.class);
			startActivity(intent1);
			break;
		case R.id.myOrder: // 我的订单
			Intent intent = new Intent(UserActivity.this, MyOrderActivity.class);
			startActivity(intent);
			break;
		case R.id.myAddr: // 我的收货地址
			Intent intent2 = new Intent(UserActivity.this, MyAddressActivity.class);
			startActivity(intent2);
			break;
		case R.id.callServer: // 联系商家

			break;
		default:
			break;
		}

	}
}
