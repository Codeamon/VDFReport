package com.ayoka.vdfreport;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_list);
        categoryList=new JsonOperations().GetListByCategory(0);
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
                categoryList=new JsonOperations().GetListByCategory(id);

                adapter.setCategoryList(categoryList);
                Integer count = adapter.getItemCount();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "uzun tıkladın", Toast.LENGTH_SHORT).show();
            }
        }));
    }

}
