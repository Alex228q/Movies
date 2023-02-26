package com.example.movies;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {
    @SerializedName("kp")
    private final double kp;

    public double getKp() {
        return kp;
    }

    public Rating(double kp) {
        this.kp = kp;
    }

    @NonNull
    @Override
    public String toString() {
        return "Rating{" +
                "kp='" + kp + '\'' +
                '}';
    }
}
