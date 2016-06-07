package com.ayoka.vdfreport;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoka.Adapters.ChartDataAdapter;
import com.ayoka.Adapters.ReportPagerAdapter;
import com.ayoka.Charts.BarChartModel;
import com.ayoka.Charts.HorizontalBarChartModel;
import com.ayoka.Charts.MultiLineChartModel;
import com.ayoka.Charts.listviewitems.HorizontalBarChartItem;
import com.ayoka.Interfaces.InterfaceController;
import com.ayoka.Model.DepartmanModel;
import com.ayoka.Model.FilterList;
import com.ayoka.Model.ReportFilter;
import com.ayoka.Model.ReportInfoResponse;
import com.ayoka.Model.ReportList;
import com.ayoka.Model.ReportRequest;
import com.ayoka.Model.ReportResponse;
import com.ayoka.Model.ResponseMessage;
import com.ayoka.common.Constants;
import com.ayoka.Charts.listviewitems.BarChartItem;
import com.ayoka.Charts.listviewitems.ChartItem;
import com.ayoka.Charts.listviewitems.LineChartItem;
import com.ayoka.fragments.FilterAlertDialogFragment;
import com.ayoka.fragments.FilterDialogFragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.app.Fragment;
import android.app.FragmentManager;

import com.melnykov.fab.ObservableScrollView;
import com.melnykov.fab.ScrollDirectionListener;
/**
 * Created by Ahmet&Korcan on 23.4.2016.
 * Deneme
 */
public class ReportActivity extends AppCompatActivity implements FilterDialogFragment.UserNameListener {

