package com.example.ede67167.firstPage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ede67167.MyApplication;
import com.example.ede67167.R;
import com.example.ede67167.firstPage.content.contentFrament;
import com.example.ede67167.firstPage.typeList.typeFrament;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by EDE67167 on 2016-01-25.
 */
public class firstPage  extends android.support.v4.app.Fragment{
    private View mView;
    private Context mContext;
    private Activity mActivity;
   // static BoxAAA mLeakContainer;

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    static public android.support.v4.app.Fragment newInstance(String s) {
        android.support.v4.app.Fragment newFragment = new firstPage();
        Bundle bundle = new Bundle();
        bundle.putString("hello", s);
        newFragment.setArguments(bundle);
        return newFragment;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {

        mContext=inflater.getContext();
        mView=inflater.inflate(R.layout.firstpage_overall_2, null);
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.id_firstpage_type_2,new typeFrament());
        fragmentTransaction.replace(R.id.id_firstpage_content_2,new contentFrament());
        fragmentTransaction.commit();

      /*  BoxAAA box = new BoxAAA();
        Cat schrodingerCat = new Cat();
        box.hiddenCat = schrodingerCat;
        mLeakContainer = box;
        RefWatcher refWatcher = MyApplication.getRefWatcher(getActivity());
        refWatcher.watch(schrodingerCat);*/
        return mView;
    }

    class Cat {
        int a;
        int b;
        int c;
    }
    class BoxAAA {
        int a;
        int b;
        int c;
        Cat hiddenCat;
    }







}
