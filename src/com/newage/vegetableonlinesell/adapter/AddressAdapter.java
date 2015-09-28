package com.newage.vegetableonlinesell.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.newage.vagetableonlinesell.activity.MyAddressActivity;
import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.Area;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AddressAdapter extends BaseAdapter {
	List<JSONObject> mAddressList;
	Context mContext;
	HashMap<String, String> mDefaultAddress;

	public AddressAdapter(ArrayList<JSONObject> addressList, Context context) {
		this.mAddressList = addressList;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mAddressList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mAddressList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	ViewHolder vh = null;

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		vh = null;
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.item_address, null);
			vh = new ViewHolder();
			vh.address = (TextView) convertView.findViewById(R.id.address);
			vh.phone = (TextView) convertView.findViewById(R.id.phone);
			vh.setDefault = (TextView) convertView
					.findViewById(R.id.setDefault);
			vh.delete = (TextView) convertView.findViewById(R.id.delete);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		final JSONObject item = mAddressList.get(position);
		mDefaultAddress = new HashMap<String, String>();
		try {
			vh.phone.setText(item.getString("phone"));
			vh.address.setText(item.getString("detail"));
			if(item.getString("isDefault").equals("true")){
				mDefaultAddress.put("address", item.getString("detail"));
				mDefaultAddress.put("phone", item.getString("phone"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertView;
	}

	class ViewHolder {
		TextView address, phone;
		TextView setDefault, delete;
	}

	public HashMap<String, String> getDefaultAddress() {
		return mDefaultAddress;
	}
}
