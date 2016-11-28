package com.example.ede67167.DownLoadView;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ede67167.MyUri.ServerUri;
import com.example.ede67167.R;
import com.example.ede67167.RecyclerView.AdapterBase;
import com.facebook.cache.common.CacheKey;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.request.ImageRequest;

import java.io.InputStream;
import java.util.ArrayList;

public class DwViewAdapter extends AdapterBase<DwData,DwViewAdapter.ViewHolder>
{

	public DwViewAdapter(Context context)
	{
		super(context);

		InitData(new ArrayList<DwData>());


	}
	public static class ViewHolder extends RecyclerView.ViewHolder
	{
		SimpleDraweeView mImg;
		TextView mTitleTxt;

		public ViewHolder(View arg0)
		{
			super(arg0);
		}
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	public void updateData(int position,InputStream dataStream) {
		DwData gsonData=getData().get(position);
		gsonData.setPicStream(dataStream);
		getData().set(position, gsonData);
		notifyItemChanged(position);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type)
	{
		    ViewHolder viewHolder;
			View view = mInflater.inflate(R.layout.dw_product_item, viewGroup, false);
			viewHolder = new ViewHolder(view);
			viewHolder.mImg = (SimpleDraweeView) view.findViewById(R.id.product_item_image);
			viewHolder.mTitleTxt = (TextView) view.findViewById(R.id.product_title);
		   return viewHolder;
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int position)
	{

		/*//viewHolder.mImg.setImageResource(getData().get(i));
		if(getData().get(position).getPicStream()!=null)
		{
			Bitmap bitmap=null;
			int byteCount;
			bitmap= MemCache.GetInstance().getBitmapFromMemCache(String.valueOf(position));
			if(bitmap==null)
			{
				bitmap= BmpDecode.DecodePicMatchImageView(viewHolder.mImg, getData().get(position).getPicStream());
				byteCount=bitmap.getByteCount();
				//if (position==0)
				{
					MemCache.GetInstance().addBitmapToMemoryCache("DwView"+String.valueOf(position), bitmap);
				}
			}
			else
			{
				int i;
				i=0;

			}
			viewHolder.mImg.setImageBitmap(bitmap);

		}*/
		String photoNamePath=getData().get(position).getPhotoName();
		photoNamePath= ServerUri.SERVER_PICTURE_ROOT+photoNamePath;
		Uri uri=Uri.parse(photoNamePath);
		//if(!isDownloaded(uri))
		{

			viewHolder.mImg.setImageURI(uri);
		}
		viewHolder.mTitleTxt.setText(getData().get(position).getTitle());
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


	private boolean isDownloaded(Uri loadUri) {
		if (loadUri == null) {
			return false;
		}
		ImageRequest imageRequest = ImageRequest.fromUri(loadUri);
		CacheKey cacheKey = DefaultCacheKeyFactory.getInstance()
				.getEncodedCacheKey(imageRequest);
	/*	try {
			InputStream inputStream = ImagePipelineFactory.getInstance().getMainDiskStorageCache().getResource(cacheKey).openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return ImagePipelineFactory.getInstance().getMainDiskStorageCache().hasKey(cacheKey);
	}


}
