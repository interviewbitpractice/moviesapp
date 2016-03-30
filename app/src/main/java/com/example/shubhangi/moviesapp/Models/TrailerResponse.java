package com.example.shubhangi.moviesapp.Models;

/**
 * Created by shubhangi on 3/30/2016.
 */
public class TrailerResponse {
    Trailer[] Tresponse = new Trailer[2];

    public Trailer[] getTresponse() {
        return Tresponse;
    }

    public void setTresponse(Trailer[] tresponse) {
        Tresponse = tresponse;
    }
}
