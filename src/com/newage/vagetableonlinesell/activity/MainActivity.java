package com.newage.vagetableonlinesell.activity;


import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xu.activity.XuBaseActivity;

public class MainActivity extends XuBaseActivity implements OnClickListener{
ImageView mUser,mShoppinCart;
GridView mRecommendGV; //今日推荐列表
TextView mTabRecipe,mTabProduct; //推荐食谱、推荐蔬果
ImageView mItemRecipe,mItemVagetable,mItemFruit,mItemSpecialOffer;

	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_main);
		initView();
		
	}


	private void initView() {
		mUser=(ImageView) findViewById(R.id.user);
		mShoppinCart=(ImageView) findViewById(R.id.shoppingCart);
		mRecommendGV=(GridView) findViewById(R.id.GV_Recommend);
		mTabRecipe=(TextView) findViewById(R.id.tab_recipe);
		mTabProduct=(TextView) findViewById(R.id.tab_vaget);
		mItemRecipe=(ImageView) findViewById(R.id.itemRecipe);
		mItemVagetable=(ImageView) findViewById(R.id.itemVagetable);
		mItemFruit=(ImageView) findViewById(R.id.itemFruit);
		mItemSpecialOffer=(ImageView) findViewById(R.id.itemSpecialOffer);
		
		mUser.setOnClickListener(this);
		mShoppinCart.setOnClickListener(this);
		mTabRecipe.setOnClickListener(this);
		mTabProduct.setOnClickListener(this);
		mItemRecipe.setOnClickListener(this);
		mItemFruit.setOnClickListener(this);
		mItemSpecialOffer.setOnClickListener(this);
		mItemVagetable.setOnClickListener(this);
		mTabRecipe.setSelected(true);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.user: //用户主页
			Intent intent1=new Intent(MainActivity.this,UserActivity.class);
			startActivity(intent1);
			break;
		case R.id.shoppingCart: //购物车
//			toActivity(ShoppingCartActivity.class, false);
			Intent intent=new Intent(MainActivity.this,ShoppingCartActivity.class);
			startActivity(intent);
			break;
		case R.id.tab_recipe://推荐食谱
			mTabRecipe.setSelected(true);
			mTabProduct.setSelected(false);
			break;
		case R.id.tab_vaget: //推荐蔬果
			mTabProduct.setSelected(true);
			mTabRecipe.setSelected(false);
			break;
		case R.id.itemRecipe: //食谱
//			Intent intent2=new Intent(MainActivity.this,RecipeActivity.class);
//			startActivity(intent2);
			break;
		case R.id.itemVagetable: 
			Intent intent3=new Intent(MainActivity.this,VagetableListActivity.class);
			startActivity(intent3);
			break;
		case R.id.itemFruit:
			Intent intent4=new Intent(MainActivity.this,FruitListActivity.class);
			startActivity(intent4);
			break;
		case R.id.itemSpecialOffer:
			Intent intent5=new Intent(MainActivity.this,SpecialOfferListActivity.class);
			startActivity(intent5);
			break;
		default:
			break;
		}
	}


	
	
	
}
