package com.example.shubhangi.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shubhangi.moviesapp.Models.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {
    ImageView poster,trailer;
    TextView release,title,overview,ratings,reviews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        title = (TextView) findViewById(R.id.title);
        poster=(ImageView)findViewById(R.id.poster);
        release = (TextView) findViewById(R.id.released);
        overview = (TextView) findViewById(R.id.overview);
        ratings= (TextView) findViewById(R.id.rating);
        reviews=(TextView)findViewById(R.id.reviews);
        trailer=(ImageView)findViewById(R.id.trailer);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("movie_obj");

        Movie obj = (Movie) b.getSerializable("object");

        if(obj.getMurl()!="") Picasso.with(this).load("https://image.tmdb.org/t/p/w185"+obj.getMurl()).into(poster);
//        Picasso.with(this).load("https://image.tmdb.org/t/p/w185"+obj.getMurl()).into(trailer);

        title.setText(obj.getMtitle());
        release.setText(obj.getMrelease_date());
        ratings.setText(obj.getMvote());
        overview.setText(obj.getMoverview());


    }
}
