package com.android.example.moviewcatalogue.adapter.recycler_view.favorite_item;

import android.app.Activity;
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
import com.android.example.moviewcatalogue.ui.main_menu.detail_item.ItemDetailActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder> {

    private ArrayList<Movie> listMovies = new ArrayList<>();
    private Activity activity;

    public FavoriteMovieAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<Movie> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<Movie> listMovies) {
        if (listMovies.size() > 0) {
            this.listMovies.clear();
        }
        this.listMovies.addAll(listMovies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Movie movie = listMovies.get(position);
        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvDescription.setText(movie.getDescription());
        viewHolder.tvUserScore.setText(String.format("%s" + activity.getString(R.string.user_score),
                movie.getUserScore()));

        Glide.with(activity)
                .load(movie.getImgPhoto())
                .apply(new RequestOptions().override(100, 150))
                .into(viewHolder.ivPoster);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemDetailActivity(listMovies.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    private void openItemDetailActivity(Movie movie, int position) {
        Intent startMoveDetailActivityyIntent = new Intent(activity, ItemDetailActivity.class);
        startMoveDetailActivityyIntent.putExtra(ItemDetailActivity.EXTRA_MOVIE, movie);
        startMoveDetailActivityyIntent.putExtra(ItemDetailActivity.EXTRA_CATEGORY, "Movie");
        startMoveDetailActivityyIntent.putExtra(ItemDetailActivity.EXTRA_POSITION, position);
        activity.startActivity(startMoveDetailActivityyIntent);
    }

    public void addItem(Movie movie) {
        this.listMovies.add(movie);
        notifyItemInserted(listMovies.size() - 1);
    }

    public void removeItem(int position) {
        this.listMovies.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, listMovies.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDescription, tvUserScore;
        ImageView ivPoster;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvUserScore = itemView.findViewById(R.id.tv_user_score);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }
}
