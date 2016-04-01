package com.example.shubhangi.moviesapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubhangi.moviesapp.Models.Movie;
import com.example.shubhangi.moviesapp.Models.Review_response;
import com.example.shubhangi.moviesapp.Models.Trailer;
import com.example.shubhangi.moviesapp.Models.TrailerResponse;
import com.example.shubhangi.moviesapp.network.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetail extends AppCompatActivity {
    ImageView poster,trailer;
    TextView release,title,overview,ratings,reviews;
    Movie obj;
    TrailerResponse response1;
    ArrayList<Trailer> array;
    Review_response allReviews;

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

        response1 = new TrailerResponse();
        array = new ArrayList<>();

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("movie_obj");
        array = new ArrayList<>();
        obj = (Movie) b.getSerializable("object");

        if(obj.getMurl()!="") Picasso.with(this).load("https://image.tmdb.org/t/p/w185"+obj.getMpic()).fit().into(poster);
//        Picasso.with(this).load("https://image.tmdb.org/t/p/w185"+obj.getMurl()).into(trailer);

        title.setText(obj.getMtitle());
        release.setText(obj.getMrelease_date());
        ratings.setText(obj.getMvote());
        overview.setText(obj.getMoverview());

        Picasso.with(this).load("https://image.tmdb.org/t/p/w185"+obj.getMurl()).into(trailer);

        Call<Review_response> rev = ApiClient.getInterface().getReviews(obj.getMid(),MainActivity.api_key);
        rev.enqueue(new Callback<Review_response>() {
            @Override
            public void onResponse(Call<Review_response> call, Response<Review_response> response) {
                if (response.isSuccessful()) {
                    allReviews = response.body();
                    String appendingReviews="";
                    int size = allReviews.getReview_response().size();
                    for(int i=0;i<size;i++){
                        appendingReviews+= "<b>"+"Review" + (i+1) +"</b>" +":- \n";
                        appendingReviews+=allReviews.getReview_response().get(i).getReview() + "\n\n";
                    }
                    reviews.setText(appendingReviews);
                } else {
                    Toast.makeText(MovieDetail.this,
                            "Trailer not available...!!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Review_response> call, Throwable t) {

            }
        });


        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<TrailerResponse> trail = ApiClient.getInterface().getTrailer(obj.getMid(),MainActivity.api_key);

                trail.enqueue(new Callback<TrailerResponse>() {
                    @Override
                    public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                        if (response.isSuccessful()) {
                            response1 = response.body();
                            int size = response1.getTresponse().size();
                            for(int i=0;i<size;i++)  array.add(response1.getTresponse().get(i));
                            if(size!=0) {
                                Intent i = new Intent();
                                i.setAction(Intent.ACTION_VIEW);
                                i.setData(Uri.parse("https://www.youtube.com/watch?v=" + array.get(0).getMkey()));
                                startActivity(i);
                            }else{
                                Toast.makeText(MovieDetail.this,
                                        "Trailer does not exist...!!!", Toast.LENGTH_LONG).show();
                            }

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

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.favourit){
            moviessqlhelper sqlHelper = new moviessqlhelper(MovieDetail.this, 1);
            SQLiteDatabase db = sqlHelper.getWritableDatabase();
//                // File fileClicked = files[position];
            ContentValues cv = new ContentValues();
            cv.put(moviessqlhelper._ID, obj.getMid());
            Date date = new Date();
            cv.put(moviessqlhelper.FAV_TABLE_DATE_ADDED, date.toString());

            db.insert(moviessqlhelper.FAV_TABLE_NAME, null, cv);
        }
        return true;
    }
}
