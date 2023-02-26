package com.example.movies;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("review")
    private final String review;
    @SerializedName("type")
    private final String type;
    @SerializedName("author")
    private final String author;

    public Review(String review, String type, String author) {
        this.review = review;
        this.type = type;
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public String getReview() {
        return review;
    }

    public String getAuthor() {
        return author;
    }

    @NonNull
    @Override
    public String toString() {
        return "Review{" +
                "review='" + review + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
