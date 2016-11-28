package com.example.ede67167.Product;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.ede67167.R;

/**
 * Created by EDE67167 on 2016-01-22.
 */
public class TopMenu {
    private TextView mTopItem_1;
    private TextView mTopItem_2;
    private Context mContext;
    private View mParentView;
    private Activity mActivity;
    TopMenu(Context context,Activity activity,View parentView)
    {
        TopItemClickListener topItemClickListener=new TopItemClickListener();
        mContext=context;
        mActivity=activity;
        mParentView=parentView;
        mTopItem_1 = (TextView) mParentView.findViewById(R.id.Menu_top_1);
        mTopItem_2 = (TextView) mParentView.findViewById(R.id.Menu_top_2);
        mTopItem_1.setOnClickListener(topItemClickListener);
        mTopItem_2.setOnClickListener(topItemClickListener);

    }

    class TopItemClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
          if(v==mTopItem_1)
          {
                 //new PopUpMenu(mContext,mActivity,v,0);
              new ListViewPopupMenu(mContext,mActivity,v,0);
          }
          else if(v==mTopItem_2)
          {
              //new PopUpMenu(mContext,mActivity, v,1);
              new ListViewPopupMenu(mContext,mActivity,v,1);
          }
        }
    }
}
