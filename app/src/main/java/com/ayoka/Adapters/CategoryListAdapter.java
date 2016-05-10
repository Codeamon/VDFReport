package com.ayoka.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayoka.Model.Category;
import com.ayoka.Model.CategoryReportModel;
import com.ayoka.vdfreport.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ahmetyildirim on 1.5.2016.
 */
public class CategoryListAdapter  extends RecyclerView.Adapter<CategoryListRowHolder> {
    public void setCategoryList(List<CategoryReportModel> categoryList) {
        this.categoryList = categoryList;
    }

    // Set numbers of List in RecyclerView.
    private List<CategoryReportModel> categoryList;
    private Context context;
    public CategoryListAdapter(Context context, ArrayList<CategoryReportModel> categoryList) {
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
        CategoryReportModel category = categoryList.get(position);
        holder.title.setText(category.getCategoryReportname());
        holder.desc.setText(category.getCategoryReportname());
        if(category.getType()==1)
        {
            holder.avatar.setImageResource(R.drawable.list_item);
        }
        else
        {
            holder.avatar.setImageResource(R.drawable.ic_folder_open_black_24dp);
        }

    }
    @Override
    public int getItemCount() {
        return (null != categoryList ? categoryList.size() : 0);
    }
    public void setFilter(ArrayList<CategoryReportModel> countryModels) {
        categoryList = new ArrayList<>();
        categoryList.addAll(countryModels);
        this.notifyDataSetChanged();
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
