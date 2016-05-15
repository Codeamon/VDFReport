package com.ayoka.Adapters;

import android.app.Activity;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayoka.vdfreport.R;

import java.util.ArrayList;

/**
 * Created by ahmetyildirim on 27.4.2016.
 */
public class MainActivityAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> web;
    public  MainActivityAdapter(Activity context,
                      ArrayList<String> web) {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.list_single, null, true);
            TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
            txtTitle.setText(web.get(position));
            imageView.setImageResource(R.drawable.ic_folder_open_black_18dp);
            return rowView;
    }
}
