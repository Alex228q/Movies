package com.example.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavouriteMoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movies);

        RecyclerView recyclerViewFavouriteMovies = findViewById(R.id.recyclerViewFavouriteMovies);
        MoviesAdapter moviesAdapter = new MoviesAdapter();
        recyclerViewFavouriteMovies.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewFavouriteMovies.setAdapter(moviesAdapter);
        FavouriteMoviesViewModel favouriteMoviesViewModel = new ViewModelProvider(
                this).get(FavouriteMoviesViewModel.class);


        favouriteMoviesViewModel.getFavouriteMovies().observe(
                this, movies -> moviesAdapter.setMovies(movies));

        moviesAdapter.setOnMovieClickListener(movie -> {
            Intent intent = MovieDetailActivity.newIntent(FavouriteMoviesActivity.this, movie);
            startActivity(intent);
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FavouriteMoviesActivity.class);
    }
}