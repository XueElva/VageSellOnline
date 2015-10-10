package com.newage.vagetableonlinesell.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bmob.v3.BmobUser;

import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.Constant;
import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.xu.activity.XuBaseActivity;

public class UserActivity extends XuBaseActivity implements OnClickListener {
	ImageView mBack, mSetting;
	TextView mDefaultAddress, mDefaultPhone;
	TextView mMyOrder, mMyShoppingCart, mMyAddr, mCallServer;

	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_user);
		mBack = (ImageView) findViewById(R.id.back);
		mMyOrder = (TextView) findViewById(R.id.myOrder);
		mMyShoppingCart = (TextView) findViewById(R.id.myShoppingCart);
		mMyAddr = (TextView) findViewById(R.id.myAddr);
		mCallServer = (TextView) findViewById(R.id.callServer);
		mSetting = (ImageView) findViewById(R.id.setting);
		mDefaultAddress = (TextView) findViewById(R.id.defaultAddress);
		mDefaultPhone = (TextView) findViewById(R.id.defaultPhone);

		User currentUser = BmobUser.getCurrentUser(getApplicationContext(),
				User.class);
		try {
			if (currentUser.getAddress() != null) {
				JSONObject defaultAddress = CommonTools
						.getDefaultAddress(new JSONArray(currentUser
								.getAddress()));
				if (defaultAddress != null) {

					mDefaultAddress.setText(getResources().getString(
							R.string.defaultAddress)
							+ defaultAddress.getString("detail"));
					mDefaultPhone.setText(getResources().getString(
							R.string.connectPhone)
							+ defaultAddress.getString("phone"));
				}

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mSetting.setOnClickListener(this);
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
		case R.id.setting:
			startActivityForResult(new Intent(UserActivity.this,
					SettingActivity.class), Constant.LOGOUT);
			break;
		case R.id.myShoppingCart:
			Intent intent1 = new Intent(UserActivity.this,
					ShoppingCartActivity.class);
			startActivity(intent1);
			break;
		case R.id.myOrder: // 我的订单
			Intent intent = new Intent(UserActivity.this, MyOrderActivity.class);
			startActivity(intent);
			break;
		case R.id.myAddr: // 我的收货地址
			Intent intent2 = new Intent(UserActivity.this,
					MyAddressActivity.class);
			startActivity(intent2);
			break;
		case R.id.callServer: // 联系商家

			break;
		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Constant.LOGOUT) {
			finish();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
