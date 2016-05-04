package com.ayoka.fragments;

import com.ayoka.Model.Reports;
import com.ayoka.common.JsonOperations;
import com.ayoka.vdfreport.MainActivity;
import com.ayoka.vdfreport.R;
import com.ayoka.vdfreport.ReportActivity;
import com.ayoka.vdfreport.ReportDetailActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Ahmet&Korcan on 26.4.2016.
 */
public class ReportTab1Fragments extends Fragment implements  OnChartValueSelectedListener {

    private int currentProjectId = 0;
    private int currentMainCategoryId=0;
    private int currentTabId=1;

    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;

        Intent intent = new Intent(getActivity().getApplicationContext(), ReportDetailActivity.class);
        intent.putExtra("SelectedIndex", e.getXIndex());
        startActivity(intent);
    }

    public void onNothingSelected() {
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            currentProjectId = extras.getInt("currentProjectId");
            currentMainCategoryId = extras.getInt("currentMainCategoryId");
        }
        ArrayList<View> entries_view = new ArrayList<>();
        ArrayList<BarEntry> entries = new ArrayList<>();

        ArrayList<String> labels = new ArrayList<String>();

        JsonOperations jo= new JsonOperations();
        ArrayList<Reports> reportsList = jo.GetReportByCategoryId(currentMainCategoryId,currentProjectId,currentTabId);
        for (int i=0;i< reportsList.size();i++)
        {
            entries.add(new BarEntry(reportsList.get(i).getValue(),reportsList.get(i).getReportId()));
            labels.add(reportsList.get(i).getTypeValue());
        }



        BarDataSet dataset = new BarDataSet(entries, "# Aylar");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        BarChart chart = new BarChart(getActivity().getApplicationContext());
      //  setContentView(chart);
        BarData data = new BarData(labels, dataset);

        chart.setData(data);
        chart.setDescription("Aylık Kredi Raporları");
        chart.animateY(3000);


        View.OnClickListener myhandler = new View.OnClickListener() {
            public void onClick(View v) {

              int i =1;
            }
        };

        chart.setOnChartValueSelectedListener(this);

return chart;
       // return inflater.inflate(R.layout.activity_report_tab1, container, false);
    }


    private View deneme()
    {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4050f, 0));
        entries.add(new BarEntry(8980f, 1));
        entries.add(new BarEntry(6045f, 2));

        BarDataSet dataset = new BarDataSet(entries, "# Aylar");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Ekim");
        labels.add("Kasım");
        labels.add("Aralık");


        BarChart chart = new BarChart(getActivity().getApplicationContext());
        //  setContentView(chart);
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.setDescription("Deneme Detay Ekranı");
        return chart;
    }
}