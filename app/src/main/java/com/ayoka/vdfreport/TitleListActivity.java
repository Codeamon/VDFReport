package com.ayoka.vdfreport;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ayoka.Adapters.CategoryListAdapter;

import com.ayoka.Adapters.MainActivityAdapter;
import com.ayoka.Interfaces.InterfaceController;
import com.ayoka.Listener.RecyclerTouchListener;
import com.ayoka.Model.Category;
import com.ayoka.Model.CategoryReportModel;
import com.ayoka.Model.DepartmanModel;
import com.ayoka.common.Constants;
import com.ayoka.common.JsonOperations;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ahmetyildirim on 3.5.2016.
 */
public class TitleListActivity extends AppCompatActivity {

    private RestAdapter restAdapter;
    private InterfaceController restInterface;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private ProgressDialog pDialog;
    private CategoryListAdapter adapter;
    public ArrayList<CategoryReportModel> categoryList = new ArrayList<CategoryReportModel>();

    private int departmentId = 0;
    private int mainCategoryId=0;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_list);


//        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setTitle("Collapsing");
//        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
//        collapsingToolbarLayout.setContentScrimColor(Color.BLUE);
//        collapsingToolbarLayout.setStatusBarScrimColor(Color.GREEN);
//        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                int primaryDark = getResources().getColor(R.color.primary_dark);
//                int primary = getResources().getColor(R.color.primary);
//                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
//                collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkVibrantColor(primaryDark));
//            }
//        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            departmentId = extras.getInt("departmentId");
            mainCategoryId = extras.getInt("mainCategoryId");
        }

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_folder_open_black_18dp);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setDisplayShowHomeEnabled(true);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.URL)
                .build();

        restInterface = restAdapter.create(InterfaceController.class);

        progressDialog = new ProgressDialog(TitleListActivity.this);
        progressDialog.setMessage("Yükleniyor..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        final Context context = this;
       if (mainCategoryId == 0){
            restInterface.GetCategoryReportList(Integer.toString(departmentId), new Callback<CategoryReportModel[]>() {
                @Override
                public void success(CategoryReportModel[] categoryReportModels, Response response) {

                    for (CategoryReportModel categoryReportModel : categoryReportModels) {
                        categoryList.add(categoryReportModel);
                    }
                   progressDialog.cancel();
                    adapter = new CategoryListAdapter(context, categoryList);
                    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);

                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context, recyclerView, new RecyclerTouchListener.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {

                            CategoryReportModel model = categoryList.get(position);
                            if(model.getType()==1){
                                Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                                intent.putExtra("reportId", model.getId());
                                startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(getApplicationContext(), TitleListActivity.class);
                                intent.putExtra("departmentId", departmentId);
                                intent.putExtra("mainCategoryId", model.getId());
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onLongClick(View view, int position) {
                            Toast.makeText(getApplicationContext(), "uzun tıkladın", Toast.LENGTH_SHORT).show();
                        }
                    }));
                }

                @Override
                public void failure(RetrofitError retrofitError) {

//                    progressDialog.cancel();
                    retrofitError.printStackTrace(); //to see if you have errors
                    String merror = retrofitError.getMessage();
                    Toast.makeText(getApplicationContext(), merror, Toast.LENGTH_LONG).show();
                }
            });
        }
        else
        {
            restInterface.GetSubCategoryReportList(Integer.toString(mainCategoryId), new Callback<CategoryReportModel[]>() {
                @Override
                public void success(CategoryReportModel[] categoryReportModels, Response response) {

                    for (CategoryReportModel categoryReportModel : categoryReportModels) {
                        categoryList.add(categoryReportModel);
                    }
                    progressDialog.cancel();
                    adapter = new CategoryListAdapter(context, categoryList);
                    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);

                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context, recyclerView, new RecyclerTouchListener.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {

                            CategoryReportModel model = categoryList.get(position);
                            if(model.getType()==1){

                                Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                                intent.putExtra("reportId", model.getId());
                                startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(getApplicationContext(), TitleListActivity.class);
                                intent.putExtra("departmentId", departmentId);
                                intent.putExtra("mainCategoryId", model.getId());
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onLongClick(View view, int position) {
                            Toast.makeText(getApplicationContext(), "uzun tıkladın", Toast.LENGTH_SHORT).show();
                        }
                    }));


                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    retrofitError.printStackTrace(); //to see if you have errors
                    String merror = retrofitError.getMessage();
                    Toast.makeText(getApplicationContext(), merror, Toast.LENGTH_LONG).show();
                }
            });
        }
    }




    private void SetLayout()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.exit) {

            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        if(id==R.id.action_home)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        if(id==android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
