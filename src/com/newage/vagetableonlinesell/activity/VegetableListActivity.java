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
import android.widget.LinearLayout;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.adapter.ProductAdapter;
import com.newage.vegetableonlinesell.bean.ProGroup;
import com.newage.vegetableonlinesell.bean.ProductItem;
import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.xu.activity.XuBaseActivity;
import com.xu.utils.T;

//蔬菜列表
public class VegetableListActivity extends XuBaseActivity implements
		OnClickListener {
	LinearLayout mBack;
	ImageView mShoppingCart;
	PullToRefreshGridView mGridView;
	ArrayList<ProductItem> mProductList=new ArrayList<ProductItem>();
	ProductAdapter mAdapter;

	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_vagetable);
		mBack = (LinearLayout) findViewById(R.id.back);
		mShoppingCart = (ImageView) findViewById(R.id.shoppingCart);
		mShoppingCart.setOnClickListener(this);
		mBack.setOnClickListener(this);
		mGridView = (PullToRefreshGridView) findViewById(R.id.vagetableLV);
		mGridView
				.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {

					public void onPullDownToRefresh(
							PullToRefreshBase<GridView> refreshView) {
						// 下拉刷新
						pageIndex = 0;
						mProductList.clear();
						getVegetableList();

					}

					public void onPullUpToRefresh(
							PullToRefreshBase<GridView> refreshView) {
						// 加载更多
						getVegetableList();
					}
				}

				);
		
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(VegetableListActivity.this,ProductDetailActivity.class);
				intent.putExtra("product", mProductList.get(position));
				startActivity(intent);
			}
		});
		mAdapter=new ProductAdapter(mProductList, VegetableListActivity.this);
		mGridView.setAdapter(mAdapter);
		getVegetableList();
	}

	int pageIndex = 0;

	// 获取蔬菜列表
	private void getVegetableList() {
		BmobQuery<ProductItem> productQuery = new BmobQuery<ProductItem>();
		ProGroup group = new ProGroup();
		// 蔬菜
		group.setObjectId("uOlm222C");
		productQuery.addWhereEqualTo("GroupTag", new BmobPointer(group));
		productQuery.setSkip(pageIndex*10);
		// 默认10条数据
		productQuery.findObjects(getApplicationContext(),
				new FindListener<ProductItem>() {

					@Override
					public void onSuccess(List<ProductItem> list) {
						mGridView.onRefreshComplete();
						if (list.size() > 0) {
							pageIndex++;
							mProductList.addAll(list);
							mAdapter.notifyDataSetChanged();
						} else {
							if(pageIndex==0){
								mAdapter.notifyDataSetChanged();
							}
							T.showShort(getApplicationContext(), getResources()
									.getString(R.string.noMoreData));
						}

					}

					@Override
					public void onError(int arg0, String arg1) {
						mGridView.onRefreshComplete();
						T.showShort(getApplicationContext(), getResources()
								.getString(R.string.dataLoadError));
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
					VegetableListActivity.this, User.class);
			if (currentUser == null) {
				CommonTools.loginFirst(VegetableListActivity.this);
			} else {
				Intent intent = new Intent(VegetableListActivity.this,
						ShoppingCartActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}

	}

}
