package com.newage.vagetableonlinesell.activity;

import java.io.File;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.xu.activity.XuBaseActivity;

//水果列表
public class FruitListActivity extends XuBaseActivity implements
		OnClickListener {
	LinearLayout mBack;
	ImageView mShoppingCart;

	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_fruit);
		mBack = (LinearLayout) findViewById(R.id.back);
		mShoppingCart=(ImageView) findViewById(R.id.shoppingCart);
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
					FruitListActivity.this, User.class);
			if (currentUser == null) {
				CommonTools.loginFirst(FruitListActivity.this);
			} else {
				Intent intent = new Intent(FruitListActivity.this,
						ShoppingCartActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}

	}

}
