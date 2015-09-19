package com.newage.vagetableonlinesell.activity;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bmob.v3.BmobUser;

import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.ProductItem;
import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xu.activity.XuBaseActivity;

//蔬菜或水果详情界面
public class ProductDetailActivity extends XuBaseActivity implements
		OnClickListener {
	ProductItem mProduct;
	ImageView mBack, mShoppingCart;
	ImageView mProductImg;
	TextView mProductName, mProductIntroduction;

	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_detail_product);
		mProduct=(ProductItem) getIntent().getSerializableExtra("product");
		initView();
		showData();
	}

	private void showData() {
		//显示商品详情
		mProductName.setText(mProduct.getName());
		if(mProduct.getProDetails()!=null){
			mProductIntroduction.setText(Html.fromHtml(mProduct.getProDetails()));
		}
		ImageLoader.getInstance().displayImage(mProduct.getPicUrl(), mProductImg);
	}

	private void initView() {
		mBack = (ImageView) findViewById(R.id.back);
		mShoppingCart = (ImageView) findViewById(R.id.shoppingCart);
		mProductName=(TextView) findViewById(R.id.productName);
		mProductImg=(ImageView) findViewById(R.id.productImg);
		mProductIntroduction=(TextView) findViewById(R.id.introduction);
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
