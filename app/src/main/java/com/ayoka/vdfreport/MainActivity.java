package com.ayoka.vdfreport;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
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
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView image;
    AppCompatTextView textview;
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
        image = (ImageView) findViewById(R.id.image);
        image.setImageResource(R.drawable.avatar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        appBarLayout=(AppBarLayout)findViewById(R.id.app_bar_layout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("VDF Rapor");
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.dark_grey));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        setPalette();
        appBarLayout.setExpanded(false);
        textview=(AppCompatTextView)findViewById(R.id.txtfullname);

        final Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            textview.setText(extras.getString("FullName"));
        }
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


                progressDialog.cancel();
                Boolean isDealer=false;
                if(extras!=null && extras.getBoolean("IsDealer")) {

                    isDealer=true;
                    mainReportsList.add(extras.getString("DealerName"));
                    textview.setText(extras.getString("Username"));
                }
                else
                {
                    for (DepartmanModel departmanModel : departmanModels) {
                        mainReportsList.add(departmanModel.getDepartmentName());
                    }
                }
                MainActivityAdapter adapter = new
                        MainActivityAdapter(MainActivity.this, mainReportsList, imageId,isDealer);
                list=(ListView)findViewById(R.id.menu_list);
                list.setAdapter(adapter);
                setListViewHeightBasedOnChildren(list);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        if(id==R.id.exit)
                        {
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }

                        Intent intent = new Intent(getApplicationContext(), TitleListActivity.class);
                        intent.putExtra("departmentId", position+1);
                        intent.putExtra("mainCategoryId", 0);
                        intent.putExtra("IsDealer", extras.getBoolean("IsDealer"));
                        if(extras.getBoolean("IsDealer"))
                        {
                            intent.putExtra("DealerId", extras.getInt("DealerId"));
                        }
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
    private void setPalette() {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int primaryDark = getResources().getColor(R.color.colorPrimaryDark);
                int primary = getResources().getColor(R.color.colorPrimary);
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkVibrantColor(primaryDark));
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
        MenuItem itemHome = menu.findItem(R.id.action_home);
        itemHome.setVisible(false);
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
//        if(id==R.id.action_home)
//        {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//        }
//        if(id==R.id.action_share)
//        {
//            share();
//        }
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
    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
