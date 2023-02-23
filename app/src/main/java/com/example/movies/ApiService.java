package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie?token=Z78P891-JHPMQTC-J4RAMNY-XTSYXXM&field=rating.kp&search=8-10&sortField=votes.kp&sortType=-1&limit=30")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("movie?token=Z78P891-JHPMQTC-J4RAMNY-XTSYXXM&field=id")
    Single<TrailerResponse> loadTrailers(@Query("search") int id);
}
