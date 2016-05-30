package com.ayoka.Charts.listviewitems;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ayoka.vdfreport.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.ChartData;

public class HorizontalBarChartItem extends ChartItem {

    private Typeface mTf;

    private String Description;

    public HorizontalBarChartItem(ChartData<?> cd, Context c,String Description) {
        super(cd);
        this.Description = Description;
        //mTf = Typeface.createFromAsset(c.getAssets(), "OpenSans-Regular.ttf");
    }

    @Override
    public int getItemType() {
        return TYPE_BARCHART;
    }

    @Override
    public View getView(int position, View convertView, Context c) {

        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(c).inflate(
                    R.layout.list_item_horizontalbarchart, null);
            holder.chart = (HorizontalBarChart) convertView.findViewById(R.id.chart);
            TextView tw = (TextView) convertView.findViewById(R.id.textView);
            tw.setText(this.Description);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.chart.setDrawBarShadow(false);

        holder.chart.setDrawValueAboveBar(true);

        holder.chart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        holder.chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        holder.chart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        // mChart.setDrawXLabels(false);

        holder.chart.setDrawGridBackground(false);

        // mChart.setDrawYLabels(false);

        XAxis xl = holder.chart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xl.setTypeface(tf);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(true);
        xl.setGridLineWidth(0.3f);

        YAxis yl = holder.chart.getAxisLeft();
        //yl.setTypeface(tf);
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setGridLineWidth(0.3f);
        yl.setAxisMinValue(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);

        YAxis yr = holder.chart.getAxisRight();
        //yr.setTypeface(tf);
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinValue(0f); // this replaces setStartAtZero(true)
//        yr.setInverted(true);
        holder.chart.setData((BarData) mChartData);

        return convertView;

    }
    
    private static class ViewHolder {
        HorizontalBarChart chart;
    }
}
