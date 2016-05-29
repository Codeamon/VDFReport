package com.ayoka.Charts;

import android.content.Context;
import android.view.View;

import com.ayoka.Interfaces.InterfaceCharts;
import com.ayoka.Model.ReportColumn;
import com.ayoka.Model.ReportDetail;
import com.ayoka.Model.ReportList;
import com.ayoka.Model.ReportValue;
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

    public final View GetChart(Context context,ReportList reportList)
    {

        BarChart chart = new BarChart(context);
        
        chart.setData(getBarData(reportList));
        chart.setDescription(reportList.getDescription());
        chart.animateY(3000);

        return chart;
    }

    public BarData getBarData(ReportList reportList)
    {
        List<ReportValue> values=reportList.getReportValues();

        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();

        for(int i=0; i<values.size(); i++)
        {
            //Raporun tek bir değeri için. BarChart için 2 tane döner
            List<ReportColumn> ReportColumn = values.get(i).getReportColumns();
            //Reportcolumn listesinin ilk nesnesi value.
            //ikinci nesnesi ise label
            entries.add(new BarEntry(Float.parseFloat(ReportColumn.get(0).getColumnValue()), i));
            labels.add(ReportColumn.get(1).getColumnValue());
        }
        BarDataSet dataset = new BarDataSet(entries, "# "+ "");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(labels, dataset);
        return data;
    }
}
