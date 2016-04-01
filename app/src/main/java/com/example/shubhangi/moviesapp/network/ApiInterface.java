package com.example.shubhangi.moviesapp.network;

import com.example.shubhangi.moviesapp.Models.Movie_response;
import com.example.shubhangi.moviesapp.Models.RequestToken;
import com.example.shubhangi.moviesapp.Models.Session;
import com.example.shubhangi.moviesapp.Models.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rishabh goel on 26-03-2016.
 */
public interface ApiInterface {

    @GET("movie/popular")
    Call<Movie_response> getMovie(@Query("api_key") String url);

    @GET("movie/upcoming")
    Call<Movie_response> getupMovie(@Query("api_key") String url);

    @GET("movie/top_rated")
    Call<Movie_response> gettopMovie(@Query("api_key") String url);

    @GET("authentication/token/new")
    Call<RequestToken> getToken(@Query("api_key") String url);

    @GET("authentication/session/new")
    Call<Session> getSession(@Query("api_key") String url,@Query("request_token") String req_token);

    @GET("movie/{id}/videos")
    Call<TrailerResponse> getTrailer(@Path("id") int movieId,@Query("api_key") String url);

//    @GET("users")
//    Call<ArrayList<User>> getUsers();
//
//    @POST("posts")
//    Call<User> createUser(@Body User user);
}
