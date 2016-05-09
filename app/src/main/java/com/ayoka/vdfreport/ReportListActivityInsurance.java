package com.ayoka.vdfreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ayoka.Adapters.MainActivityAdapter;
import com.ayoka.Adapters.ReportListAdapter;

/**
 * Created by ahmetyildirim on 28.4.2016.
 */
public class ReportListActivityInsurance extends AppCompatActivity {
    ListView list;

    private Toolbar toolbar;
    String[] web = {
            "Bayi Komisyon Rapoları",
            "Bayi Prim Raporları",
    } ;
    Integer[] imageId = {
            R.drawable.list_item,
            R.drawable.list_item,

    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list_insurance);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ReportListAdapter adapter = new
                ReportListAdapter(ReportListActivityInsurance.this, web, imageId);
        list=(ListView)findViewById(R.id.report_menu_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if(id==R.id.exit)
                {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                switch (position)
                {
                    case 0:
                        startActivity(new Intent(getApplicationContext(), InsComReportActivity.class));
                        break;
//                  Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

                    case 1:
                        startActivity(new Intent(getApplicationContext(), InsComReportActivity.class));
                        break;
                }

//                Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

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
//            case R.id.action_home:
//                startActivity (new Intent(getApplicationContext(),MainActivity.class));
//                return true;

        }
        return false;
    }
}
