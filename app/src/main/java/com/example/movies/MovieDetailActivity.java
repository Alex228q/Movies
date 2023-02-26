package com.example.movies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

    private TrailersAdapter trailersAdapter;
    private ReviewsAdapter reviewsAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewReviews;
    private static final String EXTRA_MOVIE = "movie";
    private TextView textViewDesc;
    private TextView textViewYear;
    private TextView textViewTitle;
    private ImageView imageViewPosterDetail;
    MovieDetailViewModel movieDetailViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initViews();
        trailersAdapter = new TrailersAdapter();
        recyclerView.setAdapter(trailersAdapter);


        reviewsAdapter = new ReviewsAdapter();
        recyclerViewReviews.setAdapter(reviewsAdapter);

        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewReviews.setLayoutManager(new LinearLayoutManager(this));


        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        movieDetailViewModel.loadTrailers(movie.getId());
        movieDetailViewModel.getTrailers().observe(this, trailers -> trailersAdapter.setTrailers(trailers));

        movieDetailViewModel.loadReviews(movie.getId());
        movieDetailViewModel.getReviews().observe(this, reviews -> reviewsAdapter.setReviews(reviews));


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


        trailersAdapter.setOnTrailerClickListener(trailer -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(trailer.getUrl()));
            startActivity(intent);
        });

    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        textViewDesc = findViewById(R.id.textViewDesc);
        textViewYear = findViewById(R.id.textViewYear);
        textViewTitle = findViewById(R.id.textViewTitle);
        imageViewPosterDetail = findViewById(R.id.imageViewPosterDetail);
    }
}