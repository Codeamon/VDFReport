package com.ayoka.Adapters;

import android.app.Activity;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayoka.Model.DepartmanModel;
import com.ayoka.common.Constants;
import com.ayoka.vdfreport.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmetyildirim on 27.4.2016.
 */
public class MainActivityAdapter extends ArrayAdapter<DepartmanModel> {

    private final Activity context;
    private List<DepartmanModel> departmanModels;
    public  MainActivityAdapter(Activity context,
                                ArrayList<DepartmanModel> departmanModels) {
        super(context, R.layout.list_single, departmanModels);
        this.context = context;
        this.departmanModels = departmanModels;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);
        if(departmanModels.size()>position) {
            TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
            txtTitle.setText(departmanModels.get(position).getDepartmentName());
//            imageView.setImageResource(R.drawable.ic_folder_open_black_18dp);

            String logo = departmanModels.get(position).getLogoName();
            if (logo != null && logo != "") {
                Picasso.with(context).load(Constants.imagesURL + logo).into(imageView);
            } else {
                Picasso.with(context).load(Constants.imagesURL + "department.png").into(imageView);
            }
        }
        return rowView;
    }

    public void setFilter(ArrayList<DepartmanModel> countryModels) {
        departmanModels = new ArrayList<>();
        departmanModels.addAll(countryModels);
        this.notifyDataSetChanged();
    }
}
