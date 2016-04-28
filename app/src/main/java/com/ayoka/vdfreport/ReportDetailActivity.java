package com.ayoka.vdfreport;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ayoka.Adapters.ReportPagerAdapter;
import com.ayoka.test.TestClass_ReportTab1;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Ahmet&Korcan on 23.4.2016.
 * Deneme
 */
public class ReportDetailActivity extends AppCompatActivity {
    protected BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private Typeface mTf;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String value="0";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             value = extras.getInt("SelectedIndex")+"";
        }
        TestClass_ReportTab1 tc = new TestClass_ReportTab1();
        int totalValue = tc.getValueById(Integer.parseInt(value));

        float dW = totalValue/10 *9;
        float dS = totalValue/10 *0.7f;
        float dM = totalValue/10 *0.3f;
        ArrayList<View> entries_view = new ArrayList<>();

        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(dW, 0));
        entries.add(new Entry(dS, 1));
        entries.add(new Entry(dM, 2));

        PieDataSet dataset = new PieDataSet(entries, "# Toplam 6212");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        dataset.setSliceSpace(2f);
        dataset.setColors(ColorTemplate.VORDIPLOM_COLORS);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Volkswagen");
        labels.add("Scania");
        labels.add("Man");

        PieChart pChart = new PieChart(getApplicationContext());
        PieData pdata = new PieData(labels, dataset);
        pChart.setData(pdata);
        pChart.setDescription("Krediler "+tc.getDataById(Integer.parseInt(value))+" Detayı");

        pChart.animateY(3000);

        setContentView(pChart);



    }



}
