package com.example.chaithra.yelpnew;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class PlaceAdapter extends ArrayAdapter<Place> {
    public PlaceAdapter(Context context,int resource) {

        super(context, 0);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Place item = getItem(position);
        ViewHolder holder;
        if (v == null) {
            holder = new ViewHolder();
            v = LayoutInflater.from(getContext()).inflate(R.layout.activity_list, parent, false);
            holder.name = (TextView) v.findViewById(R.id.name);
            holder.rating = (TextView) v.findViewById(R.id.rating);

            v.setTag(holder);

        } else {
            // view already exists, get the holder instance from the view
            holder = (ViewHolder) v.getTag();
        }

        holder.name.setText(item.getName());
        holder.rating.setText(item.getRating());

        return v;
    }

    private class ViewHolder {
        TextView name;
        TextView rating;


    }


}


