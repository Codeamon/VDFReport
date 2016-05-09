package com.ayoka.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoka.Model.ReportDetail;
import com.ayoka.Model.Reports;
import com.ayoka.common.JsonOperations;
import com.ayoka.test.TestClass_ReportTab1;
import com.ayoka.vdfreport.ReportDetailActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmetyildirim on 8.5.2016.
 */
public class ReportFragment extends Fragment {
    public ReportDetail.ReportList reportList;
    public int reportType=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        List<ReportDetail.ReportValue> values=reportList.getReportValues();


        if(reportType==1)
        {
            ArrayList<BarEntry> entries = new ArrayList<>();
            for(int i=0; i<values.size(); i++)
            {
                String value=values.get(i).getValueName().replace(',','.');
                entries.add(new BarEntry(Float.valueOf(value), i));
            }
            BarDataSet dataset = new BarDataSet(entries, "# "+ "'@ValueType'");
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
        else {

            TestClass_ReportTab1 tc = new TestClass_ReportTab1();
            float totalValue=0;
            for(int i=0; i<values.size(); i++)
            {
                String value=values.get(i).getValueName().replace(',','.');
                totalValue+=Float.valueOf(value);
            }

            ArrayList<Entry> entries = new ArrayList<>();
            for(int i=0; i<values.size(); i++)
            {
                String value=values.get(i).getValueName().replace(',','.');
                float floatValue=Float.valueOf(value);
                entries.add(new Entry(floatValue/totalValue, i));
            }

            PieDataSet dataset = new PieDataSet(entries, "# Total " + totalValue);
            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset.setSliceSpace(2f);
            dataset.setColors(ColorTemplate.VORDIPLOM_COLORS);

            ArrayList<String> labels = new ArrayList<String>();
            for(int i=0; i<values.size(); i++)
            {
                labels.add(values.get(i).getValueTypeName());
            }
            PieChart pChart = new PieChart(getActivity().getApplicationContext());
            PieData pdata = new PieData(labels, dataset);
            pChart.setData(pdata);
            pChart.setDescription(reportList.getDescription());

            pChart.animateY(3000);
            return pChart;
        }

    }
}