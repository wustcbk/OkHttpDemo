package com.example.ede67167.ViewPager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

public class MyViewPagerAdapter extends PagerAdapter {

	List<View> viewLists;

	public MyViewPagerAdapter(List<View> lists) {
		viewLists = lists;
	}

	@Override
	public int getCount() { // 获得size
		// TODO Auto-generated method stub
		return viewLists.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(View view, int position, Object object) // �?��Item
	{
		((ViewPager) view).removeView(viewLists.get(position));
	}

	@Override
	public Object instantiateItem(View view, int position) // 实例化Item
	{
		((ViewPager) view).addView(viewLists.get(position), 0);
		return viewLists.get(position);
	}

}