package com.newage.vegetableonlinesell.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newage.vagetableonlinesell.activity.LoginActivity;
import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.Area;
import com.newage.vegetableonlinesell.event.FinishEvent;

import de.greenrobot.event.EventBus;

public class CommonTools {

	/**
	 * 提取地区名字
	 * @param areas
	 * @return
	 */
	public static ArrayList<String> getCityName(ArrayList<Area> areas){
		ArrayList<String> areaNames=new ArrayList<String>();
		for (int i = 0; i < areas.size(); i++) {
			areaNames.add(areas.get(i).getName());
		}
		return areaNames;
		
	}
	
	/**
	 * 返回默认收货地址
	 * @param array
	 * @return
	 */
	public static JSONObject getDefaultAddress(JSONArray array){
		for (int i = 0; i < array.length(); i++) {
			try {
				JSONObject obj=array.getJSONObject(i);
				if(obj.getString("isDefault").equals("true")){
					return obj;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}
	/**
	 * 提示先登录
	 * 
	 * @param context
	 */

	public static void loginFirst(final Context context) {
		View view = View.inflate(context, R.layout.dialog_loginprompt, null);
		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(view);
		TextView login, cancel;
		login = (TextView) view.findViewById(R.id.login);
		cancel = (TextView) view.findViewById(R.id.cancel);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				context.startActivity(new Intent(context, LoginActivity.class));
				EventBus.getDefault().post(new FinishEvent());
			}
		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.cancel();
			}
		});
		dialog.show();
	}

	/**
	 * 通用加载框progressDialog
	 * 
	 * @param context
	 * @param msg
	 * @return
	 */
	public static Dialog LoadingDialog;

	public static Dialog createLoadingDialog(Context context) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.dialog_loading, null);// 寰楀埌鍔犺浇view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 鍔犺浇甯冨眬
		// main.xml涓殑ImageView
		ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
		TextView tipTextView = (TextView) v.findViewById(R.id.content);// 鎻愮ず鏂囧瓧
		tipTextView.setText(context.getResources().getString(R.string.loading));
		// 鍔犺浇鍔ㄧ敾
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
				context, R.anim.loading_animation);
		// 浣跨敤ImageView鏄剧ず鍔ㄧ敾
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		tipTextView.setText(context.getResources().getString(R.string.loading));// 璁剧疆鍔犺浇淇℃伅

		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 鍒涘缓鑷畾涔夋牱寮廳ialog

		loadingDialog.setCancelable(true);// 涓嶅彲浠ョ敤鈥滆繑鍥為敭鈥濆彇锟�?
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));// 璁剧疆甯冨眬
		LoadingDialog = loadingDialog;
		return loadingDialog;
	}

	public static Dialog createLoadingDialogWithTilte(String title,
			Context context) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.dialog_loading, null);// 寰楀埌鍔犺浇view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 鍔犺浇甯冨眬
		// main.xml涓殑ImageView
		ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
		TextView tipTextView = (TextView) v.findViewById(R.id.content);// 鎻愮ず鏂囧瓧
		tipTextView.setText(title);
		// 鍔犺浇鍔ㄧ敾
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
				context, R.anim.loading_animation);
		// 浣跨敤ImageView鏄剧ず鍔ㄧ敾
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		tipTextView.setText(context.getResources().getString(R.string.loading));// 璁剧疆鍔犺浇淇℃伅

		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 鍒涘缓鑷畾涔夋牱寮廳ialog

		loadingDialog.setCancelable(true);// 涓嶅彲浠ョ敤鈥滆繑鍥為敭鈥濆彇锟�?
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));// 璁剧疆甯冨眬
		LoadingDialog = loadingDialog;
		return loadingDialog;
	}

	public static void cancleDialog() {
		try {
			LoadingDialog.cancel();
		} catch (Exception e) {
		}
	}

	/**
	 * 压缩图片
	 * 
	 * @param bitmap
	 * @return
	 */
	private Bitmap scaleBitmap(Bitmap bitmap, int size) {
		Bitmap scaledBitmap;
		float scaledHeightPercent = size / bitmap.getHeight();
		float scaledWidthPercent = size / bitmap.getWidth();
		Matrix matrix = new Matrix();
		matrix.postScale(scaledWidthPercent, scaledHeightPercent);

		scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		return scaledBitmap;
	}

	/**
	 *  [{"imgName":""},{"imgName":""},{"imgName":""}]
	 * 
	 * @param imgList
	 * @return
	 */
	public static String getImgListJSON(
			ArrayList<HashMap<String, String>> imgList) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < imgList.size(); i++) {
			HashMap<String, String> img = imgList.get(i);

			sb.append("{\"imgName\":\"" + img.get("imgName")
					+ "\",\"imgUrl\":\"" + img.get("imgUrl") + "\"}");
			if (i != (imgList.size() - 1)) {
				sb.append(",");
			}
		}
		sb.append("]");

		return sb.toString();
	}

	/**
	 * 
	 * [{"name":"","phone":""},{"name":"","phone":""},{"name":"","phone":""}]
	 * 
	 * @return
	 */
	public static String getConnectInfoJSON(
			ArrayList<HashMap<String, String>> connectInfoList) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");

		for (int i = 0; i < connectInfoList.size(); i++) {
			sb.append("{\"name\":\"" + connectInfoList.get(i).get("name")
					+ "\",\"phone\":\"" + connectInfoList.get(i).get("phone")
					+ "\"}");
			if (i != (connectInfoList.size() - 1)) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
