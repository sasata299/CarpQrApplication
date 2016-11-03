package com.example.sasata299.carpqrapplication;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sasata299 on 2016/11/03.
 */

public class MyPagerAdapter extends PagerAdapter {
    private LayoutInflater mLayoutInflater;

    public MyPagerAdapter(Activity activity) {
        mLayoutInflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public String getPageTitle(int position) {

        String pageTitle = "";

        switch (position) {
            case 0:
                pageTitle = "試合速報";
                break;
            case 1:
                pageTitle = "選手情報";
                break;
            case 2:
                pageTitle = "その他";
                break;
        }

        return pageTitle;
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