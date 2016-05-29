package com.ayoka.vdfreport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoka.Adapters.ChartDataAdapter;
import com.ayoka.Adapters.ReportPagerAdapter;
import com.ayoka.Charts.BarChartModel;
import com.ayoka.Interfaces.InterfaceController;
import com.ayoka.Model.FilterList;
import com.ayoka.Model.ReportList;
import com.ayoka.Model.ReportRequest;
import com.ayoka.Model.ReportResponse;
import com.ayoka.Model.ResponseMessage;
import com.ayoka.common.Constants;
import com.ayoka.Charts.listviewitems.BarChartItem;
import com.ayoka.Charts.listviewitems.ChartItem;
import com.ayoka.Charts.listviewitems.LineChartItem;
import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.List;

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
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.URL)
                .build();

        restInterface = restAdapter.create(InterfaceController.class);

        progressDialog = new ProgressDialog(ReportActivity.this);
        progressDialog.setMessage("Raporlar yükleniyor...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        //Dummy Set codes/////////////////
        ReportRequest rRequest = new ReportRequest();
        List<FilterList> fList=  new ArrayList<FilterList>();

        if(reportDetailId==2){
        FilterList f = new FilterList();
        f.setFilterName("StartDate");
        f.setFilterValue("1.1.2015");
        fList.add(f);
        FilterList f1 = new FilterList();
        f1.setFilterName("EndDate");
        f1.setFilterValue("3.3.2015");
        fList.add(f1);}
        //rRequest.setFilterList(fList);
        rRequest.setReportMainId(reportDetailId);
        /////////////////////////////////
        restInterface.getreport(rRequest, new Callback<ResponseMessage<ReportResponse>>() {
            @Override
            public void success(ResponseMessage<ReportResponse> reportResponse, Response response) {
                progressDialog.cancel();

                tabLayout.addTab(tabLayout.newTab().setText("Aylık Rapor"));
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                if(reportResponse.getMessage().getReportList().size() > 1) {
                   // adapter = new ReportPagerAdapter
                    //                       (getSupportFragmentManager(), 2, reportResponse.getMessage().getReportList());
                    //viewPager.setAdapter(adapter);
                    final ListView lv = (ListView) findViewById(R.id.listView1) ;
                    ChartDataAdapter cda = new ChartDataAdapter(getApplicationContext(), GetChartList(reportResponse.getMessage().getReportList()));
                    lv.setAdapter(cda);

                }
                else {
                    final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                    adapter = new ReportPagerAdapter
                            (getSupportFragmentManager(), tabLayout.getTabCount(), reportResponse.getMessage().getReportList());
                    viewPager.setAdapter(adapter);

                }

                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        //viewPager.setCurrentItem(tab.getPosition());
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
//            case R.id.action_share:
//
////                view = adapter.tab1;
////                ShareScreenshot ss=new ShareScreenshot(ReportActivity.this);
////                ss.shareImage(view);
//                return true;

        }
        return false;
    }

    private List<ChartItem> GetChartList(List<ReportList> reportDetail)
    {
        ArrayList<ChartItem> list = new ArrayList<ChartItem>();
        for (int i = 0; i < reportDetail.size(); i++) {

            switch (reportDetail.get(i).getChartType()) {
                case 1:
                    list.add(new BarChartItem(new BarChartModel().getBarData(reportDetail.get(i)), getApplicationContext()));
                    list.add(new BarChartItem(new BarChartModel().getBarData(reportDetail.get(i)), getApplicationContext()));
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        }
        return list;
    }

}
