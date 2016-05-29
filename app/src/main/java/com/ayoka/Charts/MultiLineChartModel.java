package com.ayoka.Charts;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.ayoka.Interfaces.InterfaceCharts;
import com.ayoka.Model.ReportDetail;
import com.ayoka.Model.ReportList;
import com.ayoka.Model.ReportValue;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by OsmanKorcan on 9.5.2016.
 */
public class MultiLineChartModel implements InterfaceCharts {

    private LineChart mChart;
    public MultiLineChartModel()
    {

    }

    private int[] mColors = new int[] {
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2]
    };

    public final View GetChart(Context context, ReportList reportList) {

        mChart = new LineChart(context);
        mChart.setDrawGridBackground(false);
        mChart.setDescription("");
        mChart.setDrawBorders(false);

        mChart.getAxisLeft().setDrawAxisLine(false);
        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getAxisRight().setDrawAxisLine(false);
        mChart.getAxisRight().setDrawGridLines(false);
        mChart.getXAxis().setDrawAxisLine(false);
        mChart.getXAxis().setDrawGridLines(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);



        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        ArrayList<String> xVal = new ArrayList<String>();
        ArrayList<String> oVal = new ArrayList<String>();
        for (ReportValue rv : reportList.getReportValues()
             ) {
            if(!xVal.contains(rv.getReportColumns().get(1).getColumnValue())){
                xVal.add(rv.getReportColumns().get(1).getColumnValue());
            }
        }

        //for (int i =0; i < xVal.size();i++) {
        for (ReportValue rv : reportList.getReportValues()
                ) {
            if(!oVal.contains(rv.getReportColumns().get(1).getColumnValue())){
                oVal.add(rv.getReportColumns().get(1).getColumnValue());
            }
            if(!xVal.contains(rv.getReportColumns().get(0).getColumnValue())){
                xVal.add(rv.getReportColumns().get(0).getColumnValue());
            }

        }
        for ( String GroupName : oVal
             ) {
            ArrayList<Entry> values = new ArrayList<Entry>();
            for (ReportValue rv : reportList.getReportValues()
                    ) {

            if(GroupName.equals(rv.getReportColumns().get(1).getColumnValue()))
                values.add(new Entry((float)Integer.parseInt(rv.getReportColumns().get(0).getColumnValue()),Integer.parseInt(rv.getReportColumns().get(1).getColumnValue())));

            }

            LineDataSet d = new LineDataSet(values, GroupName);


            int color =   mColors[(oVal.indexOf(GroupName)) % mColors.length];
            d.setColor(color);
            d.setCircleColor(color);
            d.setLineWidth(6f);
            dataSets.add(d);

        }


        // make the first DataSet dashed
        //((LineDataSet) dataSets.get(0)).enableDashedLine(10, 10, 0);
        //((LineDataSet) dataSets.get(0)).setColors(ColorTemplate.PASTEL_COLORS);
        //((LineDataSet) dataSets.get(0)).setCircleColors(ColorTemplate.VORDIPLOM_COLORS);

        LineData data = new LineData(xVal, dataSets);
        mChart.setData(data);
        mChart.invalidate();

        return mChart;
    }
}
