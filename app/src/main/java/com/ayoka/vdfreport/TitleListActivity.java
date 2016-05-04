package com.ayoka.vdfreport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ayoka.Adapters.CategoryListAdapter;

import com.ayoka.Listener.RecyclerTouchListener;
import com.ayoka.Model.Category;
import com.ayoka.common.JsonOperations;

import java.util.ArrayList;

/**
 * Created by ahmetyildirim on 3.5.2016.
 */
public class TitleListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressDialog pDialog;
    private CategoryListAdapter adapter;
    public ArrayList<Category> categoryList = new ArrayList<Category>();

    private int currentProjectId = 0;
    private int currentMainCategoryId=0;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_list);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentProjectId = extras.getInt("currentProjectId");
            currentMainCategoryId = extras.getInt("currentMainCategoryId");
        }

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        categoryList=new JsonOperations().GetListByCategory(currentMainCategoryId,currentMainCategoryId);
        adapter = new CategoryListAdapter(this, categoryList);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();

        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Integer id = categoryList.get(position).getId();
                ArrayList<Category> categoryList = new ArrayList<Category>();
                categoryList=new JsonOperations().GetListByCategory(id,currentProjectId);

                if(categoryList.size()==0)
                {
                    Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                    intent.putExtra("currentProjectId", currentProjectId);
                    intent.putExtra("currentMainCategoryId", id);
                    startActivity(intent);

                }
                else
                {
                    adapter.setCategoryList(categoryList);
                    Integer count = adapter.getItemCount();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "uzun tıkladın", Toast.LENGTH_SHORT).show();
            }
        }));
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

        return super.onOptionsItemSelected(item);
    }
}
