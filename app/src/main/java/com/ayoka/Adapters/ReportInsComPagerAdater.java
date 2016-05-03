package com.ayoka.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ayoka.fragments.ReportInsComAnnualTabFragments;
import com.ayoka.fragments.ReportInsComDailyTabFragments;
import com.ayoka.fragments.ReportTab2Fragments;


public class ReportInsComPagerAdater extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ReportInsComPagerAdater(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ReportInsComDailyTabFragments tab1 = new ReportInsComDailyTabFragments();
                return tab1;
            case 1:
                ReportInsComAnnualTabFragments tab2 = new ReportInsComAnnualTabFragments();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
