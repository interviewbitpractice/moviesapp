package com.example.shubhangi.moviesapp.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubhangi on 3/30/2016.
 */
public class Trailer {
    @SerializedName("id")
    String mid;

    @SerializedName("key")
    String mkey;

    @SerializedName("name")
    String mname;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }
}
