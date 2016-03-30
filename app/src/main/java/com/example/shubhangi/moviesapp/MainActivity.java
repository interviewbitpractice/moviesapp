package com.example.shubhangi.moviesapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubhangi.moviesapp.Adapter.moviearrayadapter;
import com.example.shubhangi.moviesapp.Models.Movie;
import com.example.shubhangi.moviesapp.Models.Movie_response;
import com.example.shubhangi.moviesapp.Models.RequestToken;
import com.example.shubhangi.moviesapp.Models.Session;
import com.example.shubhangi.moviesapp.network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static String api_key="334be3f2e1b5974763a72f52eab0de93";
    public static String validate_token="https://www.themoviedb.org/authenticate/";
    public static String sessionurl="http://api.themoviedb.org/3/authentication/session/new?"+api_key+"&request_token=";
    int jugaad=-1;
    MediaPlayer song;
    int check=-1;
    TextView tv;
    ArrayList<Movie> movies;
    moviearrayadapter adapter;
    GridView grid;
    //public static String tokenurl="http://api.themoviedb.org/3/authentication/token/new?api_key="+ api_key;
    RequestToken token;
    Session sessionob;
    Movie_response result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tv=(TextView)findViewById(R.id.textView);

        song = MediaPlayer.create(this,R.raw.sound);
        song.setLooping(true);
        //song.start();

        grid=(GridView)findViewById(R.id.gridView);

        Call<RequestToken> tok = ApiClient.getInterface().getToken(api_key);

//        tok.enqueue(new Callback<RequestToken>() {
//            @Override
//            public void onResponse(Call<RequestToken> call, Response<RequestToken> response) {
//                if (response.isSuccessful()) {
//                    token = response.body();
//                    Toast.makeText(MainActivity.this,
//                            token.getmToken(), Toast.LENGTH_LONG).show();
//
//                    validate_token += token.getmToken();
//
//                    Intent i = new Intent();
//                    i.setAction(Intent.ACTION_VIEW);
//                    i.setData(Uri.parse(validate_token));
//                    startActivity(i);
//                    jugaad=1;
//
//                } else {
//                    Toast.makeText(MainActivity.this,
//                            "Session not generated!!", Toast.LENGTH_LONG).show();
//                    jugaad=1;
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<RequestToken> call, Throwable t) {
//                Toast.makeText(MainActivity.this,
//                        t.getMessage(), Toast.LENGTH_LONG).show();
//                jugaad=1;
//            }
//        });
        //while(jugaad==-1);

//        sessionurl += token.getmToken();
//
//        Call<Session> sess = ApiClient.getInterface().getSession(api_key, token.getmToken());
//        sess.enqueue(new Callback<Session>() {
//            @Override
//            public void onResponse(Call<Session> call, Response<Session> response) {
//                if (response.isSuccessful()) {
//                    sessionob = response.body();
//                    Toast.makeText(MainActivity.this,
//                            token.getmToken(), Toast.LENGTH_LONG).show();
//
//                } else {
//                    Toast.makeText(MainActivity.this,
//                            "Token not generated!!", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Session> call, Throwable t) {
//                Toast.makeText(MainActivity.this,
//                        t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });




        result = new Movie_response();
        movies=new ArrayList<>();
        adapter=new moviearrayadapter(this,movies);
        grid.setAdapter(adapter);



        Call<Movie_response> allMovieCall = ApiClient.getInterface().getMovie(api_key);
        allMovieCall.enqueue(new Callback<Movie_response>() {
            @Override
            public void onResponse(Call<Movie_response> call, Response<Movie_response> response) {

                if (response.isSuccessful()) {
                    result = response.body();
                    int size=result.getMovieResult().size();
                    for (int i=0;i<size;i++)
                        movies.add(result.getMovieResult().get(i));
                    check = 1;
                    Toast.makeText(MainActivity.this,
                            movies.size() + "   " + movies.get(0).getMtitle(), Toast.LENGTH_LONG).show();
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this,
                            response.message() + response.code(), Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<Movie_response> call, Throwable t) {

            }
       });
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.setClass(MainActivity.this, MovieDetail.class);
                Toast.makeText(MainActivity.this,
                        movies.get(position).getMtitle() + " Clicked!!", Toast.LENGTH_LONG).show();
                //i.putExtra("moviekey", movies.get(position));
                Bundle b = new Bundle();
                b.putSerializable("object", movies.get(position));
                i.putExtra("movie_obj", b);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
       // song.start();
    }

}
