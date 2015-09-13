package com.newage.vagetableonlinesell.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xu.activity.XuBaseActivity;

// ﬂ≤À¡–±Ì
public class VagetableListActivity extends XuBaseActivity implements
		OnClickListener {
	LinearLayout mBack;

	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_vagetable);
		mBack = (LinearLayout) findViewById(R.id.back);

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
