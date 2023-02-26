package com.example.movies;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {
    @SerializedName("docs")
    private final List<Review> reviewList;

    public ReviewResponse(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<Review> getReviews() {
        return reviewList;
    }

    @NonNull
    @Override
    public String toString() {
        return "ReviewResponse{" +
                "reviewList=" + reviewList +
                '}';
    }
}
