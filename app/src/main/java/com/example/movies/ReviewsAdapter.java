package com.example.movies;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {
    private static final String TAG = "ReviewsAdapter";
    private static final String TYPE_NEGATIVE = "Негативный";
    private static final String TYPE_NEUTRAL = "Нейтральный";

    private List<Review> reviewList = new ArrayList<>();

    public void setReviews(List<Review> reviewList) {
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.review_item,
                        parent,
                        false
                );
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, int position) {
        Review review = reviewList.get(position);
        String typeReview = review.getType();
        holder.textViewReview.setText(review.getReview());
        holder.textViewAuthor.setText(review.getAuthor());
        int colorResId = android.R.color.holo_green_dark;
        try {
            switch (typeReview) {
                case TYPE_NEGATIVE:
                    colorResId = android.R.color.holo_red_dark;
                    break;
                case TYPE_NEUTRAL:
                    colorResId = android.R.color.holo_orange_dark;
                    break;
            }
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        int color = ContextCompat.getColor(holder.itemView.getContext(), colorResId);
        holder.linearLayoutReviews.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    static class ReviewsViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout linearLayoutReviews;
        private final TextView textViewReview;
        private final TextView textViewAuthor;


        public ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewReview = itemView.findViewById(R.id.textViewReview);
            linearLayoutReviews = itemView.findViewById(R.id.linearLayoutReviews);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
        }
    }
}
