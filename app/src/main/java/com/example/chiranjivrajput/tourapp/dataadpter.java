package com.example.chiranjivrajput.tourapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chiranjiv Rajput on 17-01-2018.
 */

public class dataadpter extends ArrayAdapter<data> {
    int background;
    public dataadpter(Activity context, ArrayList<data> d,int color){
        super(context,0,d);
        background=color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listitemview=convertView;
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.data, parent, false);
        }
        data currentword=getItem(position);
        TextView name=(TextView) listItemView.findViewById(R.id.abc);
        name.setText(currentword.showname());
        TextView rating=(TextView)listItemView.findViewById(R.id.abc1);
        rating.setText(Float.toString(currentword.showrating()));
        TextView desc=(TextView)listItemView.findViewById(R.id.desc);
        desc.setText(currentword.showdesc());
        ImageView img=(ImageView) listItemView.findViewById(R.id.img);
        img.setImageResource(currentword.showimageresourceid());
        View v=listItemView.findViewById(R.id.ab);
        int color= ContextCompat.getColor(getContext(),background);
        v.setBackgroundColor(color);
        return listItemView;
    }
}
