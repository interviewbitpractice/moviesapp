package com.example.shubhangi.moviesapp.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubhangi on 3/27/2016.
 */
public class RequestToken {

    @SerializedName("success")
    String mStatus;

    @SerializedName("request_token")
    String mToken;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }
}
