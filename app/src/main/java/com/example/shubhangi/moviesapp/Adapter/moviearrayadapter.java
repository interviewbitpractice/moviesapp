package com.example.shubhangi.moviesapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.shubhangi.moviesapp.Models.Movie;
import com.example.shubhangi.moviesapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shubhangi on 3/27/2016.
 */
public class moviearrayadapter extends ArrayAdapter<Movie> {

    Context context;
    ArrayList<Movie> movieslist;


    public moviearrayadapter(Context context, ArrayList<Movie> objects) {
        super(context, 0, objects);    // 0 --- means no layout id by default
        this.context = context;
        movieslist = objects;
    }

    public static class ViewHolder{           //
        ImageView img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {    // in built---apne aap call hoga

        if(convertView == null){    // sirf content change hona hai view nhi -- optimisation
            convertView = View.inflate(context, R.layout.grid_layout,null);
            ViewHolder vh = new ViewHolder();
            vh.img = (ImageView) convertView.findViewById(R.id.imageView);

            convertView.setTag(vh);        // attach viewholder with convertview
        }

        ViewHolder vh = (ViewHolder)convertView.getTag();   // if not created then just fetch it
        Movie s = movieslist.get(position);

        //vh.img.setText(s.getMtitle() + "hii");
        Picasso.with(context).load("https://image.tmdb.org/t/p/w185"+s.getMurl()).fit().into(vh.img);

        return convertView;
    }
}
