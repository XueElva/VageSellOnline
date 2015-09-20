package com.newage.vagetableonlinesell.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.CookBookChildGroup;
import com.newage.vegetableonlinesell.bean.CookBookGroup;
import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.newage.vegetableonlinesell.view.ExpandedGridView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xu.activity.XuBaseActivity;
import com.xu.adapter.CommonAdapter;
import com.xu.adapter.ViewHolder;

public class RecipeParentClassListActivity extends XuBaseActivity implements
		OnClickListener {
	LinearLayout mBack;
	ImageView mShoppingCart;
	ListView mRecipeParentClassLV;
	List<CookBookGroup> groupList=new ArrayList<CookBookGroup>();
	CommonAdapter<CookBookGroup> mAdapter;

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_recipe_parentclasses);
		mBack = (LinearLayout) findViewById(R.id.back);
		mShoppingCart = (ImageView) findViewById(R.id.shoppingCart);
		mShoppingCart.setOnClickListener(this);
		mBack.setOnClickListener(this);
		mRecipeParentClassLV = (ListView) findViewById(R.id.recipeLV);
		mAdapter = new CommonAdapter<CookBookGroup>(
				RecipeParentClassListActivity.this, groupList,
				R.layout.item_recipeclass_parentclasslv) {

			@Override
			public void convert(ViewHolder helper, final CookBookGroup item) {
				helper.setText(R.id.className, item.getGroupName());
				ImageView img = helper.getView(R.id.classImg);
				ImageLoader.getInstance().displayImage(
						item.getGroupPic().getFileUrl(
								RecipeParentClassListActivity.this), img);
				LinearLayout moreClass=helper.getView(R.id.parentClass);
				moreClass.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// 更多子分类
						Intent intent=new Intent(RecipeParentClassListActivity.this,RecipeChildClassListActivity.class);
						intent.putExtra("parentClass", item);
						startActivity(intent);
					}
				});

				// 子分类
				final ArrayList<CookBookChildGroup> chidGroupList = new ArrayList<CookBookChildGroup>();
				ExpandedGridView childGroupGV = helper
						.getView(R.id.recipeChildClassGV);
				final CommonAdapter<CookBookChildGroup> mChildAdapter = new CommonAdapter<CookBookChildGroup>(
						RecipeParentClassListActivity.this, chidGroupList,
						R.layout.item_recipeclass_parentclassgv) {

					@Override
					public void convert(ViewHolder helper,
							CookBookChildGroup item) {
						helper.setText(R.id.childClassName1,
								item.getGroupName());
					}
				};
				childGroupGV.setAdapter(mChildAdapter);
				// 获取子分类
				BmobQuery<CookBookChildGroup> childGroupQuery = new BmobQuery<CookBookChildGroup>();
				childGroupQuery.addWhereEqualTo("ParentGroup", item);
				childGroupQuery.setLimit(6);
				childGroupQuery.findObjects(RecipeParentClassListActivity.this,
						new FindListener<CookBookChildGroup>() {

							@Override
							public void onSuccess(List<CookBookChildGroup> list) {
								chidGroupList.clear();
								chidGroupList.addAll(list);
								mChildAdapter.notifyDataSetChanged();
							}

							@Override
							public void onError(int arg0, String arg1) {
								// TODO Auto-generated method stub

							}
						});
			}
		};

		BmobQuery<CookBookGroup> parentClassQuery = new BmobQuery<CookBookGroup>();
		parentClassQuery.setLimit(20);
		parentClassQuery.findObjects(RecipeParentClassListActivity.this,
				new FindListener<CookBookGroup>() {

					@Override
					public void onError(int arg0, String arg1) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(List<CookBookGroup> list) {
						// TODO Auto-generated method stub
						groupList.addAll(list);
						mRecipeParentClassLV.setAdapter(mAdapter);
					}
				});
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
