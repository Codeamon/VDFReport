package com.ayoka.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ayoka.Model.ReportDetail;
import com.ayoka.fragments.ReportInsComDailyTabFragments;
import com.ayoka.fragments.ReportTab1Fragments;
import com.ayoka.fragments.ReportTab2Fragments;

public class ReportPagerAdapter extends FragmentStatePagerAdapter {
	int mNumOfTabs;
	ReportDetail reportDetail;
	public ReportTab1Fragments tab1;
	public ReportPagerAdapter(FragmentManager fm, int NumOfTabs, ReportDetail reportDetail) {
		super(fm);
		this.mNumOfTabs = NumOfTabs;
		this.reportDetail=reportDetail;
	}

	@Override
	public Fragment getItem(int position) {

		switch (position) {
			case 0:
				tab1 = new ReportTab1Fragments();

				return tab1;
			case 1:
				ReportTab2Fragments tab2 = new ReportTab2Fragments();
				tab2.reportList=this.reportDetail.getReportList().get(1);
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