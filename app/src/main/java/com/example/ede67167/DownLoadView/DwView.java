package com.example.ede67167.DownLoadView;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ede67167.MyUri.ServerUri;
import com.example.ede67167.R;
import com.example.ede67167.RecyclerView.ItemDivider;
import com.example.ede67167.RecyclerView.MyRecyclerView;
import com.example.ede67167.okhttpdemo.okhttpUnity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.List;

import common.MyPullToRefresh.PullToRefreshRecyclerView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by EDE67167 on 2016-01-28.
 */
public class DwView extends android.support.v4.app.Fragment{

    private Context mContext;
    private View mContainer;
    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    private MyRecyclerView mRecyclerView;
    private DwViewAdapter mAdapter;
    private okhttpUnity mOkhttpUnity;
    private int mPictureCount=0;

    @Override
    public void onDestroyView() {
        int i;
        super.onDestroyView();
       // EventBus.getDefault().unregister(this);
        i=0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fresco.initialize(inflater.getContext());
        mPictureCount=0;
        mOkhttpUnity=new okhttpUnity();
        mContext=inflater.getContext();
        mContainer=inflater.inflate(R.layout.p3_dw, null);
        mPullToRefreshRecyclerView = (PullToRefreshRecyclerView)mContainer.findViewById(R.id.id_p3p3_dw);
        mRecyclerView=mPullToRefreshRecyclerView.getRefreshableView();
        mPullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);//anable up pull,and down pull
       /* mRecyclerView= (MyRecyclerView)mContainer.findViewById(R.id.id_p3p3_dw);*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new DwViewAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
        // mRecyclerView.addItemDecoration(new ItemDivider(context, R.drawable.divide_line));
        mRecyclerView.addItemDecoration(new ItemDivider(mContext, R.drawable.divide_line_2));
        long id = Thread.currentThread().getId();
        mRecyclerView.setOnItemScrollChangeListener(new MyRecyclerView.OnItemScrollChangeListener() {
            @Override
            public void onChange(View view, int position) {
                //  mImg.setImageResource(mDatas.get(position));
                int i;
                i = 0;
            }

            ;
        });





        getSumInfo();
       // EventBus.getDefault().register(this);
        mPullToRefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<MyRecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<MyRecyclerView> refreshView) {
                //new GetDataTask().execute();
                RefreshData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<MyRecyclerView> refreshView) {
                RefreshData();
            }


        });
        return mContainer;
    }

    private void getSumInfo()
    {
        mOkhttpUnity.RequestGson(ServerUri.SERVER_PRODUCT_SUM_URI, new okhttpUnity.RequestInterface<List<Product>>()
        {
            @Override
            public void onFailure(String errorMessage) {
                mPullToRefreshRecyclerView.onRefreshComplete();
            }
            @Override
            public void onResponse(List<Product> response) {
                final List<Product> final_response = response;

                Observable<String> myObservable = Observable.create(
                        new Observable.OnSubscribe<String>() {
                            @Override
                            public void call(Subscriber<? super String> sub) {
                                sub.onNext("Hello, world!");
                                sub.onCompleted();
                            }
                        }
                );
                Subscriber<String> mySubscriber = new Subscriber<String>() {
                    @Override
                    public void onNext(String s) { System.out.println(s); }
                    @Override
                    public void onCompleted()
                    {
                        for (int i = 0; i < final_response.size(); i++) {
                            Product data = final_response.get(i);
                            DwData dwData = new DwData();
                            dwData.setTitle(data.getTitle());
                            dwData.setPicStream(null);
                            dwData.setPhotoName(data.getImgSrc());
                            mAdapter.addData(i, dwData);
                        }
                        mPullToRefreshRecyclerView.onRefreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) { }
                };
                myObservable
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mySubscriber);

            }
        });
    }




    private void RefreshData()
    {
        //if(mPictureCount+1>=mAdapter.getData().size())
        {
            Observable.just(1)
                    .subscribeOn(Schedulers.io()) // subscribeOn the I/O thread
                    .observeOn(AndroidSchedulers.mainThread()) // observeOn the UI Thread
            .subscribe(new Subscriber() {
                @Override
                public void onCompleted() {
                    long id = Thread.currentThread().getId();
                    mPictureCount = 0;
                    mAdapter.removeAllData();
                    getSumInfo();
                }

                @Override
                public void onError(Throwable e) {
                    mPullToRefreshRecyclerView.onRefreshComplete();
                }

                @Override
                public void onNext(Object o) {
                       int i,j;
                       i=(Integer)o;
                      j=0;
                }

            });


        }

    }
}


