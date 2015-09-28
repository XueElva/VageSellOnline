package com.newage.vagetableonlinesell.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.adapter.AddressAdapter;
import com.newage.vegetableonlinesell.bean.Area;
import com.newage.vegetableonlinesell.bean.Constant;
import com.newage.vegetableonlinesell.bean.User;
import com.xu.activity.XuBaseActivity;
import com.xu.adapter.CommonAdapter;
import com.xu.adapter.ViewHolder;

public class MyAddressActivity extends XuBaseActivity implements
		OnClickListener {
	RelativeLayout mBack;
	TextView mAddAddress;
	TextView mDefaultAddressTV;
	ListView mAddressListView;
	List<JSONObject> mAddressList;
    AddressAdapter mAdapter;    
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_myaddress);
		initView();
		User currentUser = BmobUser.getCurrentUser(MyAddressActivity.this,
				User.class);
		if (currentUser.getAddress() != null) {
			try {
				mAddressList = parseAddress(new JSONArray(
						currentUser.getAddress()));
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mAdapter=new AddressAdapter((ArrayList<JSONObject>)mAddressList, MyAddressActivity.this);
			mAddressListView.setAdapter(mAdapter);
//			mDefaultAddressTV.setText(mAdapter.getDefaultAddress().get("address")+"  "+mAdapter.getDefaultAddress().get("phone"));
		}

	}

	private void initView() {
		// TODO Auto-generated method stub
		mBack = (RelativeLayout) findViewById(R.id.back);
		mAddAddress = (TextView) findViewById(R.id.addAddress);
		mDefaultAddressTV = (TextView) findViewById(R.id.defaultAddress);
		mAddressListView = (ListView) findViewById(R.id.addressLV);
		mBack.setOnClickListener(this);
		mAddAddress.setOnClickListener(this);
	}

	private ArrayList<JSONObject> parseAddress(JSONArray array) {
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		for (int i = 0; i < array.length(); i++) {
			try {
				list.add(array.getJSONObject(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.addAddress:
			startActivityForResult(new Intent(MyAddressActivity.this,
					AddNewAddressDialog.class), Constant.ADD_NEWADDRESS);
			break;
		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Constant.ADD_NEWADDRESS) {

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
