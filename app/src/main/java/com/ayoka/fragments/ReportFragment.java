package com.ayoka.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoka.Charts.BarChartModel;
import com.ayoka.Charts.HorizontalBarChartModel;
import com.ayoka.Charts.LineChartModel;
import com.ayoka.Charts.MultiBarChartModel;
import com.ayoka.Charts.MultiLineChartModel;
import com.ayoka.Charts.PieChartModel;
import com.ayoka.Charts.StackedBarChartModel;
import com.ayoka.Model.ReportDetail;
import com.ayoka.Model.ReportList;
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
import com.github.mikephil.charting.formatter.StackedValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by ahmetyildirim on 8.5.2016.
 */
public class ReportFragment extends Fragment {
    public  ReportList reportList;
    public int reportType=1;
    public Objects report;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

/*
        1 barchart
                2 horizontal barchart
                3 piechart
                4multiple barschart
                5 line chart legent
*/

        switch (reportList.getChartType()) {
            case 1:
                BarChartModel barChartModel = new BarChartModel();
                return barChartModel.GetChart(getActivity().getApplicationContext(), reportList);
            case 2:
                LineChartModel lineChartModel = new LineChartModel();
                return lineChartModel.GetChart(getActivity().getApplicationContext(), reportList);
            case 3:
                PieChartModel pieChartModel = new PieChartModel();
                return pieChartModel.GetChart(getActivity().getApplicationContext(), reportList);
            case 4:
                MultiLineChartModel multiLineChartModel = new MultiLineChartModel();
                return multiLineChartModel.GetChart(getActivity().getApplicationContext(), reportList);
            case 5:
                HorizontalBarChartModel horizontalBarChartModel = new HorizontalBarChartModel();
                return horizontalBarChartModel.GetChart(getActivity().getApplicationContext(), reportList);
            case 6:
                MultiBarChartModel multiBarChartModel = new MultiBarChartModel();
                return multiBarChartModel.GetChart(getActivity().getApplicationContext(), reportList);
            case 7:
                StackedBarChartModel stackedBarChartModel = new StackedBarChartModel();
                return stackedBarChartModel.GetChart(getActivity().getApplicationContext(), reportList);
            default:

                break;
        }

        return new View(getActivity().getApplicationContext());

    }
}