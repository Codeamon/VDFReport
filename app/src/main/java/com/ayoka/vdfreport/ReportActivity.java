package com.ayoka.vdfreport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoka.Adapters.CategoryListAdapter;
import com.ayoka.Adapters.ReportPagerAdapter;
import com.ayoka.Helper.ShareScreenshot;
import com.ayoka.Interfaces.InterfaceController;
import com.ayoka.Listener.RecyclerTouchListener;
import com.ayoka.Model.CategoryReportModel;
import com.ayoka.Model.ReportDetail;
import com.ayoka.common.Constants;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Ahmet&Korcan on 23.4.2016.
 * Deneme
 */
public class ReportActivity extends AppCompatActivity {

    private RestAdapter restAdapter;
    private InterfaceController restInterface;
    private ProgressDialog progressDialog;
    protected BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private Typeface mTf;
    public View view;

    private int reportDetailId = 0;
    ReportPagerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            reportDetailId = extras.getInt("reportId");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.URL)
                .build();

        restInterface = restAdapter.create(InterfaceController.class);

        progressDialog = new ProgressDialog(ReportActivity.this);
        progressDialog.setMessage("Raporlar yükleniyor...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        restInterface.getreport(reportDetailId,"2015", new Callback<ReportDetail>() {
            @Override
            public void success(ReportDetail reportDetail, Response response) {
                progressDialog.cancel();
                for (ReportDetail.TabList tab : reportDetail.getTabList()) {
                    tabLayout.addTab(tabLayout.newTab().setText(tab.getTabName()));
                }
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
                adapter = new ReportPagerAdapter
                        (getSupportFragmentManager(), tabLayout.getTabCount(),reportDetail);
                viewPager.setAdapter(adapter);
                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }

            @Override
            public void failure(RetrofitError retrofitError) {

                progressDialog.cancel();
                retrofitError.printStackTrace(); //to see if you have errors
                String merror = retrofitError.getMessage();
                Toast.makeText(getApplicationContext(), merror, Toast.LENGTH_LONG).show();
            }
        });




//        tvX = (TextView) findViewById(R.id.tvXMax);
//        tvY = (TextView) findViewById(R.id.tvYMax);
//
//        mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
//        mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);
//
//        mChart = (BarChart) findViewById(R.id.chart1);
//        mChart.setOnChartValueSelectedListener(this);
//
//        mChart.setDrawBarShadow(false);
//        mChart.setDrawValueAboveBar(true);
//
//        mChart.setDescription("");
//
//        // if more than 60 entries are displayed in the chart, no values will be
//        // drawn
//        mChart.setMaxVisibleValueCount(60);
//
//        // scaling can now only be done on x- and y-axis separately
//        mChart.setPinchZoom(false);
//
//        mChart.setDrawGridBackground(false);
//        // mChart.setDrawYLabels(false);
//
//        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
//
//        XAxis xAxis = mChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTypeface(mTf);
//        xAxis.setDrawGridLines(false);
//        xAxis.setSpaceBetweenLabels(2);
//
//        YAxisValueFormatter custom = new MyYAxisValueFormatter();
//
//        YAxis leftAxis = mChart.getAxisLeft();
//        leftAxis.setTypeface(mTf);
//        leftAxis.setLabelCount(8, false);
//        leftAxis.setValueFormatter(custom);
//        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        leftAxis.setSpaceTop(15f);
//        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)
//
//        YAxis rightAxis = mChart.getAxisRight();
//        rightAxis.setDrawGridLines(false);
//        rightAxis.setTypeface(mTf);
//        rightAxis.setLabelCount(8, false);
//        rightAxis.setValueFormatter(custom);
//        rightAxis.setSpaceTop(15f);
//        rightAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)
//
        /*
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4050f, 0));
        entries.add(new BarEntry(8980f, 1));
        entries.add(new BarEntry(6045f, 2));
        entries.add(new BarEntry(12916f, 3));
        entries.add(new BarEntry(18405f, 4));
        entries.add(new BarEntry(9240f, 5));

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

        BarChart chart = new BarChart(getApplicationContext());
        setContentView(chart);
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.setDescription("# of times ");
        chart.animateY(3000);*/
    }

    public boolean onCreateOptionsMenu (Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater menutanim = getMenuInflater();
        menutanim.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.exit:
                startActivity (new Intent(getApplicationContext(),LoginActivity.class));
                return true;
            case R.id.action_share:

                view = adapter.tab1.chart;
                ShareScreenshot ss=new ShareScreenshot(ReportActivity.this);
                ss.shareImage(view);
                return true;

        }
        return false;
    }

}
