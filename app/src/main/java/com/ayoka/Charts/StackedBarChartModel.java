package com.ayoka.Charts;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.ayoka.Interfaces.InterfaceCharts;
import com.ayoka.Model.ReportList;
import com.ayoka.Model.ReportValue;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OsmanKorcan on 9.5.2016.
 */
public class StackedBarChartModel implements InterfaceCharts {

    public StackedBarChartModel()
    {

    }
    private int[] mColors = new int[] {
            Color.BLACK,
            Color.RED,
            Color.BLUE,
            Color.CYAN,
            Color.GREEN
    };
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
        ArrayList<String> xVal = new ArrayList<String>();
        ArrayList<String> oVal = new ArrayList<String>();

        for (ReportValue rv : reportList.getReportValues()
                ) {
            if(!oVal.contains(rv.getReportColumns().get(2).getColumnValue())){
                oVal.add(rv.getReportColumns().get(2).getColumnValue());
            }
            if(!xVal.contains(rv.getReportColumns().get(1).getColumnValue())){
                xVal.add(rv.getReportColumns().get(1).getColumnValue());
            }

        }

        ArrayList<BarEntry> values = new ArrayList<BarEntry>();

        for ( String x : xVal
                ) {

            float[] f = new float[oVal.size()];
            for (ReportValue rv : reportList.getReportValues()
                    ) {
                if(x.equals(rv.getReportColumns().get(1).getColumnValue())){
                    f[oVal.indexOf(rv.getReportColumns().get(2).getColumnValue())]=(float)Integer.parseInt(rv.getReportColumns().get(0).getColumnValue());

            }
            }
            BarEntry barEntry = new BarEntry(f,xVal.indexOf(x));
            values.add(barEntry);
        }

        BarDataSet set1;

        set1 = new BarDataSet(values, reportList.getDescription());
        set1.setColors(getColors(oVal.size()));

        String[] oValArr = new String[oVal.size()];
        oValArr = oVal.toArray(oValArr);
        set1.setStackLabels(oValArr);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVal, dataSets);
        //data.setValueFormatter(new MyValueFormatter());



        return data;
    }

    private int[] getColors(int stacksize) {

        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];

        for (int i = 0; i < stacksize; i++) {
            colors[i] = ColorTemplate.VORDIPLOM_COLORS[i];
        }

        return colors;
    }
}
