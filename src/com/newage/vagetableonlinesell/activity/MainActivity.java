package com.newage.vagetableonlinesell.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.CookBookChildGroup;
import com.newage.vegetableonlinesell.bean.CookBookGroup;
import com.newage.vegetableonlinesell.bean.ProGroup;
import com.newage.vegetableonlinesell.bean.ProductItem;
import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.event.FinishEvent;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.xu.activity.XuBaseActivity;
import com.xu.utils.T;

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

		// �����Ʒ����
//		ProGroup group = new ProGroup();
//		group.setObjectId("6uqxGGGe");// ����
//		BmobRelation relation = new BmobRelation();
//		relation.add(group);
//		
//		
//		ProductItem product = new ProductItem();
//		product.setObjectId("a62127a1e9");
//
//		product.setGroupTag(relation);
//
//		ProductItem product2 = new ProductItem();
//		product2.setObjectId("c956eeefc9");
//		product2.setGroupTag(relation);
//
//		ProductItem product3 = new ProductItem();
//		product3.setObjectId("402e665352");
//		product3.setGroupTag(relation);
//
//		ProductItem product4 = new ProductItem();
//		product4.setObjectId("30cc0b8f47");
//		product4.setGroupTag(relation);
//
//		List<BmobObject> productList = new ArrayList<BmobObject>();
//		productList.add(product);
//		productList.add(product2);
//		productList.add(product3);
//		productList.add(product4);
//		new BmobObject().updateBatch(getApplicationContext(), productList, new UpdateListener() {
//			
//			@Override
//			public void onSuccess() {
//				// TODO Auto-generated method stub
//				T.showShort(getApplicationContext(), "���������ɹ�");
//			}
//			
//			@Override
//			public void onFailure(int arg0, String arg1) {
//				// TODO Auto-generated method stub
//				
//			}
//		});

		//��Ӳ�������
		CookBookGroup parentGroup=new CookBookGroup();
		parentGroup.setObjectId("qytCWWWf");
		
		CookBookChildGroup childGroup=new CookBookChildGroup();
		childGroup.setObjectId("Aay30005");
		childGroup.setParentGroup(parentGroup);
		
		CookBookChildGroup childGroup2=new CookBookChildGroup();
		childGroup2.setObjectId("Q3l8777W");
		childGroup2.setParentGroup(parentGroup);
		
		CookBookChildGroup childGroup3=new CookBookChildGroup();
		childGroup3.setObjectId("etCaTTTs");
		childGroup3.setParentGroup(parentGroup);
		
		CookBookChildGroup childGroup4=new CookBookChildGroup();
		childGroup4.setObjectId("NCFe888J");
		childGroup4.setParentGroup(parentGroup);
		
		CookBookChildGroup childGroup5=new CookBookChildGroup();
		childGroup5.setObjectId("voTtGGGb");
		childGroup5.setParentGroup(parentGroup);
		
		CookBookChildGroup childGroup6=new CookBookChildGroup();
		childGroup6.setObjectId("PCRb666D");
		childGroup6.setParentGroup(parentGroup);
		
		List<BmobObject> list=new ArrayList<BmobObject>();
		list.add(childGroup);
		list.add(childGroup2);
		list.add(childGroup3);
		list.add(childGroup4);
		list.add(childGroup5);
		list.add(childGroup6);
		
		 new BmobObject().updateBatch(getApplicationContext(), list, new UpdateListener() {
			
			@Override
			public void onSuccess() {
				T.showShort(getApplicationContext(), "���������ɹ�");
				
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		if (!EventBus.getDefault().isRegistered(this)) {
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
				Intent intent1 = new Intent(MainActivity.this,
						UserActivity.class);
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
					VegetableListActivity.class);
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
