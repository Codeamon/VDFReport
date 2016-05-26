package com.ayoka.vdfreport;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoka.Adapters.MainActivityAdapter;
import com.ayoka.Interfaces.InterfaceController;
import com.ayoka.Model.CategoryReportModel;
import com.ayoka.Model.DepartmanModel;
import com.ayoka.Model.ResponseMessage;
import com.ayoka.common.Constants;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ahmetyildirim on 24.5.2016.
 */
public class MainNewActivity  extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RestAdapter restAdapter;
    private InterfaceController restInterface;
    private Toolbar toolbar;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private TextView txtFullName;
    private TextView txtMail;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        txtFullName= (TextView)headerView.findViewById(R.id.txtfullname);
        txtMail= (TextView)headerView.findViewById(R.id.txtMail);


//        txtFullName= (TextView) findViewById(R.id.txtfullname);
//        txtMail= (TextView) findViewById(R.id.txtMail);
        final Bundle extras = getIntent().getExtras();
        String userId="0";
        if(extras!=null) {
            txtFullName.setText(extras.getString("FullName"));
            txtMail.setText(extras.getString("Mail"));
            userId=extras.getString("UserId");
        }

        final ArrayList<String> mainReportsList = new ArrayList<String>();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.URL)
                .build();

        restInterface = restAdapter.create(InterfaceController.class);
        progressDialog = new ProgressDialog(MainNewActivity.this);
        progressDialog.setMessage("Yükleniyor..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        restInterface.GetDepartments(userId,new Callback<ResponseMessage<DepartmanModel[]>>() {
            @Override
            public void success(ResponseMessage<DepartmanModel[]> responseMessage, Response response) {


                progressDialog.cancel();
                final DepartmanModel[] depList = responseMessage.getMessage();
                    MainActivityAdapter adapter = new
                            MainActivityAdapter(MainNewActivity.this, depList);
                    list=(ListView)findViewById(R.id.menu_list);
                    list.setAdapter(adapter);
//                setListViewHeightBasedOnChildren(list);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                        int position, long id) {

                            if(id==R.id.exit)
                            {
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }
                            DepartmanModel model = depList[position];
                            Intent intent = new Intent(getApplicationContext(), TitleListActivity.class);
                            intent.putExtra("departmentId", model.getId());
                            intent.putExtra("mainCategoryId", 0);
//                            intent.putExtra("IsDealer", extras.getBoolean("IsDealer"));
//                            if(extras.getBoolean("IsDealer"))
//                            {
//                                intent.putExtra("DealerId", extras.getInt("DealerId"));
//                            }
                            intent.putExtras(extras);
                            startActivity(intent);

                        }
                });
            }
            @Override
            public void failure(RetrofitError retrofitError) {
                retrofitError.printStackTrace(); //to see if you have errors
                String merror = retrofitError.getMessage();
                Toast.makeText(getApplicationContext(),merror,Toast.LENGTH_LONG).show();
            }
        });







    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Programdan Çıkılsın Mı?").setCancelable(false).setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int id) { //Eğer evet butonuna basılırsa
                    dialog.dismiss();
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Programdan çıkmaktan vazgeçtiniz.", Toast.LENGTH_SHORT).show();
                }
            });
            alertDialogBuilder.create().show();
//            super.onBackPressed();
        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_exit) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        } else if (id == R.id.nav_report) {
            startActivity(new Intent(getApplicationContext(), MainNewActivity.class));
        } else if (id == R.id.nav_add) {

        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
