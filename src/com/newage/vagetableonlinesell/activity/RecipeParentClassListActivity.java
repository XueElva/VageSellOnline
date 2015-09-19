package com.newage.vagetableonlinesell.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import cn.bmob.v3.BmobUser;
import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.xu.activity.XuBaseActivity;

public class RecipeParentClassListActivity extends XuBaseActivity implements
		OnClickListener {
	LinearLayout mBack;
	ImageView mShoppingCart;

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_recipe_parentclasses);
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
					RecipeParentClassListActivity.this, User.class);
			if (currentUser == null) {
				CommonTools.loginFirst(RecipeParentClassListActivity.this);
			} else {
				Intent intent = new Intent(RecipeParentClassListActivity.this,
						ShoppingCartActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}

	}

}
