package com.newage.vagetableonlinesell.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.xu.activity.XuBaseActivity;

//蔬菜或水果详情界面
public class ProductDetailActivity extends XuBaseActivity implements OnClickListener{
	ImageView mBack;

	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_detail_product);
		mBack = (ImageView) findViewById(R.id.back);

		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		default:
			break;
		}

	}
}
