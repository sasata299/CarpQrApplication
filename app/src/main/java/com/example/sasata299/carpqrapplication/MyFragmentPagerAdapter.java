package com.example.sasata299.carpqrapplication;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;

/**
 * Created by sasata299 on 2016/11/03.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String[] mPageTitles;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] pageTitles) {
        super(fm);
        mPageTitles = pageTitles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            default:
                return new Fragment3();
        }
    }

    @Override
    public int getCount() {
        return mPageTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitles[position];
    }
}