package com.ayoka.fragments;

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

/**
 * Created by Ahmet&Korcan on 26.4.2016.
 */
public class ReportTab1Fragments extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4050f, 0));
        entries.add(new BarEntry(8980f, 1));
        entries.add(new BarEntry(6045f, 2));
        entries.add(new BarEntry(12916f, 3));
        entries.add(new BarEntry(18405f, 4));
        entries.add(new BarEntry(9240f, 5));
        entries.add(new BarEntry(6040f, 6));
        BarDataSet dataset = new BarDataSet(entries, "# of Calls");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Ekim");
        labels.add("Kasım");
        labels.add("Aralık");
        labels.add("Ocak");
        labels.add("Şubat");
        labels.add("Mart");
        labels.add("Nisan");

        BarChart chart = new BarChart(getActivity().getApplicationContext());
      //  setContentView(chart);
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.setDescription("Aylık Kredi Raporları");
        chart.animateY(3000);
return chart;
       // return inflater.inflate(R.layout.activity_report_tab1, container, false);
    }
}