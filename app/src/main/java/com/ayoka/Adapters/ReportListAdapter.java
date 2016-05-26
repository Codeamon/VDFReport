package com.ayoka.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayoka.vdfreport.R;

/**
 * Created by ahmetyildirim on 28.4.2016.
 */
public class ReportListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    public ReportListAdapter(Activity context,
                               String[] web, Integer[] imageId) {
        super(context, R.layout.list_default_size, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_default_size, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);
//        Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
