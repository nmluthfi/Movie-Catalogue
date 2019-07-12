package com.android.example.moviewcatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.model.Movie;
import com.android.example.moviewcatalogue.ui.ItemDetailActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context mContext;
    private ArrayList<Movie> mData;

    public MovieAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<Movie> getmData() {
        return mData;
    }

    public void setmData(ArrayList<Movie> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup,
                false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, final int position) {
        final Movie movie = mData.get(position);
        movieViewHolder.tvTitle.setText(movie.getTitle());
        movieViewHolder.tvDescription.setText(Integer.parseInt(movie.getDescription()));
        movieViewHolder.tvUserScore.setText(String.format(
                "%s" + mContext.getString(R.string.user_score),
                movie.getUserScore()));

        Glide.with(mContext)
                .load(movie.getImgPhoto())
                .apply(new RequestOptions().override(100, 150))
                .into(movieViewHolder.ivPoster);

        movieViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemDetailActivity(mData.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void openItemDetailActivity(Movie movie) {
        Intent startMoveDetailActivityyIntent = new Intent(mContext, ItemDetailActivity.class);
        startMoveDetailActivityyIntent.putExtra(ItemDetailActivity.EXTRA_MOVIE, movie);
        mContext.startActivity(startMoveDetailActivityyIntent);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDescription, tvUserScore;
        ImageView ivPoster;
        CardView cardView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ivPoster = itemView.findViewById(R.id.img_photo);
            tvUserScore = itemView.findViewById(R.id.tv_user_score);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }

}
