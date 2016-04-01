package com.example.shubhangi.moviesapp.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubhangi on 4/1/2016.
 */
public class Review {
    @SerializedName("content")
    String review;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
