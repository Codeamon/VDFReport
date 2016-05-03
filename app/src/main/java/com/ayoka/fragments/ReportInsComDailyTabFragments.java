package com.ayoka.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class ReportInsComDailyTabFragments extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(43115f, 0));
        entries.add(new BarEntry(65980f, 1));
        entries.add(new BarEntry(73045f, 2));
        entries.add(new BarEntry(53296f, 3));
        BarDataSet dataset = new BarDataSet(entries, "# Aylar");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("2013");
        labels.add("2014");
        labels.add("2015");
        labels.add("2016");

        BarChart chart = new BarChart(getActivity().getApplicationContext());
        //  setContentView(chart);
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.setDescription("Bayi Komisyon Raporları");
        chart.animateY(3000);
        return chart;
        // return inflater.inflate(R.layout.activity_report_tab1, container, false);
    }
}
