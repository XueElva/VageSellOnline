package com.newage.vagetableonlinesell.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.CookBookChildGroup;
import com.newage.vegetableonlinesell.bean.CookBookGroup;
import com.xu.activity.XuBaseActivity;
import com.xu.adapter.CommonAdapter;
import com.xu.adapter.ViewHolder;

public class RecipeChildClassListActivity extends XuBaseActivity {
	ImageView mBack;
	TextView mParentClassName;
	ListView mChildClassLV;
	List<CookBookChildGroup> mChildClassList = new ArrayList<CookBookChildGroup>();
	CommonAdapter<CookBookChildGroup> mAdapter;

	@Override
	public void setLayout() {
		setContentView(R.layout.activity_recipe_childclass_lv);
		mBack = (ImageView) findViewById(R.id.back);
		mParentClassName = (TextView) findViewById(R.id.parentClassName);
		mBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
		CookBookGroup parentClass = (CookBookGroup) getIntent()
				.getSerializableExtra("parentClass");
		mParentClassName.setText(parentClass.getGroupName());
		mChildClassLV=(ListView) findViewById(R.id.childClassLV);
		mChildClassLV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(RecipeChildClassListActivity.this,RecipeListActivity.class);
				intent.putExtra("class", mChildClassList.get(position));
				startActivity(intent);
				
			}
		});
		
		getChildClassList(parentClass);
		mAdapter=new CommonAdapter<CookBookChildGroup>(RecipeChildClassListActivity.this, mChildClassList, R.layout.item_recipe_childclass) {

			@Override
			public void convert(ViewHolder helper, CookBookChildGroup item) {
				helper.setText(R.id.className, item.getGroupName());
				
			}
		};
		mChildClassLV.setAdapter(mAdapter);
	}

	
	//获取子分类
	private void getChildClassList(CookBookGroup parentClass) {
		BmobQuery<CookBookChildGroup> childClassQuery=new BmobQuery<CookBookChildGroup>();
		childClassQuery.setLimit(30);
		childClassQuery.addWhereEqualTo("ParentGroup", parentClass);
		childClassQuery.findObjects(RecipeChildClassListActivity.this, new FindListener<CookBookChildGroup>() {
			
			@Override
			public void onSuccess(List<CookBookChildGroup> list) {
				mChildClassList.clear();
				mChildClassList.addAll(list);
				mAdapter.notifyDataSetChanged();
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
