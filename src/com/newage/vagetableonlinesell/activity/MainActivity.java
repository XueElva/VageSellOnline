package com.newage.vagetableonlinesell.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.event.FinishEvent;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.xu.activity.XuBaseActivity;

import de.greenrobot.event.EventBus;

public class MainActivity extends XuBaseActivity implements OnClickListener {
	ImageView mUser, mShoppinCart;
	GridView mRecommendGV; // �����Ƽ��б�
	LinearLayout mTabRecipe, mTabProduct; // �Ƽ�ʳ�ס��Ƽ��߹�
	ImageView mItemRecipe, mItemVagetable, mItemFruit, mItemSpecialOffer;

	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_main);

		Bmob.initialize(this, "c5e8ef95cc618067adbaa736ba2bf982");
		initView();
		
		if(!EventBus.getDefault().isRegistered(this)){
			EventBus.getDefault().register(this);
		}

	}
	
	public void onEvent(FinishEvent event) {
		finish();
	}

	private void initView() {
		mUser = (ImageView) findViewById(R.id.user);
		mShoppinCart = (ImageView) findViewById(R.id.shoppingCart);
		mRecommendGV = (GridView) findViewById(R.id.GV_Recommend);
		mTabRecipe = (LinearLayout) findViewById(R.id.tab_recipe);
		mTabProduct = (LinearLayout) findViewById(R.id.tab_vaget);
		mItemRecipe = (ImageView) findViewById(R.id.itemRecipe);
		mItemVagetable = (ImageView) findViewById(R.id.itemVagetable);
		mItemFruit = (ImageView) findViewById(R.id.itemFruit);
		mItemSpecialOffer = (ImageView) findViewById(R.id.itemSpecialOffer);

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
		case R.id.user: // �û���ҳ
			User currentUser1 = BmobUser.getCurrentUser(MainActivity.this,
					User.class);
			if (currentUser1 == null) {
				CommonTools.loginFirst(MainActivity.this);
			} else {
				Intent intent1 = new Intent(MainActivity.this, UserActivity.class);
				startActivity(intent1);
			}
			
			break;
		case R.id.shoppingCart: // ���ﳵ

			User currentUser = BmobUser.getCurrentUser(MainActivity.this,
					User.class);
			if (currentUser == null) {
				CommonTools.loginFirst(MainActivity.this);
			} else {
				Intent intent = new Intent(MainActivity.this,
						ShoppingCartActivity.class);
				startActivity(intent);
			}

			break;
		case R.id.tab_recipe:// �Ƽ�ʳ��
			mTabRecipe.setSelected(true);
			mTabProduct.setSelected(false);
			break;
		case R.id.tab_vaget: // �Ƽ��߹�
			mTabProduct.setSelected(true);
			mTabRecipe.setSelected(false);
			break;
		case R.id.itemRecipe: // ʳ�׷���
			Intent intent2 = new Intent(MainActivity.this,
					RecipeParentClassListActivity.class);
			startActivity(intent2);
			break;
		case R.id.itemVagetable: // �߲��б�
			Intent intent3 = new Intent(MainActivity.this,
					VagetableListActivity.class);
			startActivity(intent3);
			break;
		case R.id.itemFruit:// ˮ���б�
			Intent intent4 = new Intent(MainActivity.this,
					FruitListActivity.class);
			startActivity(intent4);
			break;
		case R.id.itemSpecialOffer:// �ؼ���
			Intent intent5 = new Intent(MainActivity.this,
					SpecialOfferListActivity.class);
			startActivity(intent5);
			break;
		default:
			break;
		}
	}

}
