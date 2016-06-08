package com.ayoka.Charts.listviewitems;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ayoka.vdfreport.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.PieData;

public class PieChartItem extends ChartItem {

    private Typeface mTf;

    private String Description;

    public PieChartItem(ChartData<?> cd, Context c, String Description) {
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
                    R.layout.list_item_piechart, null);
            holder.chart = (PieChart) convertView.findViewById(R.id.chart);
            TextView tw = (TextView) convertView.findViewById(R.id.textView);
            tw.setText(this.Description);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // apply styling
        holder.chart.setDescription("");
        mChartData.setValueTypeface(mTf);
        
        // set data
        holder.chart.setData((PieData) mChartData);

        // do not forget to refresh the chart
//        holder.chart.invalidate();
        holder.chart.animateY(3000);
        holder.chart.setClickable(false);
        holder.chart.setBackgroundColor(Color.WHITE);
        return convertView;

    }
    
    private static class ViewHolder {
        PieChart chart;
    }
}
