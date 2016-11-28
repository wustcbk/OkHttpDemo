package com.example.ede67167.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by EDE67167 on 2015-11-30.
 */
public class MyRecyclerView extends RecyclerView {
    private View mCurrentView;

    private OnItemScrollChangeListener mItemScrollChangeListener;
    public void setOnItemScrollChangeListener(
            OnItemScrollChangeListener mItemScrollChangeListener)
    {
        this.mItemScrollChangeListener = mItemScrollChangeListener;
    }
    public interface OnItemScrollChangeListener
    {
        void onChange(View view, int position);
    }
    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        OnScrollListener list=new OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int i;
                super.onScrollStateChanged(recyclerView, newState);
                i=0;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int i;
                super.onScrolled(recyclerView, dx, dy);
                View newView = getChildAt(0);

                if (mItemScrollChangeListener != null)
                {
                    if (newView != null && newView != mCurrentView)
                    {
                        mCurrentView = newView ;
                        mItemScrollChangeListener.onChange(mCurrentView, getChildAdapterPosition(mCurrentView));

                    }
                }
            }
        };
        // TODO Auto-generated constructor stub
        this.addOnScrollListener(list);
    }




}
