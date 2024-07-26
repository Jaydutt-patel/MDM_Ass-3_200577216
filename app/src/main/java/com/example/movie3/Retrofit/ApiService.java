package com.example.movie3.Retrofit;

import com.example.movie3.Model.MovieDetails;
import com.example.movie3.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
//    @GET("?apikey=63e6165")
//    Call<MovieResponse> getMovies(@Query("s") String searchQuery);

    @GET("/")
    Call<MovieResponse> getMovies(@Query("s") String query, @Query("apikey") String apiKey);

    @GET("/")
    Call<MovieDetails> getMovieDetails(@Query("i") String imdbID, @Query("apikey") String apiKey);
}
