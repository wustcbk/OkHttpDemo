package com.example.ede67167.Product;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.ede67167.R;

/**
 * Created by EDE67167 on 2016-01-22.
 */
public class PopUpMenu {
    private PopupWindow popupWindow;
    final int mPopupMenuWidth=400;
    final  Context mContext;
    PopUpMenu(Context context,Activity activity,View parent_V,int item)
    {
        View popupWindow_view;
        mContext=context;
        // 获取自定义布局文件activity_popupwindow_left.xml的视图
        if(0==item)
        {
             popupWindow_view = activity.getLayoutInflater().inflate(R.layout.product_top_popup_menu_1, null,false);
        }
        else
        {
            popupWindow_view = activity.getLayoutInflater().inflate(R.layout.product_top_popup_menu_2, null,false);
        }

        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
       // popupWindow = new PopupWindow(popupWindow_view, 200, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow = new PopupWindow(popupWindow_view,  mPopupMenuWidth, LinearLayout.LayoutParams.MATCH_PARENT, true);
        // 设置动画效果
     //   popupWindow.setAnimationStyle(R.style.AnimationFade);
        // 这里是位置显示方式,在屏幕的左侧
        //View menuBar=parent_V.findViewById(R.id.Menu_bar);
        //popupWindow.showAtLocation(menuBar, Gravity.BOTTOM, 0, 0);



        popupWindow.showAsDropDown(parent_V, (parent_V.getLeft()+parent_V.getRight())/2, 0);
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
          /*
                    tag=(String)v.getTag();
                    if(tag.equals("menu_1_1"))
                    {
                        Toast.makeText(mContext, "the tag is" + tag, Toast.LENGTH_SHORT).show();
                    }
                    else if(tag.equals("menu_1_2"))
                    {
                        Toast.makeText(mContext, "the tag is" + tag, Toast.LENGTH_SHORT).show();
                    }
         */
                }
                return false;
            }
        });
    }
}
