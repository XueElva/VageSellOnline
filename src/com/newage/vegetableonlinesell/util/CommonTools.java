package com.newage.vegetableonlinesell.util;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newage.vagetableonlinesell.activity.LoginActivity;
import com.newage.vagetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.event.FinishEvent;

import de.greenrobot.event.EventBus;

public class CommonTools {
	/**
	 * 提示请先登录
	 * 
	 * @param context
	 */

	public static void loginFirst(final Context context) {
		View view = View.inflate(context, R.layout.dialog_loginprompt, null);
		final Dialog dialog = new Dialog(context);
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
	 * 得到自定义的progressDialog
	 * 
	 * @param context
	 * @param msg
	 * @return
	 */
	public static Dialog LoadingDialog;

	public static Dialog createLoadingDialog(Context context) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		// main.xml中的ImageView
		ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
		TextView tipTextView = (TextView) v.findViewById(R.id.content);// 提示文字
		tipTextView.setText(context.getResources().getString(R.string.loading));
		// 加载动画
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
				context, R.anim.loading_animation);
		// 使用ImageView显示动画
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		tipTextView.setText(context.getResources().getString(R.string.loading));// 设置加载信息

		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog

		loadingDialog.setCancelable(true);// 不可以用“返回键”取�?
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
		LoadingDialog = loadingDialog;
		return loadingDialog;
	}

	public static Dialog createLoadingDialogWithTilte(String title,
			Context context) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		// main.xml中的ImageView
		ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
		TextView tipTextView = (TextView) v.findViewById(R.id.content);// 提示文字
		tipTextView.setText(title);
		// 加载动画
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
				context, R.anim.loading_animation);
		// 使用ImageView显示动画
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		tipTextView.setText(context.getResources().getString(R.string.loading));// 设置加载信息

		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog

		loadingDialog.setCancelable(true);// 不可以用“返回键”取�?
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
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
	 * 缩小图片到指定大小
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
	 * 生成图片名称的json [{"imgName":""},{"imgName":""},{"imgName":""}]
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
	 * 得到联系人列表 JSON串
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
