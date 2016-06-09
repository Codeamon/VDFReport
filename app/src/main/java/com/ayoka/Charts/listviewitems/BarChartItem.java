package com.ayoka.Charts.listviewitems;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoka.Charts.BarChartModel;
import com.ayoka.Interfaces.InterfaceController;
import com.ayoka.Model.ReportInfoResponse;
import com.ayoka.Model.ResponseMessage;
import com.ayoka.common.Constants;
import com.ayoka.vdfreport.LoginActivity;
import com.ayoka.vdfreport.R;
import com.ayoka.vdfreport.ReportActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.ChartData;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BarChartItem extends ChartItem {
    
    private Typeface mTf;

    private RestAdapter restAdapter;
    private InterfaceController restInterface;
    private final Activity activityContext;
    private String Description;
    private final Integer preContentId;

    public BarChartItem(Activity activityContext,ChartData<?> cd, Context c,String Description,Integer preContentId) {
        super(cd);
        this.preContentId=preContentId;
        this.Description = Description;
        this.activityContext=activityContext;
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.URL)
                .build();

        restInterface = restAdapter.create(InterfaceController.class);
        //mTf = Typeface.createFromAsset(c.getAssets(), "OpenSans-Regular.ttf");
    }

    @Override
    public int getItemType() {
        return TYPE_BARCHART;
    }

    @Override
    public View getView(final int position, View convertView, Context c) {

        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(c).inflate(
                    R.layout.list_item_barchart, null);
            holder.chart = (BarChart) convertView.findViewById(R.id.chart);
            TextView tw = (TextView) convertView.findViewById(R.id.textView);
            tw.setText(this.Description);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // apply styling
        holder.chart.setDescription("");
        holder.chart.setDrawGridBackground(false);
        holder.chart.setDrawBarShadow(false);

        XAxis xAxis = holder.chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        
        YAxis leftAxis = holder.chart.getAxisLeft();
        //leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(5, false);
        leftAxis.setSpaceTop(20f);
        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)
       
        YAxis rightAxis = holder.chart.getAxisRight();
        //rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(5, false);
        rightAxis.setSpaceTop(20f);
        rightAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

        //mChartData.setValueTypeface(mTf);
        
        // set data

        holder.chart.setData((BarData) mChartData);
        if(preContentId!=0) {
            holder.chart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    restInterface.GetReportInfo("13", new Callback<ResponseMessage<ReportInfoResponse[]>>() {

                        @Override
                        public void success(ResponseMessage<ReportInfoResponse[]> reportInfoResponse, Response response) {

                            Intent intent = new Intent(activityContext, ReportActivity.class);
                            intent.putExtra("reportInfoResponse", reportInfoResponse.getMessage());
                            intent.putExtra("PreContentId", preContentId);
                            activityContext.startActivity(intent);
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {


                            retrofitError.printStackTrace(); //to see if you have errors
                            String merror = retrofitError.getMessage();
                            Toast.makeText(activityContext, merror, Toast.LENGTH_LONG).show();
                        }
                    });

//                mChartData.getDataSets().
                    //activityContext.startActivity(new Intent(activityContext, LoginActivity.class));
                }
            });
        }
        
        // do not forget to refresh the chart
//        holder.chart.invalidate();
        holder.chart.animateY(700);

        return convertView;

    }
    
    private static class ViewHolder {
        BarChart chart;
    }
}
