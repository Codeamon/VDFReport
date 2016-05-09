package com.ayoka.Charts;

import android.content.Context;
import android.view.View;

import com.ayoka.Interfaces.InterfaceCharts;
import com.ayoka.Model.ReportDetail;
import com.ayoka.test.TestClass_ReportTab1;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OsmanKorcan on 9.5.2016.
 */
public class PieChartModel implements InterfaceCharts {

    public PieChartModel()
    {

    }

    public final View GetChart(Context context, ReportDetail.ReportList reportList) {

        List<ReportDetail.ReportValue> values=reportList.getReportValues();

        float totalValue=0;

        ArrayList<Entry> entries = new ArrayList<>();
        for(int i=0; i<values.size(); i++)
        {

            String value=values.get(i).getValueName().replace(',','.');
            totalValue+=Float.valueOf(values.get(i).getValueName());
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
        PieChart pChart = new PieChart(context);
        PieData pdata = new PieData(labels, dataset);
        pChart.setData(pdata);
        pChart.setDescription(reportList.getDescription());

        pChart.animateY(3000);
        return pChart;

    }

}
