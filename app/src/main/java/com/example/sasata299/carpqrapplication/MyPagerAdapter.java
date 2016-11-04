package com.example.sasata299.carpqrapplication;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sasata299 on 2016/11/03.
 */

public class MyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String[] mPageTitles;

    public MyPagerAdapter(Activity activity, String[] pageTitles) {
        mContext = activity;
        mLayoutInflater = activity.getLayoutInflater();
        mPageTitles = pageTitles;
    }

    @Override
    public int getCount() {
        return mPageTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitles[position];
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object); // objectに viewが含まれるなら trueを返す
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = mLayoutInflater.inflate(R.layout.page, null);
        TextView tv = (TextView) v.findViewById(R.id.textView);
        tv.setText("Page" + (position + 1));

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}