package com.newage.vagetableonlinesell.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import cn.bmob.v3.BmobUser;
import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.xu.activity.XuBaseActivity;

//ÌØ¼ÛÇø
public class SpecialOfferListActivity extends XuBaseActivity implements
		OnClickListener {
	ImageView mBack;
	ImageView mShoppingCart;

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_specialoffer);
		mBack = (ImageView) findViewById(R.id.back);
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
					SpecialOfferListActivity.this, User.class);
			if (currentUser == null) {
				CommonTools.loginFirst(SpecialOfferListActivity.this);
			} else {
				Intent intent = new Intent(SpecialOfferListActivity.this,
						ShoppingCartActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}

	}
}
