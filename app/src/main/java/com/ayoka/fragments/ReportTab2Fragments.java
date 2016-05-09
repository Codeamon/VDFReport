package com.ayoka.fragments;

import com.ayoka.Model.ReportDetail;
import com.ayoka.vdfreport.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmet&Korcan on 26.4.2016.
 */
public class ReportTab2Fragments extends Fragment {

    public ReportDetail.ReportList reportList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        ArrayList<BarEntry> entries = new ArrayList<>();

        List<ReportDetail.ReportValue> values=reportList.getReportValues();
        for(int i=0; i<values.size(); i++)
        {
            String value=values.get(i).getValueName().replace(',','.');
            entries.add(new BarEntry(Float.valueOf(value), i));
        }



        BarDataSet dataset = new BarDataSet(entries, "# Aylar");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<String> labels = new ArrayList<String>();
        for(int i=0; i<values.size(); i++)
        {
            labels.add(values.get(i).getValueTypeName());
        }

        BarChart chart = new BarChart(getActivity().getApplicationContext());
        //  setContentView(chart);
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.setDescription(reportList.getDescription());
        chart.animateY(3000);

        return chart;
    }
}