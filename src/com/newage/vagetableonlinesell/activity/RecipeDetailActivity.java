package com.newage.vagetableonlinesell.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import cn.bmob.v3.BmobUser;

import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.xu.activity.XuBaseActivity;

//Ê³Æ×ÏêÇé
public class RecipeDetailActivity extends XuBaseActivity implements
		OnClickListener {
	ImageView mBack, mShoppingCart;

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_detail_recipe);
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
					RecipeDetailActivity.this, User.class);
			if (currentUser == null) {
				CommonTools.loginFirst(RecipeDetailActivity.this);
			} else {
				Intent intent = new Intent(RecipeDetailActivity.this,
						ShoppingCartActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}

	}

}
