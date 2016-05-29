package com.ayoka.Charts;

import android.content.Context;
import android.view.View;

import com.ayoka.Interfaces.InterfaceCharts;
import com.ayoka.Model.ReportColumn;
import com.ayoka.Model.ReportList;
import com.ayoka.Model.ReportValue;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OsmanKorcan on 9.5.2016.
 */
public class SimpleBarChartModel implements InterfaceCharts {

    public SimpleBarChartModel()
    {

    }

    public final View GetChart(Context context,ReportList reportList)
    {
       // List<ReportValue> values=reportList.getReportValues();

        BarChart chart = new BarChart(context);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();

        List<String> labels;// = new ArrayList<String>();
//            entries = FileUtils.loadEntriesFromAssets(getActivity().getAssets(), "stacked_bars.txt");

        for(int j = 0; j < reportList.getReportValues().size(); j++) {
            int barValue = Integer.parseInt(reportList.getReportValues().get(j).getReportColumns().get(0).getColumnValue());
            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
            entries.add(new BarEntry((float)barValue , j));
            //labels.add(reportList.getReportValues().get(j).getReportColumns().get(1).getColumnValue());
            BarDataSet ds = new BarDataSet(entries, reportList.getReportValues().get(j).getReportColumns().get(1).getColumnValue());
            ds.setColors(ColorTemplate.VORDIPLOM_COLORS);
            dataSets.add(ds);
        }

        BarData data = new BarData(ChartData.generateXVals(0, reportList.getReportValues().size()),dataSets);
        chart.setData(data);
        chart.setDescription(reportList.getDescription());

        chart.animateY(3000);

        return chart;
    }
}
