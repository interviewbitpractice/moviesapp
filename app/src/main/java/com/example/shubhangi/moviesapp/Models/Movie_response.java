package com.example.shubhangi.moviesapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shubhangi on 3/27/2016.
 */
public class Movie_response {
    String page;
    @SerializedName("results")
    ArrayList<Movie> movieResult;

    public ArrayList<Movie> getMovieResult() {
        return movieResult;
    }

    public void setMovieResult(ArrayList<Movie> movieResult) {
        this.movieResult = movieResult;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
