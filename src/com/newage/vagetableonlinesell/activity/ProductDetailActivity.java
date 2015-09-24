package com.newage.vagetableonlinesell.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;

import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.CookBook;
import com.newage.vegetableonlinesell.bean.ProductItem;
import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.newage.vegetableonlinesell.view.ExpandedGridView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xu.activity.XuBaseActivity;
import com.xu.adapter.CommonAdapter;
import com.xu.adapter.ViewHolder;

//蔬菜或水果详情界面
public class ProductDetailActivity extends XuBaseActivity implements
		OnClickListener {
	ProductItem mProduct;
	ImageView mBack, mShoppingCart;
	ImageView mProductImg;
	TextView mProductName, mProductIntroduction;
	ExpandedGridView mRecipeGV;
	ArrayList<CookBook> mRecipeList;
	CommonAdapter mAdapter;
    TextView mMoreRecipe;
	@Override
	public void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_detail_product);
		mProduct = (ProductItem) getIntent().getSerializableExtra("product");
		initView();
		showData();
	}

	private void showData() {
		// 显示商品详情
		mProductName.setText(mProduct.getName());
		if (mProduct.getProDetails() != null) {
			mProductIntroduction
					.setText(Html.fromHtml(mProduct.getProDetails()));
		}
		ImageLoader.getInstance().displayImage(mProduct.getPicUrl(),
				mProductImg);

		// 获取推荐食谱
		getRecipe();
	}

	/**
	 * 根据商品获取推荐食谱
	 */
	private void getRecipe() {
		BmobQuery<CookBook> query = new BmobQuery<CookBook>();
		query.addWhereEqualTo("Needs", new BmobPointer(mProduct));
		query.findObjects(ProductDetailActivity.this,
				new FindListener<CookBook>() {

					@Override
					public void onSuccess(List<CookBook> list) {
						mRecipeList = (ArrayList<CookBook>) list;
						mAdapter = new CommonAdapter<CookBook>(
								ProductDetailActivity.this, mRecipeList,
								R.layout.item_common) {

							@Override
							public void convert(ViewHolder helper,
									CookBook item, int position) {
								helper.setText(R.id.name, item.getName());
								ImageView img = helper.getView(R.id.img);
								ImageLoader.getInstance().displayImage(
										item.getPic().getFileUrl(
												ProductDetailActivity.this), img);

							}
						};
						mRecipeGV.setAdapter(mAdapter);
					}

					@Override
					public void onError(int arg0, String arg1) {
						// TODO Auto-generated method stub

					}
				});

	}

	private void initView() {
		mBack = (ImageView) findViewById(R.id.back);
		mShoppingCart = (ImageView) findViewById(R.id.shoppingCart);
		mProductName = (TextView) findViewById(R.id.productName);
		mProductImg = (ImageView) findViewById(R.id.productImg);
		mProductIntroduction = (TextView) findViewById(R.id.introduction);
		mRecipeGV=(ExpandedGridView) findViewById(R.id.recipeGV);
		mMoreRecipe=(TextView) findViewById(R.id.more);
		mBack.setOnClickListener(this);
		mShoppingCart.setOnClickListener(this);
		mMoreRecipe.setOnClickListener(this);
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
		case R.id.more:
			Intent intent=new Intent(ProductDetailActivity.this,RecipeListActivity.class);
			intent.putExtra("product", mProduct);
			startActivity(intent);
			break;
		default:
			break;
		}

	}
}
