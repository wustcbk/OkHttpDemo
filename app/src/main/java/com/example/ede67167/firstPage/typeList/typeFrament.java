package com.example.ede67167.firstPage.typeList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ede67167.MyApplication;
import com.example.ede67167.R;
import com.example.ede67167.Event;

import de.greenrobot.event.EventBus;

/**
 * Created by EDE67167 on 2016-01-25.
 */
public class typeFrament extends android.support.v4.app.Fragment {
    private LayoutInflater mLayoutInflater;

    @Override
    public void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EventBus.getDefault().register(this);
    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mLayoutInflater=inflater;
        View framentView = inflater.inflate(R.layout.firstpage_typelist, container, false);
           final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
               //设置组视图的显示文字
               private  final String[] generalsTypes = new String[]{"A", "B", "C"};
               //子视图显示文字
               private  final String[][] generals = new String[][]{
                       {"a1", "a2", "a3", "a4", "a5", "a6","a7","a8","a9","a10","a11","a12"},
                       {"b1", "b2", "b3", "b4", "b5", "b6","b7","b8","b9","b10","b11","b12"},
                       {"c1", "c2", "c3", "c4", "c5", "c6","c7","c8","c9","c10","c11","c12"}
               };
               @Override
            public int getGroupCount() {
                   return generalsTypes.length;
               }

            @Override
            public int getChildrenCount(int groupPosition) {
                return generals[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return generalsTypes[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return generals[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                View view=mLayoutInflater.inflate(R.layout.firstpage_typelist_title,null);
                TextView textView=(TextView)view.findViewById(R.id.first_page_type_item);
                textView.setText(generalsTypes[groupPosition]);
                return view;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                View view=mLayoutInflater.inflate(R.layout.firstpage_typelist_item,null);
                TextView textView=(TextView)view.findViewById(R.id.first_page_type_item);
                textView.setText(generals[groupPosition][childPosition]);
                return view;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }




        };
        ExpandableListView expandableListView = (ExpandableListView) framentView.findViewById(R.id.firstpage_expand_typelist);
        expandableListView.setAdapter(adapter);
        //设置item点击的监听器
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                EventBus.getDefault().post(new Event.TypeSelectInfo(groupPosition,childPosition));
                Toast.makeText(MyApplication.getInstance(),
                        "你点击了" + adapter.getChild(groupPosition, childPosition),
                        Toast.LENGTH_SHORT).show();

                return false;
            }
        });




        return framentView;
    }
}
