package com.example.ede67167.Product;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ede67167.R;
import com.example.ede67167.RecyclerView.AdapterBase;
import com.example.ede67167.RecyclerView.ItemDivider;
import com.example.ede67167.RecyclerView.MyRecyclerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import common.MyPullToRefresh.PullToRefreshRecyclerView;

/**
 * Created by EDE67167 on 2016-01-20.
 */
public class ProductView extends android.support.v4.app.Fragment{
    private  MyRecyclerView mRecyclerView;
    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    private ProductAdapter mAdapter;
    private View mContainer;
    private Context mContext;
    private Activity mActivity;
/*
   // public ProductView(View parentView,Context context,Activity activity)
    public ProductView(Context context,Activity activity)
    {
        super();
        mContext=context;
        mActivity=activity;

    }
*/
static public android.support.v4.app.Fragment newInstance(String s) {
    android.support.v4.app.Fragment newFragment = new ProductView();
    Bundle bundle = new Bundle();
    bundle.putString("hello", s);

    newFragment.setArguments(bundle);
    //bundle还可以在每个标签里传送数据
    return newFragment;

}

    @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        int i;
        mContext=inflater.getContext();
        mContainer=inflater.inflate(R.layout.p2_product, null);
        mPullToRefreshRecyclerView = (PullToRefreshRecyclerView)mContainer.findViewById(R.id.product_view_id);
        mPullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);//anable up pull,and down pull
        mRecyclerView=mPullToRefreshRecyclerView.getRefreshableView();
       // mRecyclerView= (MyRecyclerView)mContainer.findViewById(R.id.product_view_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ProductAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
       // mRecyclerView.addItemDecoration(new ItemDivider(context, R.drawable.divide_line));
        mRecyclerView.addItemDecoration(new ItemDivider(mContext, R.drawable.divide_line_2));

        mRecyclerView.setOnItemScrollChangeListener(new MyRecyclerView.OnItemScrollChangeListener() {
            @Override
            public void onChange(View view, int position) {

            }


        });


                mAdapter.setOnItemClickLitener(new AdapterBase.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(mContext, position + "", Toast.LENGTH_SHORT).show();
                        //     mImg.setImageResource(mDatas.get(position));
                    }
                });

   //    new TopMenu(mContext,mActivity,mContainer);
       /* mPullToRefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<MyRecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<MyRecyclerView> refreshView) {
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<MyRecyclerView> refreshView) {
                new GetDataTask().execute();
            }


        });*/
        ProductData productData=new ProductData();
        productData.setTitle("Title_1");
        productData.setDescribe("Title_1 des describe");
        productData.setType(1);
        productData.setPrice(10);
        AssetManager assets = mContext.getAssets();
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int totalMem=(int) (Runtime.getRuntime().totalMemory() / 1024);
        int freeMem=(int) (Runtime.getRuntime().freeMemory() / 1024);
        for(i=0;i<12;i++) {
            productData.setFileName("files/a1.jpg");
            mAdapter.getData().add(0,productData);

        }

        return mContainer;
    }
    private class GetDataTask extends AsyncTask<Void, Void, String>
    {

        @Override
        protected String doInBackground(Void... params) {
            return "ok";
        }
        @Override
        protected void onPostExecute(String result) {
            // Call onRefreshComplete when the list has been refreshed.
         //   mPullToRefreshRecyclerView.onRefreshComplete();
            super.onPostExecute(result);
        }
    }

}
