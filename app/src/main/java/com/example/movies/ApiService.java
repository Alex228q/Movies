package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {
    @GET("movie?token=Z78P891-JHPMQTC-J4RAMNY-XTSYXXM&field=rating.kp&search=8-10&sortField=votes.kp&sortType=-1&page=1&limit=5")
    Single<MovieResponse> loadMovies();
}
