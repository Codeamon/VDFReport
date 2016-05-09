package com.ayoka.vdfreport;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ayoka.Adapters.ReportInsComPagerAdater;
import com.ayoka.Adapters.ReportPagerAdapter;
import com.github.mikephil.charting.charts.BarChart;

/**
 * Created by ahmetyildirim on 28.4.2016.
 */
public class InsComReportActivity extends AppCompatActivity {
    protected BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private Typeface mTf;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_commission_report);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Yıllık"));
        tabLayout.addTab(tabLayout.newTab().setText("Aylık"));
        tabLayout.setTabGravity(TabLayout.MODE_SCROLLABLE);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final ReportInsComPagerAdater adapter = new ReportInsComPagerAdater
                (getSupportFragmentManager(), tabLayout.getTabCount());
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
//            case R.id.action_home:
//                startActivity (new Intent(getApplicationContext(),MainActivity.class));
//                return true;

        }
        return false;
    }

}