    private RestAdapter restAdapter;
    private InterfaceController restInterface;
    private ProgressDialog progressDialog;
    protected BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private Typeface mTf;
    public View view;
    public ResponseMessage<ReportResponse> finalResponse;
    public List<com.ayoka.Model.ReportList>  finalResponseMessageList;
    private int reportDetailId = 0;
    ReportPagerAdapter adapter;
    ChartDataAdapter cda;
    public ReportRequest rRequest = new ReportRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_report);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Bundle extras = getIntent().getExtras();
        ReportInfoResponse[] response;
        //if (extras != null) {
          //  reportDetailId = extras.getInt("reportId");
            response =(ReportInfoResponse[])getIntent().getSerializableExtra("reportInfoResponse");
        //}
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        if( response == null)
        {

            new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
                    .setTitle("Üzgünüz!")
                    .setMessage("Bu menü adımına tanımlanmış bir rapor içeriği bulunamadı...")
                    .setCancelable(false)
                    .setPositiveButton("Geri", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Whatever...
                        }
                    }).create().show();
            return;
        }

        ReportInfoResponse firstResponse = response[0];
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.URL)
                .build();

        restInterface = restAdapter.create(InterfaceController.class);


        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        //Dummy Set codes/////////////////

        List<FilterList> fList=  new ArrayList<FilterList>();

        if(getIntent().getSerializableExtra("reportFilterRequest")!=null)
        {
            rRequest=(ReportRequest)getIntent().getSerializableExtra("reportFilterRequest");
        }
        else {
            rRequest.setReportMainId(firstResponse.getReportMainId());
            List<ReportFilter> filters = firstResponse.getReportFilters();

            for (ReportFilter filter : filters) {
                if (filter.getFilterRequired() && filter.getDefaultValue() != "") {
//                FilterList f = new FilterList();
//                f.setFilterName(filter.getFilterColumn());
//                if(filter.getFilterTypeId()==3)
//                {
//                    Calendar c = Calendar.getInstance();
//                    f.setFilterValue(c.add(Calendar.DATE, Integer.parseInt(filter.getDefaultValue())));
//                }
//                else
//                {
//                    f.setFilterValue(filter.getDefaultValue());
//                }
//                fList.add(f);
                }

            }
        }

        //rRequest.setFilterList(fList);
        /////////////////////////////////


        progressDialog = new ProgressDialog(ReportActivity.this);
        progressDialog.setMessage("Raporlar yükleniyor...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        restInterface.getreport(rRequest, new Callback<ResponseMessage<ReportResponse>>() {
            @Override
            public void success(ResponseMessage<ReportResponse> reportResponse, Response response) {
                progressDialog.cancel();

                tabLayout.addTab(tabLayout.newTab().setText(""));
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                if(reportResponse.getMessage().getReportList().size() > 1) {

                    //Normalde report_acvity de var. Çoklu olunca listview itemlerin kendi içinde
                    //başlıkları olduğu için yer kaplamaması adına invisible yapılıyor
                    final TextView tv = (TextView) findViewById(R.id.textView) ;
                    tv.setVisibility(View.INVISIBLE);
                    tv.setHeight(0);
                    final ListView lv = (ListView) findViewById(R.id.listView1) ;
                    finalResponseMessageList=reportResponse.getMessage().getReportList();

                    cda = new ChartDataAdapter(getApplicationContext(), GetChartList(finalResponseMessageList));

                    lv.setAdapter(cda);
                    com.melnykov.fab.FloatingActionButton fab = (com.melnykov.fab.FloatingActionButton) findViewById(R.id.fab);
                    fab.attachToListView(lv, new ScrollDirectionListener() {
                        @Override
                        public void onScrollDown() {
                            Log.d("ListViewFragment", "onScrollDown()");
                        }

                        @Override
                        public void onScrollUp() {
                            Log.d("ListViewFragment", "onScrollUp()");
                        }
                    }, new AbsListView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(AbsListView view, int scrollState) {
                            Log.d("ListViewFragment", "onScrollStateChanged()");
                        }

                        @Override
                        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                            Log.d("ListViewFragment", "onScroll()");
                        }
                    });
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showAlert();
//                            FragmentManager manager = getFragmentManager();
//                            FilterAlertDialogFragment alertDialogFragment = new FilterAlertDialogFragment();
//                            alertDialogFragment.show(manager, "fragment_filter");
                        }
                    });

                }
                else {
                    final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                    adapter = new ReportPagerAdapter
                            (getSupportFragmentManager(), tabLayout.getTabCount(), reportResponse.getMessage().getReportList());
                    final TextView tv = (TextView) findViewById(R.id.textView) ;
                    tv.setText(reportResponse.getMessage().getReportList().get(0).getDescription());
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


    public void GetReport()
    {
        restInterface.getreport(rRequest, new Callback<ResponseMessage<ReportResponse>>() {
            @Override
            public void success(ResponseMessage<ReportResponse> reportResponse, Response response) {

                finalResponse= reportResponse;
                finalResponseMessageList=reportResponse.getMessage().getReportList();
            }
            @Override
            public void failure(RetrofitError retrofitError) {

                retrofitError.printStackTrace(); //to see if you have errors
                String merror = retrofitError.getMessage();
                Toast.makeText(getApplicationContext(), merror, Toast.LENGTH_LONG).show();
                finalResponse=new ResponseMessage<ReportResponse>();
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
                    //list.add(new HorizontalBarChartItem(new HorizontalBarChartModel().getBarData(reportDetail.get(i)), getApplicationContext(),reportDetail.get(i).getDescription()));
                    list.add(new BarChartItem(new BarChartModel().getBarData(reportDetail.get(i)), getApplicationContext(),reportDetail.get(i).getDescription()));
                case 2:
                    break;
                case 3:
                    list.add(new BarChartItem(new BarChartModel().getBarData(reportDetail.get(i)), getApplicationContext(),reportDetail.get(i).getDescription()));
                    break;
                case 4:
                    list.add(new LineChartItem(new MultiLineChartModel().getBarData(reportDetail.get(i)), getApplicationContext(),reportDetail.get(i).getDescription()));
                    break;
                case 5:
                    list.add(new HorizontalBarChartItem(new HorizontalBarChartModel().getBarData(reportDetail.get(i)), getApplicationContext(),reportDetail.get(i).getDescription()));
                    break;
                default:
                    break;
            }
        }
        return list;
    }

    @Override
    public void onFinishUserDialog(String user) {
        Toast.makeText(this, "Hello, " + user, Toast.LENGTH_SHORT).show();
    }
    public void onClick(View view) {
        // close existing dialog fragments
        FragmentManager manager = getFragmentManager();
        Fragment frag = manager.findFragmentByTag("fragment_filter");
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }
//        switch (view.getId()) {
////            case R.id.showCustomFragment:
////                FilterDialogFragment editNameDialog = new FilterDialogFragment();
////                editNameDialog.show(manager, "fragment_filter");
////                break;
////            case R.id.showAlertDialogFragment:
////                FilterAlertDialogFragment alertDialogFragment = new FilterAlertDialogFragment();
////                alertDialogFragment.show(manager, "fragment_filter");
////                break;
//        }
    }

    private void showAlert() {

        ReportInfoResponse[] response =(ReportInfoResponse[])getIntent().getSerializableExtra("reportInfoResponse");
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final LinearLayout layout       = new LinearLayout(this);
        TextView tvMessage        = new TextView(this);
        final EditText etInput    = new EditText(this);

        final ReportInfoResponse reportInfo = response[0];
        List<ReportFilter> filterList = reportInfo.getReportFilters();
        for (int i=0; i<filterList.size(); i++) {

            ReportFilter filter=filterList.get(i);
            etInput.setSingleLine();
            layout.setOrientation(LinearLayout.VERTICAL);
            switch (filter.getFilterTypeId())
            {
                case 1://int
                    final NumberPicker picker = new NumberPicker(this);
                    final EditText etInt  = new EditText(this);
                    final TextInputLayout etLayoutInt  = new TextInputLayout(this);
                    etInt.setText(filter.getDefaultValue());
                    etInt.setInputType(InputType.TYPE_CLASS_NUMBER);
                    etLayoutInt.addView(etInt);
                    etLayoutInt.setHint(filter.getFilterColumn());
                    etLayoutInt.setPadding(50,40,50,0);
                    layout.addView(etLayoutInt);
                    break;
                case 2://string

                    final EditText etStr  = new EditText(this);
                    final TextInputLayout etLayoutStr  = new TextInputLayout(this);
                    etStr.setText(filter.getDefaultValue());
                    etLayoutStr.addView(etStr);
                    etLayoutStr.setHint(filter.getFilterColumn());
                    etLayoutStr.setPadding(50,40,50,0);
                    layout.addView(etLayoutStr);
                    break;
                case 3://date
                    final DatePickerDialog fromDatePickerDialog;
                    final EditText etDate  = new EditText(this);
                    final TextInputLayout etDateLayout  = new TextInputLayout(this);

                    final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

                    Calendar newCalendar = Calendar.getInstance();
                    fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Calendar newDate = Calendar.getInstance();
                            newDate.set(year, monthOfYear, dayOfMonth);
                            etDate.setText(dateFormatter.format(newDate.getTime()));
                        }

                    },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                    final Calendar c = Calendar.getInstance();
                    if(rRequest.getFilterList()!=null && rRequest.getFilterList().size()>0 && rRequest.getFilterList().get(i)!=null)
                    {
                        Date date;
                        try {
                            date = dateFormatter.parse(rRequest.getFilterList().get(i).getFilterValue());
                            c.setTime(date);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    else
                    {
                        c.add(Calendar.DATE, Integer.parseInt(filter.getDefaultValue()));
                    }
                    etDate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fromDatePickerDialog.updateDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
                            fromDatePickerDialog.show();
                        }
                    });

                    etDate.setText(dateFormatter.format(c.getTime()));
                    etDateLayout.addView(etDate);
                    etDateLayout.setHint(filter.getFilterColumn());
                    etDateLayout.setPadding(50,40,50,0);
                    layout.addView(etDateLayout);
                    break;
                case 4://bool
                    break;
            }


        }
        tvMessage.setText("Enter name:");
//        layout.addView(tvMessage);
//        layout.addView(etInput);
        alert.setTitle("Lütfen Filtre Seçiniz");
        alert.setView(layout);

        alert.setNegativeButton("İptal", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alert.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                int childcount = layout.getChildCount();
                String result = "";
                rRequest.getFilterList().clear();
                for (int i=0; i < childcount; i++){
                    TextInputLayout v = (TextInputLayout)layout.getChildAt(i);
                    result+=reportInfo.getReportFilters().get(i).getFilterColumn();
                    FilterList filter = new FilterList();
                    filter.setFilterValue(((TextView)v.getChildAt(0)).getText().toString());
                    filter.setFilterName(reportInfo.getReportFilters().get(i).getFilterColumn());
                    rRequest.getFilterList().add(filter);
                }

                Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                intent.putExtra("reportInfoResponse", (ReportInfoResponse[])getIntent().getSerializableExtra("reportInfoResponse"));
                intent.putExtra("reportFilterRequest", rRequest);

                finish();
                startActivity(intent);
//                GetReport();
              //  adapter.notifyDataSetChanged();

             //   cda = new ChartDataAdapter(getApplicationContext(), GetChartList(finalResponseMessageList));
             //   cda.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();
    }

}
