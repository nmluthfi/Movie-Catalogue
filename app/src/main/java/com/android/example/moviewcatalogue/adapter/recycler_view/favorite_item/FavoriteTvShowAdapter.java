package com.android.example.moviewcatalogue.adapter.recycler_view.favorite_item;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.model.TvShow;
import com.android.example.moviewcatalogue.ui.main_menu.detail_item.ItemDetailActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import static com.android.example.moviewcatalogue.database.tv_show.TvShowContract.TvColumns.CONTENT_URI;

public class FavoriteTvShowAdapter extends RecyclerView.Adapter<FavoriteTvShowAdapter.ViewHolder> {

    private ArrayList<TvShow> listTvShows = new ArrayList<>();
    private Activity activity;

    public FavoriteTvShowAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<TvShow> getListTvShows() {
        return listTvShows;
    }

    public void setListTvShows(ArrayList<TvShow> listTvShows) {
        if (listTvShows.size() > 0) {
            this.listTvShows.clear();
        }
        this.listTvShows.addAll(listTvShows);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteTvShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteTvShowAdapter.ViewHolder viewHolder, final int position) {
        final TvShow tvShow = listTvShows.get(position);
        viewHolder.tvTitle.setText(tvShow.getTitle());
        viewHolder.tvDescription.setText(tvShow.getDescription());
        viewHolder.tvUserScore.setText(String.format("%s" + activity.getString(R.string.user_score),
                tvShow.getUserScore()));

        Glide.with(activity)
                .load(tvShow.getImgPhoto())
                .apply(new RequestOptions().override(100, 150))
                .into(viewHolder.ivPoster);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemDetailActivity(listTvShows.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTvShows.size();
    }

    private void openItemDetailActivity(TvShow tvShow, int position) {
        Intent startMoveDetailActivityyIntent = new Intent(activity, ItemDetailActivity.class);
        Uri uri = Uri.parse(CONTENT_URI + "/" + getListTvShows().get(position).getId());
        startMoveDetailActivityyIntent.setData(uri);
        startMoveDetailActivityyIntent.putExtra(ItemDetailActivity.EXTRA_TV_SHOW, tvShow);
        startMoveDetailActivityyIntent.putExtra(ItemDetailActivity.EXTRA_CATEGORY, "Tv Show");
        activity.startActivity(startMoveDetailActivityyIntent);
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
