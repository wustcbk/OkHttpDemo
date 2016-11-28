package com.example.ede67167.Product;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ede67167.R;
import com.example.ede67167.RecyclerView.AdapterBase;

import java.util.ArrayList;

import common.Cache.MemCache;
import common.bmp.BmpDecode;

public class ProductAdapter extends AdapterBase<ProductData,ProductAdapter.ViewHolder>
{
	private Context mContext;
	private int mCount=0;
	public ProductAdapter(Context context)
	{
		super(context);
		mContext=context;
		InitData(new ArrayList<ProductData>());


	}
	public static class ViewHolder extends RecyclerView.ViewHolder
	{
		ImageView mImg;
		TextView mTitleTxt;
		TextView mDiscribe;
		TextView mType;
		TextView mPrice;
		public ViewHolder(View arg0)
		{
			super(arg0);
		}
	}

	@Override
	public int getItemViewType(int position) {
		if((position%2)==0)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type)
	{
		ViewHolder viewHolder;
		if(type==0) {
			View view = mInflater.inflate(R.layout.product_item, viewGroup, false);
			viewHolder = new ViewHolder(view);
			viewHolder.mImg = (ImageView) view.findViewById(R.id.product_item_image);
			viewHolder.mTitleTxt = (TextView) view.findViewById(R.id.product_title);
			viewHolder.mDiscribe = (TextView) view.findViewById(R.id.product_describe);
			viewHolder.mType = (TextView) view.findViewById(R.id.product_type);
			viewHolder.mPrice = (TextView) view.findViewById(R.id.product_price);
		}
		else
		{
			View view = mInflater.inflate(R.layout.product_item_2, viewGroup, false);
			viewHolder = new ViewHolder(view);
			viewHolder.mImg = (ImageView) view.findViewById(R.id.product_item_2_image);
		}
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int position)
	{

		//viewHolder.mImg.setImageResource(getData().get(i));
		//if(getData().get(position).getPicStream()!=null)
		{
			Bitmap bitmap=null;
			int byteCount;
			bitmap= MemCache.GetInstance().getBitmapFromMemCache(String.valueOf(position));
			if(bitmap==null)
			{
				//bitmap= BmpDecode.DecodePicMatchImageView(viewHolder.mImg, getData().get(position).getPicStream());
				bitmap= BmpDecode.DecodePicMatchImageView(viewHolder.mImg, getData().get(position).getFileName(),mContext);
				byteCount=bitmap.getByteCount();
				//if (position==0)
				{
					MemCache.GetInstance().addBitmapToMemoryCache("product"+String.valueOf(position), bitmap);
				}
			}
			else
			{
				int i;
				i=0;
				mCount++;
			}
			viewHolder.mImg.setImageBitmap(bitmap);

		}
		if(getItemViewType( position)==0) {
			viewHolder.mTitleTxt.setText(getData().get(position).getTitle());
			viewHolder.mDiscribe.setText(getData().get(position).getDescribe());
			viewHolder.mType.setText(String.valueOf(getData().get(position).getType()));
			viewHolder.mPrice.setText(String.valueOf(getData().get(position).getPrice()));
		}
		else
		{
			int j;
			j=0;
		}
	    if (mOnItemClickLitener != null)
		{
			viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mOnItemClickLitener.onItemClick(viewHolder.itemView, position);
				}
			});

		}

	}


}
