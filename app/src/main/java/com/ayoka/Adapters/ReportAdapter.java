package com.ayoka.Adapters;

import java.util.List;

import com.ayoka.vdfreport.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayoka.common.Reports;

public class ReportAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<Reports>     mKisiListesi;

	public ReportAdapter(Activity activity, List<Reports> kisiler) {
		//XML'i alıp View'a çevirecek inflater'ı örnekleyelim
		mInflater = (LayoutInflater) activity.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		//gösterilecek listeyi de alalım
		mKisiListesi = kisiler;
	}

	@Override
	public int getCount() {
		return mKisiListesi.size();
	}

	@Override
	public Reports getItem(int position) {
		//şöyle de olabilir: public Object getItem(int position)
		return mKisiListesi.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View satirView;

		satirView = mInflater.inflate(R.layout.line_layout, null);
		TextView textView = 
				(TextView) satirView.findViewById(R.id.reportname);
		ImageView imageView = 
				(ImageView) satirView.findViewById(R.id.reportsimge);

		Reports kisi = mKisiListesi.get(position);

		textView.setText(kisi.getReportName());


		return satirView;
	}
}