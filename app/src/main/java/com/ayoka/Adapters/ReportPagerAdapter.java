package com.ayoka.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ayoka.Model.ReportDetail;
import com.ayoka.fragments.ReportFragment;
import com.ayoka.fragments.ReportInsComDailyTabFragments;
import com.ayoka.fragments.ReportTab1Fragments;
import com.ayoka.fragments.ReportTab2Fragments;

public class ReportPagerAdapter extends FragmentStatePagerAdapter {
	int mNumOfTabs;
	ReportDetail reportDetail;
	public ReportFragment tab1;
	public ReportPagerAdapter(FragmentManager fm, int NumOfTabs, ReportDetail reportDetail) {
		super(fm);
		this.mNumOfTabs = NumOfTabs;
		this.reportDetail=reportDetail;
	}

	@Override
	public Fragment getItem(int position) {

		tab1 = new ReportFragment();
		tab1.reportList=this.reportDetail.getReportList().get(position);
		tab1.reportType=1;
		return tab1;

	}

	@Override
	public int getCount() {
		return mNumOfTabs;
	}
}