package com.newage.vegetableonlinesell.application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		// ����Ĭ�ϵ�ImageLoader���ò���
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration
				.createDefault(this);

		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(configuration);
		super.onCreate();
	}
}