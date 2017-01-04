package com.example.sasata299.carpqrapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by sasata299 on 2016/11/03.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mPageTitles;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] pageTitles) {
        super(fm);
        mPageTitles = pageTitles;
    }

    void outputLog(int position) {
        Log.i("logger", String.format("%s:[%d](%x)", Thread.currentThread().getStackTrace()[3].getMethodName(), position, this.hashCode()));
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                outputLog(position);
                return new ScoreReportFragment();
            case 1:
                outputLog(position);
                return new Fragment2();
            default:
                outputLog(position);
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