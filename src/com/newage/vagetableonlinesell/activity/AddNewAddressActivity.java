package com.newage.vagetableonlinesell.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.Area;
import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.xu.activity.XuBaseActivity;
import com.xu.utils.T;

public class AddNewAddressActivity extends XuBaseActivity {
	Spinner mProvince, mCity, mArea, mLivingArea;
	EditText mConnectPhone, mDetailAddress;
	Button mAdd, mCancel;
	User mCurrentUser;
	ArrayList<Area> mProvinces = new ArrayList<Area>(),
			mCitys = new ArrayList<Area>(), mAreas = new ArrayList<Area>(),
			mLivingAreas = new ArrayList<Area>();

	ArrayList<String> mProvinceList = new ArrayList<String>(),
			mCityList = new ArrayList<String>(),
			mAreaList = new ArrayList<String>(),
			mLivingAreaList = new ArrayList<String>();
	ArrayAdapter<String> mProvinceAdapter, mCityAdapter, mAreaAdapter,
			mLivingAdapter;

	@Override
	public void setLayout() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_newaddress);
		mCurrentUser = BmobUser.getCurrentUser(AddNewAddressActivity.this,
				User.class);
		initView();
		mProvinceAdapter = new ArrayAdapter<String>(AddNewAddressActivity.this,
				R.layout.item_spinner, mProvinceList);
		mProvince.setAdapter(mProvinceAdapter);
		mCityAdapter = new ArrayAdapter<String>(AddNewAddressActivity.this,
				R.layout.item_spinner, mCityList);
		mCity.setAdapter(mCityAdapter);
		mAreaAdapter = new ArrayAdapter<String>(AddNewAddressActivity.this,
				R.layout.item_spinner, mAreaList);
		mArea.setAdapter(mAreaAdapter);
		mLivingAdapter = new ArrayAdapter<String>(AddNewAddressActivity.this,
				R.layout.item_spinner, mLivingAreaList);
		mLivingArea.setAdapter(mLivingAdapter);

		BmobQuery<Area> province = new BmobQuery<Area>();
		province.addWhereEqualTo("level", 1);
		province.findObjects(getApplicationContext(), new FindListener<Area>() {

			@Override
			public void onSuccess(List<Area> arg0) {
				mProvinces.clear();
				mProvinces.addAll(arg0);
				mProvinceList.clear();
				mProvinceList.addAll(CommonTools.getCityName(mProvinces));
				mProvinceAdapter.notifyDataSetChanged();

			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initView() {
		mProvince = (Spinner) findViewById(R.id.province);
		mCity = (Spinner) findViewById(R.id.city);
		mArea = (Spinner) findViewById(R.id.area);
		mLivingArea = (Spinner) findViewById(R.id.livingArea);
		mDetailAddress = (EditText) findViewById(R.id.detailAddress);
		mConnectPhone = (EditText) findViewById(R.id.connectPhone);
		mAdd = (Button) findViewById(R.id.add);
		mCancel = (Button) findViewById(R.id.cancel);
		mProvince.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				BmobQuery<Area> city = new BmobQuery<Area>();
				city.addWhereEqualTo("parentArea", mProvinces.get(position)
						.getObjectId());
				city.findObjects(AddNewAddressActivity.this,
						new FindListener<Area>() {

							@Override
							public void onSuccess(List<Area> arg0) {
								mCitys.clear();
								mCitys.addAll(arg0);
								mCityList.addAll(CommonTools
										.getCityName(mCitys));
								mCityAdapter.notifyDataSetChanged();
							}

							@Override
							public void onError(int arg0, String arg1) {
								// TODO Auto-generated method stub

							}
						});
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		mCity.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				BmobQuery<Area> area = new BmobQuery<Area>();
				area.addWhereEqualTo("parentArea", mCitys.get(position)
						.getObjectId());
				area.findObjects(AddNewAddressActivity.this,
						new FindListener<Area>() {

							@Override
							public void onSuccess(List<Area> arg0) {
								mAreas.clear();
								mAreas.addAll(arg0);
								mAreaList.clear();
								mAreaList.addAll(CommonTools
										.getCityName(mAreas));
								mAreaAdapter.notifyDataSetChanged();

							}

							@Override
							public void onError(int arg0, String arg1) {
								// TODO Auto-generated method stub

							}
						});
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		mArea.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				BmobQuery<Area> livingArea = new BmobQuery<Area>();
				livingArea.addWhereEqualTo("parentArea", mAreas.get(position)
						.getObjectId());
				livingArea.findObjects(AddNewAddressActivity.this,
						new FindListener<Area>() {

							@Override
							public void onSuccess(List<Area> arg0) {
								mLivingAreas.clear();
								mLivingAreas.addAll(arg0);
								mLivingAreaList.clear();
								mLivingAreaList.addAll(CommonTools
										.getCityName(mLivingAreas));
								mLivingAdapter.notifyDataSetChanged();

							}

							@Override
							public void onError(int arg0, String arg1) {
								// TODO Auto-generated method stub

							}
						});

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		mCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				T.show(AddNewAddressActivity.this,
						getResources().getString(R.string.addAddressPlease), 1);
			}
		});

		mAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					if (mDetailAddress.getText().length() == 0) {
						T.show(AddNewAddressActivity.this, getResources()
								.getString(R.string.inputDetailAddress), 1);
					} else if (mConnectPhone.getText().length() == 0) {
						T.show(AddNewAddressActivity.this, getResources()
								.getString(R.string.inputConnectPhone), 1);
					} else {
						// Ìí¼ÓÐÂµØÖ·

						JSONObject newAddress = new JSONObject();
						newAddress.put("detail", mProvince.getSelectedItem()
								.toString()
								+ " "
								+ mCity.getSelectedItem().toString()
								+ " "
								+ mArea.getSelectedItem().toString()
								+ " "
								+ mLivingArea.getSelectedItem().toString()
								+ " " + mDetailAddress.getText().toString());

						newAddress.put(
								"areaId",
								mLivingAreas.get(
										mLivingArea.getSelectedItemPosition())
										.getObjectId());
						newAddress.put("isDefault", "true");
						newAddress.put("phone", mConnectPhone.getText()
								.toString());
						StringBuffer sb = new StringBuffer();
						sb.append("[");
						sb.append(newAddress.toString());
						sb.append("]");

						User currentUser = BmobUser.getCurrentUser(
								AddNewAddressActivity.this, User.class);
						currentUser.setAddress(sb.toString());
						currentUser.update(AddNewAddressActivity.this,
								new UpdateListener() {

									@Override
									public void onSuccess() {
										T.show(AddNewAddressActivity.this,
												getString(R.string.addSucceed),
												1);
										startActivity(new Intent(AddNewAddressActivity.this,MainActivity.class));
										finish();
									}

									@Override
									public void onFailure(int arg0, String arg1) {
										T.show(AddNewAddressActivity.this,
												getString(R.string.addFail), 1);

									}
								});
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			mCurrentUser.logOut(AddNewAddressActivity.this);
			Intent intent = new Intent(AddNewAddressActivity.this,
					LoginActivity.class);
			startActivity(intent);
			finish();

		}
		return super.onKeyDown(keyCode, event);
	}
}
