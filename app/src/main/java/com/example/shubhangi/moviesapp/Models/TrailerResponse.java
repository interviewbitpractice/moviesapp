package com.example.shubhangi.moviesapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shubhangi on 3/30/2016.
 */
public class TrailerResponse {
    @SerializedName("results")
    ArrayList<Trailer> Tresponse;


    public ArrayList<Trailer> getTresponse() {
        return Tresponse;
    }

    public void setTresponse(ArrayList<Trailer> tresponse) {
        Tresponse = tresponse;
    }
}
