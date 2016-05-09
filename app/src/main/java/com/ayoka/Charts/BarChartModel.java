package com.ayoka.Charts;

import android.content.Context;
import android.view.View;

import com.ayoka.Interfaces.InterfaceCharts;
import com.ayoka.Model.ReportDetail;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OsmanKorcan on 9.5.2016.
 */
public class BarChartModel implements InterfaceCharts {

    public BarChartModel()
    {

    }

    public final View GetChart(Context context,ReportDetail.ReportList reportList)
    {
        List<ReportDetail.ReportValue> values=reportList.getReportValues();
        BarChart chart = new BarChart(context);


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

        //  setContentView(chart);
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.setDescription(reportList.getDescription());
        chart.animateY(3000);

        return chart;
    }


}
