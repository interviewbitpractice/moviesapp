package com.example.shubhangi.moviesapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubhangi.moviesapp.Models.Movie;
import com.example.shubhangi.moviesapp.Models.Trailer;
import com.example.shubhangi.moviesapp.Models.TrailerResponse;
import com.example.shubhangi.moviesapp.network.ApiClient;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetail extends AppCompatActivity {
    ImageView poster,trailer;
    TextView release,title,overview,ratings,reviews;
    Movie obj;
    TrailerResponse response1;
    Trailer[] array  = new Trailer[2];

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

        obj = (Movie) b.getSerializable("object");

        if(obj.getMurl()!="") Picasso.with(this).load("https://image.tmdb.org/t/p/w185"+obj.getMurl()).into(poster);
//        Picasso.with(this).load("https://image.tmdb.org/t/p/w185"+obj.getMurl()).into(trailer);

        title.setText(obj.getMtitle());
        release.setText(obj.getMrelease_date());
        ratings.setText(obj.getMvote());
        overview.setText(obj.getMoverview());

        Picasso.with(this).load("https://image.tmdb.org/t/p/w185"+obj.getMurl()).into(trailer);

        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<TrailerResponse> trail = ApiClient.getInterface().getTrailer(obj.getMid(),MainActivity.api_key);

                trail.enqueue(new Callback<TrailerResponse>() {
                    @Override
                    public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                        if (response.isSuccessful()) {
                            response1 = response.body();
                            array[0] = response1.getTresponse()[0];
                            array[1] = response1.getTresponse()[1];

                            Intent i = new Intent();
                            i.setAction(Intent.ACTION_VIEW);
                            i.setData(Uri.parse("https://www.youtube.com/watch?v=12"));
                            startActivity(i);

                        } else {
                            Toast.makeText(MovieDetail.this,
                                    "Trailer not available...!!!", Toast.LENGTH_LONG).show();
                       }
                    }

                    @Override
                    public void onFailure(Call<TrailerResponse> call, Throwable t) {
                        Toast.makeText(MovieDetail.this,
                                "Error occured.", Toast.LENGTH_LONG).show();
                    }
                });

                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(""));
                startActivity(i);
            }
        });
    }
}
