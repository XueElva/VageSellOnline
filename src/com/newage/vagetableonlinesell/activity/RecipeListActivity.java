package com.newage.vagetableonlinesell.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.CookBook;
import com.newage.vegetableonlinesell.bean.CookBookChildGroup;
import com.newage.vegetableonlinesell.bean.ProductItem;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xu.activity.XuBaseActivity;
import com.xu.adapter.CommonAdapter;
import com.xu.adapter.ViewHolder;
import com.xu.utils.T;

public class RecipeListActivity extends XuBaseActivity {
	ImageView mBack, mShoppingCart;
	TextView mClassName;
	CookBookChildGroup mClass; // 若不为空，按种类搜索
	ProductItem mProduct; // 若不为空，按商品搜索
	PullToRefreshGridView mRecipeGV;
	List<CookBook> mRecipeList = new ArrayList<CookBook>();
	CommonAdapter<CookBook> mAdapter;
	int pageIndex = 0;

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_recipe_lv);

		if (getIntent().getSerializableExtra("class") != null) {
			mClass = (CookBookChildGroup) getIntent().getSerializableExtra(
					"class");
		} else {
			mProduct = (ProductItem) getIntent()
					.getSerializableExtra("product");
		}

		mBack = (ImageView) findViewById(R.id.back);
		mShoppingCart = (ImageView) findViewById(R.id.shoppingCart);
		mClassName = (TextView) findViewById(R.id.className);

		mClassName.setText(mClass == null ? mProduct.getName() : mClass
				.getGroupName());
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

		mRecipeGV = (PullToRefreshGridView) findViewById(R.id.recipeGV);
		mRecipeGV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(RecipeListActivity.this,
						RecipeDetailActivity.class);
				intent.putExtra("recipe", mRecipeList.get(position));
				startActivity(intent);

			}
		});
		mRecipeGV
				.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {

					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<GridView> refreshView) {
						pageIndex = 0;
						mRecipeList.clear();
						getDatas();
					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<GridView> refreshView) {
						getDatas();

					}

				});
		mAdapter = new CommonAdapter<CookBook>(RecipeListActivity.this,
				mRecipeList, R.layout.item_common) {

			@Override
			public void convert(ViewHolder helper, CookBook item, int position) {
				helper.setText(R.id.name, item.getName());
				ImageView img = helper.getView(R.id.img);
				ImageLoader.getInstance().displayImage(
						item.getPic().getFileUrl(RecipeListActivity.this), img);

			}
		};
		mRecipeGV.setAdapter(mAdapter);
		getDatas();

	}

	// 获取菜谱列表
	private void getDatas() {

		if (mClass == null) {
			// 按商品搜

			BmobQuery<CookBook> query = new BmobQuery<CookBook>();
			query.addWhereEqualTo("Needs", new BmobPointer(mProduct));
			query.findObjects(RecipeListActivity.this,
					new FindListener<CookBook>() {

						@Override
						public void onSuccess(List<CookBook> list) {
							if (list.size() > 0) {
								pageIndex++;
								mRecipeList.addAll(list);
								mAdapter.notifyDataSetChanged();
							} else {
								if (pageIndex == 0) {
									mAdapter.notifyDataSetChanged();
								}
								T.showShort(
										RecipeListActivity.this,
										getResources().getString(
												R.string.noMoreData));
							}

						}

						@Override
						public void onError(int arg0, String arg1) {
							// TODO Auto-generated method stub
							T.showShort(RecipeListActivity.this, getResources()
									.getString(R.string.dataLoadError));
						}
					});

		} else {
			// 按分类搜
			BmobQuery<CookBook> query = new BmobQuery<CookBook>();
			query.addWhereEqualTo("GroupTag", new BmobPointer(mClass));
			query.setSkip(pageIndex * 10);
			query.findObjects(RecipeListActivity.this,
					new FindListener<CookBook>() {

						@Override
						public void onSuccess(List<CookBook> list) {
							if (list.size() > 0) {
								pageIndex++;
								mRecipeList.addAll(list);
								mAdapter.notifyDataSetChanged();
							} else {
								if (pageIndex == 0) {
									mAdapter.notifyDataSetChanged();
								}
								T.showShort(
										RecipeListActivity.this,
										getResources().getString(
												R.string.noMoreData));
							}

						}

						@Override
						public void onError(int arg0, String arg1) {
							// TODO Auto-generated method stub
							T.showShort(RecipeListActivity.this, getResources()
									.getString(R.string.dataLoadError));
						}
					});

		}

	}

}
