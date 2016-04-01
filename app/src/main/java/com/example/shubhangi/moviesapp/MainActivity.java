package com.example.shubhangi.moviesapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
    Movie res;
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
        this.setTitle("Most Popular Movies");

        song = MediaPlayer.create(this,R.raw.sound);
        song.setLooping(true);
        res = new Movie();
        //song.start();

        grid=(GridView)findViewById(R.id.gridView);

        Call<RequestToken> tok = ApiClient.getInterface().getToken(api_key);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        Call<Movie_response> allMovieCall = null;
        String sort;

        if(i == R.id.favourites){
            setTitle("My Favourites");
            moviessqlhelper sqlHelper = new moviessqlhelper(this, 1);
            SQLiteDatabase db = sqlHelper.getReadableDatabase();
            String[] columns = {moviessqlhelper._ID,moviessqlhelper.FAV_TABLE_DATE_ADDED};
            Cursor c = db.query(true, moviessqlhelper.FAV_TABLE_NAME, columns, null, null,
                    null, null, moviessqlhelper.FAV_TABLE_DATE_ADDED + " DESC", null);

            ArrayList<Integer> movie_id = new ArrayList<>();

            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndexOrThrow
                        (moviessqlhelper._ID));
                movie_id.add(id);
            }
            movies.clear();
            final ArrayList<Movie> movies_fav=new ArrayList<>();
            for(int j=0;j<movie_id.size();j++){
                int y = movie_id.get(j);
                Toast.makeText(MainActivity.this,
                                    y+"", Toast.LENGTH_LONG).show();
                Call<Movie> MovieCall = ApiClient.getInterface().getMovieDetail(y, api_key);
                MovieCall.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful()) {
                            res = response.body();
                            movies.add(res);
                            Toast.makeText(MainActivity.this,
                                    res.getMtitle(), Toast.LENGTH_LONG).show();
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(MainActivity.this,
                                    response.message() + response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {

                    }
                });

            }
//            for(int k=0;k<movies_fav.size();k++) movies.add(movies_fav.get(k));
            adapter.notifyDataSetChanged();
            return true;
        }

        if(i==R.id.popular){
            allMovieCall = ApiClient.getInterface().getMovie(api_key);
            this.setTitle("Most Popular Movies");
        }else if(i == R.id.top){
            allMovieCall = ApiClient.getInterface().gettopMovie(api_key);
            this.setTitle("Top Rated Movies");
        }else if(i == R.id.upcoming){
            allMovieCall = ApiClient.getInterface().getupMovie(api_key);
            this.setTitle("Upcoming Movies");
        }
            allMovieCall.enqueue(new Callback<Movie_response>() {
                @Override
                public void onResponse(Call<Movie_response> call, Response<Movie_response> response) {
                    if (response.isSuccessful()) {
                        result = response.body();
                        int size=result.getMovieResult().size();
                        movies.clear();
                        for (int i=0;i<size;i++)
                            movies.add(result.getMovieResult().get(i));
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this,
                                response.message() + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Movie_response> call, Throwable t) {
                    Toast.makeText(MainActivity.this,
                            t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        return true;
    }
}
