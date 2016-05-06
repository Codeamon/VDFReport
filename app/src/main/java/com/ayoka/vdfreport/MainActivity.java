package com.ayoka.vdfreport;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ayoka.Adapters.MainActivityAdapter;

import com.ayoka.Helper.ShareScreenshot;
import com.ayoka.Interfaces.InterfaceController;
import com.ayoka.Model.DepartmanModel;
import com.ayoka.common.Constants;
import com.ayoka.common.JsonOperations;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    ListView list;
    private RestAdapter restAdapter;
    private InterfaceController restInterface;
    private Toolbar toolbar;
    private ProgressDialog progressDialog;
    Integer[] imageId = {
            R.drawable.finance,
            R.drawable.insurance,
            R.drawable.faktoringg,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        final ArrayList<String> mainReportsList = new ArrayList<String>();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.URL)
                .build();

        restInterface = restAdapter.create(InterfaceController.class);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Yükleniyor..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        restInterface.GetDepartments("1",new Callback<DepartmanModel[]>() {
            @Override
            public void success(DepartmanModel[] departmanModels, Response response) {

                for (DepartmanModel departmanModel : departmanModels) {
                    mainReportsList.add(departmanModel.getDepartmentName());
                }
                progressDialog.cancel();

                MainActivityAdapter adapter = new
                        MainActivityAdapter(MainActivity.this, mainReportsList, imageId);
                list=(ListView)findViewById(R.id.menu_list);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        if(id==R.id.exit)
                        {
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }

                        Intent intent = new Intent(getApplicationContext(), TitleListActivity.class);
                        intent.putExtra("currentProjectId", position+1);
                        intent.putExtra("currentMainCategoryId", position+1);
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
        if(id==R.id.action_share)
        {
            share();
        }
        return super.onOptionsItemSelected(item);
    }
    public void share(){//Share butonu tıklandığında çalışır
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); //Share intentini oluşturuyoruz
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Mesaj Konu");//share mesaj konusu
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "içerik");//share mesaj içeriği
        startActivity(Intent.createChooser(sharingIntent, "Paylaşmak İçin Seçiniz"));//Share intentini başlığı ile birlikte başlatıyoruz
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            try {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this); //Mesaj Penceresini Yaratalım

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
                return super.onKeyDown(keyCode, event);
            } catch (IllegalStateException e) {  //yapımızı try-catch blogu içerisine aldık
                e.printStackTrace();
            }
        }
        return true;
    }

    private void hide(final View view) {

        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

        int initialRadius = view.getWidth();
        //Verilen koordinatlara göre daire seklinde gizleme işlemini yapan animasyon olusturuyoruz
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy,
                initialRadius, 0);
        anim.setDuration(1000);

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //view elementini gizle komutu verdim
                view.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }

}
