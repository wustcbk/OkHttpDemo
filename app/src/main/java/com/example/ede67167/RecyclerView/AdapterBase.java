package com.example.ede67167.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by EDE67167 on 2016-01-20.
 */
public abstract class AdapterBase<T_Gson,T_hoder extends RecyclerView.ViewHolder >  extends RecyclerView.Adapter<T_hoder>{
    protected LayoutInflater mInflater;
    private   ArrayList<T_Gson> mDatas=null;
    protected  Context mContext;
    protected OnItemClickLitener mOnItemClickLitener;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public AdapterBase(Context context)
    {
        mContext=context;
        mInflater = LayoutInflater.from(context);


    }
    public void InitData( ArrayList<T_Gson> datats)
    {
        mDatas = datats;
    }
    public void addData(int position,T_Gson gsonData) {
        mDatas.add(position, gsonData);
        notifyItemInserted(position);
    }


    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);

    }

    public void removeAllData() {
        int dataSize=getItemCount();
        mDatas.clear();
        notifyItemRangeRemoved(0, dataSize);

       // notifyItemRangeChanged(0,dataSize );
       // notifyDatSetChanged();
    }

    @Override
    public int getItemCount()
    {
        int dataSize=mDatas.size();
        return dataSize;
    }

    public ArrayList<T_Gson> getData()
    {
        return mDatas;
    }
}
