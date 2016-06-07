package com.ayoka.Charts;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.ayoka.Interfaces.InterfaceCharts;
import com.ayoka.Model.ReportColumn;
import com.ayoka.Model.ReportList;
import com.ayoka.Model.ReportValue;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OsmanKorcan on 9.5.2016.
 */
public class MultiBarChartModel implements InterfaceCharts {

    public MultiBarChartModel()
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
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
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
        for ( String GroupName : oVal
                ) {
            ArrayList<BarEntry> values = new ArrayList<BarEntry>();
            for (ReportValue rv : reportList.getReportValues()
                    ) {

                if(GroupName.equals(rv.getReportColumns().get(2).getColumnValue()))
                    values.add(new BarEntry((float)Integer.parseInt(rv.getReportColumns().get(0).getColumnValue()),values.size()));//Integer.parseInt(rv.getReportColumns().get(1).getColumnValue())));

            }

            BarDataSet d = new BarDataSet(values, GroupName);

            int color =   mColors[(oVal.indexOf(GroupName)) % mColors.length];
            d.setColor(color);

            dataSets.add(d);

        }

        BarData data = new BarData(xVal, dataSets);

        return data;
    }
}
