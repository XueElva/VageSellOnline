package com.newage.vagetableonlinesell.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.bmob.v3.BmobUser;

import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.xu.activity.XuBaseActivity;

// ﬂ≤À¡–±Ì
public class VagetableListActivity extends XuBaseActivity implements
		OnClickListener {
	LinearLayout mBack;
	ImageView mShoppingCart;

	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_vagetable);
		mBack = (LinearLayout) findViewById(R.id.back);
		mShoppingCart = (ImageView) findViewById(R.id.shoppingCart);
		mShoppingCart.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.shoppingCart:
			User currentUser = BmobUser.getCurrentUser(
					VagetableListActivity.this, User.class);
			if (currentUser == null) {
				CommonTools.loginFirst(VagetableListActivity.this);
			} else {
				Intent intent = new Intent(VagetableListActivity.this,
						ShoppingCartActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}

	}

}
