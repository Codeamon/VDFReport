package com.ayoka.Charts;

import android.content.Context;
import android.view.View;

import com.ayoka.Interfaces.InterfaceCharts;
import com.ayoka.Model.ReportColumn;
import com.ayoka.Model.ReportDetail;
import com.ayoka.Model.ReportList;
import com.ayoka.Model.ReportValue;
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
public class PercentPieChartModel implements InterfaceCharts {

    public PercentPieChartModel()
    {

    }

    public final View GetChart(Context context, ReportList reportList) {

        List<ReportValue> values=reportList.getReportValues();

        float totalValue=0;



        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();

        for(int i=0; i<values.size(); i++)
        {
            List<ReportColumn> ReportColumn = values.get(i).getReportColumns();
            //ReportColumn listesinin ilk nesnesi value.
            String value=ReportColumn.get(0).getColumnValue().replace(',','.');
            float floatValue=Float.valueOf(value);
            totalValue+=floatValue;
        }
        float divider = totalValue / 100;
        for(int i=0; i<values.size(); i++)
        {
            List<ReportColumn> ReportColumn = values.get(i).getReportColumns();
            String value=ReportColumn.get(0).getColumnValue().replace(',','.');
            float floatValue=Float.valueOf(value);
            entries.add(new Entry(floatValue/divider, i));

            labels.add(ReportColumn.get(1).getColumnValue());
        }

        PieDataSet dataset = new PieDataSet(entries, "# Total " + totalValue);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        dataset.setSliceSpace(3f);
        dataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
        dataset.setValueTextSize(11f);

        PieChart pChart = new PieChart(context);
        PieData pdata = new PieData(labels, dataset);
        pChart.setData(pdata);
        pChart.setDescription(reportList.getDescription());

        pChart.animateY(3000);
        return pChart;

    }

}
