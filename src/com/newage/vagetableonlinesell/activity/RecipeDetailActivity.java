package com.newage.vagetableonlinesell.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobUser;

import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.CookBook;
import com.newage.vegetableonlinesell.bean.User;
import com.newage.vegetableonlinesell.util.CommonTools;
import com.newage.vegetableonlinesell.view.ExpandedListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xu.activity.XuBaseActivity;
import com.xu.adapter.CommonAdapter;
import com.xu.adapter.ViewHolder;

//食谱详情
public class RecipeDetailActivity extends XuBaseActivity implements
		OnClickListener {
	ImageView mBack, mShoppingCart;
	TextView mRecipeName, mIntroduction;
	ImageView mRecipeImg;
	CookBook mRecipe;
	// 选择
	LinearLayout mTabMaterial, mTabProcess;
	ExpandedListView mMaterialLV, mProcessLV;
	CommonAdapter<JSONObject> mProcessAdapter;
	List<JSONObject> mProcessList = new ArrayList<JSONObject>();

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_detail_recipe);
		mRecipe = (CookBook) getIntent().getSerializableExtra("recipe");
		initView();
		showData();
	}

	private void showData() {
		mRecipeName.setText(mRecipe.getName());
		ImageLoader.getInstance().displayImage(
				mRecipe.getPic().getFileUrl(RecipeDetailActivity.this),
				mRecipeImg);

		if (mRecipe.getIntroduction() != null) {
			mIntroduction.setText(mRecipe.getIntroduction());
		}

		if (mRecipe.getSteps() != null) {
			try {
				mProcessList.addAll(getProcessList(new JSONArray(mRecipe
						.getSteps())));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		mProcessAdapter = new CommonAdapter<JSONObject>(
				RecipeDetailActivity.this, mProcessList, R.layout.item_process) {

			@Override
			public void convert(ViewHolder helper, JSONObject item,int position) {
				try {
					int step=position+1;
					helper.setText(R.id.text,step+"."+item.getString("step"));
					if(item.getString("imgUrl")!=null && !item.getString("imgUrl").equals("")){
						ImageLoader.getInstance().displayImage(item.getString("imgUrl"), (ImageView) helper.getView(R.id.img));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		
		mProcessLV.setAdapter(mProcessAdapter);
	}

	private ArrayList<JSONObject> getProcessList(JSONArray array) {
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		for (int i = 0; i < array.length(); i++) {
			try {
				list.add(array.getJSONObject(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

	private void initView() {
		mBack = (ImageView) findViewById(R.id.back);
		mShoppingCart = (ImageView) findViewById(R.id.shoppingCart);
		mRecipeName = (TextView) findViewById(R.id.recipeName);
		mRecipeImg = (ImageView) findViewById(R.id.Img);
		mIntroduction = (TextView) findViewById(R.id.introduction);
		mTabMaterial = (LinearLayout) findViewById(R.id.tab_material);
		mTabProcess = (LinearLayout) findViewById(R.id.tab_process);
		mMaterialLV = (ExpandedListView) findViewById(R.id.materialLV);
		mProcessLV = (ExpandedListView) findViewById(R.id.processLV);

		mTabMaterial.setSelected(true);
		mProcessLV.setVisibility(View.INVISIBLE);

		mTabMaterial.setOnClickListener(this);
		mTabProcess.setOnClickListener(this);
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
					RecipeDetailActivity.this, User.class);
			if (currentUser == null) {
				CommonTools.loginFirst(RecipeDetailActivity.this);
			} else {
				Intent intent = new Intent(RecipeDetailActivity.this,
						ShoppingCartActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.tab_material: // 食材
			mTabMaterial.setSelected(true);
			mTabProcess.setSelected(false);
			mMaterialLV.setVisibility(View.VISIBLE);
			mProcessLV.setVisibility(View.INVISIBLE);
			break;
		case R.id.tab_process: // 流程
			mTabMaterial.setSelected(false);
			mTabProcess.setSelected(true);
			mMaterialLV.setVisibility(View.INVISIBLE);
			mProcessLV.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}

	}

}
