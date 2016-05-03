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

/**
 * Created by ahmetyildirim on 29.4.2016.
 */
public class ReportInsComAnnualTabFragments extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(110f, 0));
        entries.add(new BarEntry(298f, 1));
        entries.add(new BarEntry(145f, 2));
        entries.add(new BarEntry(291f, 3));
        entries.add(new BarEntry(145f, 4));
        entries.add(new BarEntry(624f, 5));
        entries.add(new BarEntry(520f, 6));
        entries.add(new BarEntry(410f, 7));
        entries.add(new BarEntry(604f, 8));
        entries.add(new BarEntry(116f, 9));
        entries.add(new BarEntry(134f, 10));
        entries.add(new BarEntry(120f, 11));


        BarDataSet dataset = new BarDataSet(entries, "# Aylar");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Ocak");
        labels.add("Şubat");
        labels.add("Mart");
        labels.add("Nisan");
        labels.add("Mayıs");
        labels.add("Haziran");
        labels.add("Temmuz");
        labels.add("Ağustos");
        labels.add("Eylül");
        labels.add("Ekim");
        labels.add("Kasım");
        labels.add("Aralık");

        BarChart chart = new BarChart(getActivity().getApplicationContext());
        //  setContentView(chart);
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.setDescription("Aylık Komisyon Raporları ");
        chart.animateY(3000);

        return chart;
    }
}