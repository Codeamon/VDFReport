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
public class ReportTab2Fragments extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 0));
        entries.add(new BarEntry(298f, 1));
        entries.add(new BarEntry(145f, 2));
        entries.add(new BarEntry(2916f, 3));
        entries.add(new BarEntry(1405f, 4));
        entries.add(new BarEntry(924f, 5));

        entries.add(new BarEntry(0f, 6));
        entries.add(new BarEntry(0f, 7));
        entries.add(new BarEntry(604f, 8));
        entries.add(new BarEntry(1216f, 9));
        entries.add(new BarEntry(134f, 10));
        entries.add(new BarEntry(120f, 11));

        entries.add(new BarEntry(405f, 12));
        entries.add(new BarEntry(0f, 13));
        entries.add(new BarEntry(0f, 14));
        entries.add(new BarEntry(12916f, 15));
        entries.add(new BarEntry(18405f, 16));
        entries.add(new BarEntry(9240f, 17));

        entries.add(new BarEntry(4050f, 18));
        entries.add(new BarEntry(8980f, 19));
        entries.add(new BarEntry(0f, 20));
        entries.add(new BarEntry(0f, 21));
        entries.add(new BarEntry(18405f, 22));
        entries.add(new BarEntry(9240f, 23));

        entries.add(new BarEntry(4050f, 24));
        entries.add(new BarEntry(8980f, 25));
        entries.add(new BarEntry(6045f, 26));
        entries.add(new BarEntry(0f, 27));
        entries.add(new BarEntry(05f, 28));
        entries.add(new BarEntry(9240f, 29));

        BarDataSet dataset = new BarDataSet(entries, "# Günler");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("30");
        labels.add("29");
        labels.add("30");
        labels.add("31");
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");
        labels.add("5");
        labels.add("6");
        labels.add("7");
        labels.add("8");
        labels.add("9");
        labels.add("10");
        labels.add("11");
        labels.add("12");
        labels.add("13");
        labels.add("14");
        labels.add("15");
        labels.add("16");
        labels.add("17");
        labels.add("18");
        labels.add("19");
        labels.add("20");
        labels.add("21");
        labels.add("22");
        labels.add("23");
        labels.add("24");
        labels.add("25");
        labels.add("26");

        BarChart chart = new BarChart(getActivity().getApplicationContext());
        //  setContentView(chart);
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.setDescription("Günlük Kredi Raporları ");
        chart.animateY(3000);

        return chart;
    }
}