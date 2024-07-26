package com.example.movie3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie3.Adapter.MovieAdapter;
import com.example.movie3.Model.Movie;
import com.example.movie3.Model.MovieResponse;
import com.example.movie3.OnClicked;
import com.example.movie3.R;
import com.example.movie3.Retrofit.ApiService;
import com.example.movie3.Retrofit.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> movieList = new ArrayList<>();
    ApiService apiService;
    public static String API_KEY = "c5dbc992";
    MovieAdapter dataAdapter;
    RecyclerView showMovieList;
SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        showMovieList = findViewById(R.id.recMovieList);
        showMovieList.setLayoutManager(new LinearLayoutManager(this));

        apiService = RetrofitClient.getClient().create(ApiService.class);

        searchView = findViewById(R.id.searchMovie);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchMoviesFromApi(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }



    private void fetchMoviesFromApi(String searchQuery) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<MovieResponse> movieResponseCall = apiService.getMovies(searchQuery, API_KEY);

        movieResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    movieList.clear();
                    movieList.addAll(response.body().getSearch());
                    dataAdapter = new MovieAdapter(MainActivity.this, movieList, onClicked);
                    showMovieList.setAdapter(dataAdapter);
                } else {
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Failed to fetch movies", Toast.LENGTH_SHORT).show();
            }
        });
    }

    OnClicked onClicked = new OnClicked() {
        @Override
        public void on_clicked(String id) {
            Intent i = new Intent(MainActivity.this, MovieDetailsActivity.class);
            i.putExtra("movieId", id);
            startActivity(i);
        }
    };

}