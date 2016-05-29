package com.ayoka.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.ArrayAdapter;

import com.ayoka.Model.ReportDetail;
import com.ayoka.Model.ReportList;
import com.ayoka.fragments.ReportFragment;
import com.ayoka.fragments.ReportInsComDailyTabFragments;
import com.ayoka.fragments.ReportTab1Fragments;
import com.ayoka.fragments.ReportTab2Fragments;

import java.util.ArrayList;
import java.util.List;

public class ReportPagerAdapter extends FragmentStatePagerAdapter {
	int mNumOfTabs;

	List<com.ayoka.Model.ReportList>  reportlist;
	public ReportFragment tab1;
	public ReportPagerAdapter(FragmentManager fm, int NumOfTabs, List<com.ayoka.Model.ReportList> reportlist) {
		super(fm);
		this.mNumOfTabs = NumOfTabs;
		this.reportlist=reportlist;
	}

	@Override
	public Fragment getItem(int position) {

		tab1 = new ReportFragment();
		//ReportDetail.ReportList rreportList = this.reportDetail.getReportList().get(position);
		tab1.reportList =reportlist.get(position);
		tab1.reportType=3;
		return tab1;

	}

	@Override
	public int getCount() {
		return mNumOfTabs;
	}
}