package com.newage.vagetableonlinesell.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import cn.bmob.v3.BmobUser;

import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.xu.activity.XuBaseActivity;

//蔬菜或水果详情界面
public class ProductDetailActivity extends XuBaseActivity implements
		OnClickListener {
	ImageView mBack, mShoppingCart;

	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_detail_product);
		mBack = (ImageView) findViewById(R.id.back);
		mShoppingCart = (ImageView) findViewById(R.id.shoppingCart);
		mBack.setOnClickListener(this);
		mShoppingCart.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.shoppingCart:
			User currentUser = BmobUser.getCurrentUser(
					ProductDetailActivity.this, User.class);
			if (currentUser == null) {
				CommonTools.loginFirst(ProductDetailActivity.this);
			} else {
				Intent intent = new Intent(ProductDetailActivity.this,
						ShoppingCartActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}

	}
}
