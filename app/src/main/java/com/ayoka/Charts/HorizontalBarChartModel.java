package com.ayoka.Charts;

import android.content.Context;
import android.view.View;

import com.ayoka.Interfaces.InterfaceCharts;
import com.ayoka.Model.ReportColumn;
import com.ayoka.Model.ReportList;
import com.ayoka.Model.ReportValue;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OsmanKorcan on 9.5.2016.
 */
public class HorizontalBarChartModel implements InterfaceCharts {

    private HorizontalBarChart mChart;
    public HorizontalBarChartModel()
    {

    }

    private int[] mColors = new int[] {
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2]
    };

    public final View GetChart(Context context, ReportList reportList) {

        mChart = new HorizontalBarChart(context);
        //mChart.setOnChartValueSelectedListener(this);
        // mChart.setHighlightEnabled(false);

        mChart.setDrawBarShadow(false);

        mChart.setDrawValueAboveBar(true);

        mChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        // mChart.setDrawXLabels(false);

        mChart.setDrawGridBackground(false);

        // mChart.setDrawYLabels(false);

        XAxis xl = mChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xl.setTypeface(tf);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(true);
        xl.setGridLineWidth(0.3f);

        YAxis yl = mChart.getAxisLeft();
        //yl.setTypeface(tf);
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setGridLineWidth(0.3f);
        yl.setAxisMinValue(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);

        YAxis yr = mChart.getAxisRight();
        //yr.setTypeface(tf);
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinValue(0f); // this replaces setStartAtZero(true)
//        yr.setInverted(true);

        mChart.setData(getBarData(reportList));
        return mChart;
    }


    public BarData getBarData(ReportList reportList) {

        List<ReportValue> values=reportList.getReportValues();

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

        for(int i=0; i<values.size(); i++)
        {
            //Raporun tek bir değeri için. BarChart için 2 tane döner
            List<ReportColumn> ReportColumn = values.get(i).getReportColumns();
            //Reportcolumn listesinin ilk nesnesi value.
            //ikinci nesnesi ise label
            yVals1.add(new BarEntry(Float.parseFloat(ReportColumn.get(0).getColumnValue().replace(',','.')), i));
            xVals.add(ReportColumn.get(1).getColumnValue());
        }



        BarDataSet set1;

        set1 = new BarDataSet(yVals1, "");

        set1.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(10f);
            //data.setValueTypeface(tf);

            //mChart.setData(data);

        return data;
    }
/*
    public LineData getBarData(ReportList reportList) {
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

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
            ArrayList<Entry> values = new ArrayList<Entry>();
            for (ReportValue rv : reportList.getReportValues()
                    ) {

                if(GroupName.equals(rv.getReportColumns().get(2).getColumnValue()))
                    values.add(new Entry((float)Integer.parseInt(rv.getReportColumns().get(0).getColumnValue()),Integer.parseInt(rv.getReportColumns().get(1).getColumnValue())));

            }

            LineDataSet d = new LineDataSet(values, GroupName);

            int color =   mColors[(oVal.indexOf(GroupName)) % mColors.length];
            d.setColor(color);
            d.setCircleColor(color);
            d.setLineWidth(6f);
            dataSets.add(d);

        }
        LineData data = new LineData(xVal, dataSets);
        return data;
    }
*/
}
