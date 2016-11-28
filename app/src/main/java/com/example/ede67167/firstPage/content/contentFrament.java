package com.example.ede67167.firstPage.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ede67167.R;
import com.example.ede67167.Event;

import de.greenrobot.event.EventBus;

/**
 * Created by EDE67167 on 2016-01-25.
 */
public class contentFrament extends android.support.v4.app.Fragment {
    TextView mTextView;
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View framentView=inflater.inflate(R.layout.firstpage_content, container, false);
        mTextView=(TextView)framentView.findViewById(R.id.id_first_page_content);
        return framentView;
    }

    public void onEventMainThread(Event.TypeSelectInfo item)
    {
        mTextView.setText("the group:"+item.getGroup()+",the child is:"+item.getChild());
    }

}
