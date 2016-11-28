package com.example.ede67167.Product;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.ede67167.R;

import java.util.ArrayList;

/**
 * Created by EDE67167 on 2016-01-22.
 */
public class ListViewPopupMenu {
    private PopupWindow popupWindow;
    final int mPopupMenuWidth=400;
    final  Context mContext;
    ArrayList<String> mData;
    ListViewPopupMenu(Context context,Activity activity,View parent_V,int item)
    {
        View popupWindow_view;
        mContext=context;
        popupWindow_view = activity.getLayoutInflater().inflate(R.layout.menu_popup_layout, null,false);
        popupWindow = new PopupWindow(popupWindow_view,  300, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        //popupWindow = new PopupWindow(popupWindow_view);
        popupWindow.showAsDropDown(parent_V, (parent_V.getLeft()+parent_V.getRight())/2, 0);
        ListView listView = (ListView) popupWindow_view.findViewById(R.id.MenuPopUpListView);
        MyAdapter adapter = new MyAdapter(mContext);
        mData=new ArrayList<String>();
        mData.add("menu 1");
        mData.add("menu 2");
        mData.add("menu 3");
        listView.setAdapter(adapter);
        // 使其聚集
        popupWindow.setFocusable(false);
// 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
//刷新状态（必须刷新否则无效）
        popupWindow.update();
        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String tag;
                // TODO Auto-generated method stub
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;

                }
                return false;
            }
        });
    }
    public final class ViewHolder{

        public TextView menuItem;

    }
    public class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;


        public MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {

                holder=new ViewHolder();
                convertView = mInflater.inflate(R.layout.menu_popup_item, null);
                holder.menuItem = (TextView)convertView.findViewById(R.id.MenuItem);
                convertView.setTag(holder);

            } else {

                holder = (ViewHolder)convertView.getTag();
            }

            holder.menuItem.setText(mData.get(position));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i,j;
                    i=position;
                    j=0;

                }
            });
           // holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));

/*
            holder.viewBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    showInfo();
                }
            });

*/
            return convertView;
        }

    }

}
