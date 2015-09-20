package com.newage.vagetableonlinesell.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.CookBookChildGroup;
import com.xu.activity.XuBaseActivity;

public class RecipeListActivity extends XuBaseActivity {
	ImageView mBack, mShoppingCart;
	TextView mClassName;
	CookBookChildGroup mClass;

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_recipe_lv);

		mClass = (CookBookChildGroup) getIntent().getSerializableExtra("class");

		mBack = (ImageView) findViewById(R.id.back);
		mShoppingCart = (ImageView) findViewById(R.id.shoppingCart);
		mClassName = (TextView) findViewById(R.id.className);

		mBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

		mShoppingCart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(RecipeListActivity.this,
						ShoppingCartActivity.class));

			}
		});
		mClassName.setText(mClass.getGroupName());
	}

}
