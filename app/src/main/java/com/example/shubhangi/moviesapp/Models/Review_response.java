package com.example.shubhangi.moviesapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shubhangi on 4/1/2016.
 */
public class Review_response {
    @SerializedName("id")
    int id;

    @SerializedName("results")
    ArrayList<Review> review_response;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Review> getReview_response() {
        return review_response;
    }

    public void setReview_response(ArrayList<Review> review_response) {
        this.review_response = review_response;
    }
}
