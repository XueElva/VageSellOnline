package com.newage.vagetableonlinesell.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.xu.activity.XuBaseActivity;

//ÌØ¼ÛÇø
public class SpecialOfferListActivity extends XuBaseActivity implements OnClickListener{
	ImageView mBack;
	@Override
	public void setLayout() {
		setContentView(R.layout.activity_specialoffer);
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
