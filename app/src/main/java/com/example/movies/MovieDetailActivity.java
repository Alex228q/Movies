package com.example.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String EXTRA_MOVIE = "movie";
    private TextView textViewDesc;
    private TextView textViewYear;
    private TextView textViewTitle;
    private ImageView imageViewPosterDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initViews();

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        if (movie.getPoster() != null) {
            Glide.with(this)
                    .load(movie.getPoster().getUrl())
                    .into(imageViewPosterDetail);
        } else {
            Glide.with(this)
                    .load(R.drawable.image_not_found)
                    .into(imageViewPosterDetail);
        }
        textViewTitle.setText(movie.getName());
        textViewDesc.setText(movie.getDescription());
        textViewYear.setText(String.valueOf(movie.getYear()));
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    private void initViews() {
        textViewDesc = findViewById(R.id.textViewDesc);
        textViewYear = findViewById(R.id.textViewYear);
        textViewTitle = findViewById(R.id.textViewTitle);
        imageViewPosterDetail = findViewById(R.id.imageViewPosterDetail);
    }
}