package com.newage.vegetableonlinesell.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import com.newage.vagetableonlinesell.activity.MyAddressActivity;
import com.newage.vagetableonlinesell.activity.MyAddressActivity.OnShowDefaultAddressListener;
import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.Area;
import com.newage.vegetableonlinesell.bean.User;
import com.xu.utils.T;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AddressAdapter extends BaseAdapter {
	List<JSONObject> mAddressList;
	Context mContext;
	HashMap<String, String> mDefaultAddress;
	OnShowDefaultAddressListener mListener;

	public AddressAdapter(ArrayList<JSONObject> addressList, Context context,
			OnShowDefaultAddressListener listener) {
		this.mAddressList = addressList;
		this.mContext = context;
		this.mListener = listener;
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

		try {
			vh.phone.setText(item.getString("phone"));
			vh.address.setText(item.getString("detail"));
			if (item.getString("isDefault").equals("true")) {
				mDefaultAddress = new HashMap<String, String>();
				mDefaultAddress.put("address", item.getString("detail"));
				mDefaultAddress.put("phone", item.getString("phone"));
				mListener.OnShowDefaultAddress(mDefaultAddress);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		vh.delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mAddressList.size() > 1) {
					try {
						if (item.getString("isDefault").equals("true")) {
							T.show(mContext,
									mContext.getResources()
											.getText(
													R.string.cannotDeleteDefaultAddress),
									1);
						} else {

							final List<JSONObject> addressList = new ArrayList<JSONObject>();
							addressList.addAll(mAddressList);
							addressList.remove(position);
							StringBuffer sb = new StringBuffer();
							sb.append("[");
							for (int i = 0; i < addressList.size(); i++) {
								sb.append(addressList.get(i).toString());
								if (i < (addressList.size() - 1)) {
									sb.append(",");
								}

							}

							sb.append("]");
							User currentUser = BmobUser.getCurrentUser(
									mContext, User.class);
							currentUser.setAddress(sb.toString());
							currentUser.update(mContext, new UpdateListener() {

								@Override
								public void onSuccess() {
									mAddressList.remove(position);
									notifyDataSetChanged();
									T.show(mContext, mContext
											.getString(R.string.deleteSucceed), 1);
								}

								@Override
								public void onFailure(int arg0, String arg1) {
									T.show(mContext, mContext
											.getString(R.string.deleteFail), 1);

								}
							});

						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					T.show(mContext,
							mContext.getResources().getText(
									R.string.atleastOneAddress), 1);
				}

			}
		});
		vh.setDefault.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					StringBuffer sb = new StringBuffer();
					final List<JSONObject> addressList = new ArrayList<JSONObject>();
					addressList.addAll(mAddressList);
					sb.append("[");
					for (int i = 0; i < addressList.size(); i++) {
						if (i == position) {
							addressList.get(i).put("isDefault", "true");
						} else {
							addressList.get(i).put("isDefault", "false");
						}
						sb.append(addressList.get(i).toString());
						if (i < (addressList.size() - 1)) {
							sb.append(",");
						}

					}
					sb.append("]");
					User currentUser = BmobUser.getCurrentUser(mContext,
							User.class);
					currentUser.setAddress(sb.toString());
					currentUser.update(mContext, new UpdateListener() {

						@Override
						public void onSuccess() {
							T.show(mContext,
									mContext.getString(R.string.setSucceed), 1);
							mAddressList = addressList;
							notifyDataSetChanged();
						}

						@Override
						public void onFailure(int arg0, String arg1) {
							T.show(mContext,
									mContext.getString(R.string.setFail), 1);

						}
					});
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return convertView;
	}

	class ViewHolder {
		TextView address, phone;
		TextView setDefault, delete;
	}

}
