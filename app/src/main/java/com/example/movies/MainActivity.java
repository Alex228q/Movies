package com.example.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        moviesAdapter = new MoviesAdapter();
        recyclerView.setAdapter(moviesAdapter);

        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getMovies().observe(this, movies -> moviesAdapter.setMovies(movies));
        mainViewModel.getIsLoading().observe(this, (isLoading) -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });
        moviesAdapter.setOnReachEndListener(() -> mainViewModel.loadMovies());
        moviesAdapter.setOnMovieClickListener(movie -> {
            Intent intent = MovieDetailActivity.newIntent(MainActivity.this, movie);
            startActivity(intent);
        });
    }

    void initView() {
        recyclerView = findViewById(R.id.recyclerViewMovies);
        progressBar = findViewById(R.id.progressBar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemFavorite) {
            Intent intent = FavouriteMoviesActivity.newIntent(this);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}