package com.example.movie3.Activity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.movie3.Model.MovieDetails;
import com.example.movie3.R;
import com.example.movie3.Retrofit.ApiService;
import com.example.movie3.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    public String movieId;
    ApiService apiService;
    public static String API_KEY = "c5dbc992";
    TextView movieTitle,movieStudio,genre,year,length,imdbRating,imdbVotes,plot,actors,writers,director,country,language,boxOffice,production;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);

        initView();
        apiService = RetrofitClient.getClient().create(ApiService.class);
        movieId=getIntent().getStringExtra("movieId");
        retrieveMovieDetails(movieId);
    }

    private void initView() {
        poster=findViewById(R.id.moviePoster);
        movieTitle=findViewById(R.id.mTitle);
        movieStudio=findViewById(R.id.mStudio);
        genre=findViewById(R.id.movieGenre);
        plot=findViewById(R.id.moviePlot);
        year=findViewById(R.id.mYear);
        length=findViewById(R.id.movieLength);
        imdbRating=findViewById(R.id.movieImdb_rating);
        imdbVotes=findViewById(R.id.movieImdbVotes);
        country=findViewById(R.id.movieCountry);
        director=findViewById(R.id.movieDirector);
        language=findViewById(R.id.movieLanguages);
        production=findViewById(R.id.movieProduction);
        boxOffice=findViewById(R.id.movieBoxOffice);
        actors=findViewById(R.id.movieActor);
        writers=findViewById(R.id.movieWriter);
    }

    private void retrieveMovieDetails(String movieId) {
        ApiService movieApiService = RetrofitClient.getClient().create(ApiService.class);
        Call<MovieDetails> movieDetailsCall = movieApiService.getMovieDetails(movieId, API_KEY);

        movieDetailsCall.enqueue(new Callback<MovieDetails>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                MovieDetails movieDetails = response.body();
                if (response.isSuccessful() && movieDetails != null) {
                    String posterUrl = movieDetails.getPoster();
                    movieTitle.setText(movieDetails.getTitle());

                    if (posterUrl != null && !posterUrl.isEmpty()) {
                        Glide.with(MovieDetailsActivity.this)
                                .load(Uri.parse(posterUrl))
                                .into(poster);
                    }

                    genre.setText(movieDetails.getGenre());
                    imdbRating.setText(movieDetails.getImdbRating());
                    imdbVotes.setText(movieDetails.getImdbVotes());
                    year.setText(movieDetails.getYear());
                    length.setText(movieDetails.getRuntime());
                    plot.setText(movieDetails.getPlot());
                    director.setText(movieDetails.getDirector());
                    actors.setText(movieDetails.getActors());
                    writers.setText(movieDetails.getWriter());
                    country.setText(movieDetails.getCountry());
                    language.setText(movieDetails.getLanguage());
                    production.setText(movieDetails.getProduction());
                    boxOffice.setText(movieDetails.getBoxOffice());
                } else {}
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable throwable) {
                Toast.makeText(MovieDetailsActivity.this, "Failed to retrieve movie details", Toast.LENGTH_SHORT).show();
            }
        });
    }


}