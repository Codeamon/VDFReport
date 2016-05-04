package com.ayoka.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayoka.Model.Category;
import com.ayoka.vdfreport.R;

import java.util.List;


/**
 * Created by ahmetyildirim on 1.5.2016.
 */
public class CategoryListAdapter  extends RecyclerView.Adapter<CategoryListRowHolder> {
    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    // Set numbers of List in RecyclerView.
    private List<Category> categoryList;
    private Context context;
    public CategoryListAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public CategoryListRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
        CategoryListRowHolder categoryListRowHolder = new CategoryListRowHolder(view);
        return categoryListRowHolder;
    }
    @Override
    public void onBindViewHolder(CategoryListRowHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.title.setText(category.getTitle());
        holder.desc.setText(category.getDesc());

    }
    @Override
    public int getItemCount() {
        return (null != categoryList ? categoryList.size() : 0);
    }

}
class CategoryListRowHolder extends RecyclerView.ViewHolder {


    protected TextView title, desc;
    protected ImageView avatar;


    public CategoryListRowHolder(View itemView) {
        super(itemView);
        this.avatar = (ImageView) itemView.findViewById(R.id.list_avatar);
        this.title = (TextView) itemView.findViewById(R.id.list_title);
        this.desc = (TextView) itemView.findViewById(R.id.list_desc);
    }
}
