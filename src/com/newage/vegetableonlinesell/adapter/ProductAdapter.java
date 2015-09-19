package com.newage.vegetableonlinesell.adapter;

import java.util.ArrayList;



import com.newage.vegetableonlinesell.activity.R;
import com.newage.vegetableonlinesell.bean.ProductItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductAdapter extends BaseAdapter {
	ArrayList<ProductItem> mProductList;
	Context mContext;

	public ProductAdapter(ArrayList<ProductItem> list, Context context) {
		this.mProductList = list;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mProductList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mProductList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh=null;
		if(convertView==null){
			convertView=View.inflate(mContext, R.layout.item_common, null);
			vh=new ViewHolder();
			vh.img=(ImageView) convertView.findViewById(R.id.img);
			vh.productName=(TextView) convertView.findViewById(R.id.name);
			vh.collect=(ImageView) convertView.findViewById(R.id.collect);
			vh.price=(TextView) convertView.findViewById(R.id.price);
			
			convertView.setTag(vh);
		}else{
			vh=(ViewHolder) convertView.getTag();
		}
		ImageLoader.getInstance().displayImage(mProductList.get(position).getPicUrl(), vh.img);
		vh.productName.setText(mProductList.get(position).getName());
		return convertView;
	}

	class ViewHolder {
		ImageView img, collect;
		TextView productName, price;
	}

}
