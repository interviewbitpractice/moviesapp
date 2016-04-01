package com.example.shubhangi.moviesapp.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rishabh goel on 27-03-2016.
 */
public class Movie implements Serializable {

    @SerializedName("id")
    int mid;

    @SerializedName("overview")
    String moverview;

    @SerializedName("poster_path")
    String murl;

    @SerializedName("release_date")
    String mrelease_date;

    @SerializedName("title")
    String mtitle;

    @SerializedName("popularity")
    String mpopularity;

    @SerializedName("vote_average")
    String maverage;

    @SerializedName("adult")
    String madult;

    @SerializedName("backdrop_path")
    String mpic;

    String key;
    String youtubeurl;


    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMoverview() {
        return moverview;
    }

    public void setMoverview(String moverview) {
        this.moverview = moverview;
    }

    public String getMurl() {
        return murl;
    }

    public void setMurl(String murl) {
        this.murl = murl;
    }

    public String getMrelease_date() {
        return mrelease_date;
    }

    public void setMrelease_date(String mrelease_date) {
        this.mrelease_date = mrelease_date;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMpopularity() {
        return mpopularity;
    }

    public void setMpopularity(String mpopularity) {
        this.mpopularity = mpopularity;
    }

    public String getMvote() {
        return maverage;
    }

    public void setMvote(String mvote) {
        this.maverage = mvote;
    }

    public String getMadult() {
        return madult;
    }

    public void setMadult(String madult) {
        this.madult = madult;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getYoutubeurl() {
        return youtubeurl;
    }

    public void setYoutubeurl(String youtubeurl) {
        this.youtubeurl = youtubeurl;
    }

    public String getMpic() {
        return mpic;
    }

    public void setMpic(int mid) {
        this.mpic = mpic;
    }
}
