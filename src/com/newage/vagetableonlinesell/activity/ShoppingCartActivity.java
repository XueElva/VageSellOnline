package com.newage.vagetableonlinesell.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.xu.activity.XuBaseActivity;

//¹ºÎï³µ
public class ShoppingCartActivity extends XuBaseActivity implements OnClickListener{
	ImageView mBack;
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_shoppingcart);
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
