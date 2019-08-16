package com.android.example.moviewcatalogue.adapter.recycler_view;

import android.content.Context;
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

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private Context mContext;
    private ArrayList<TvShow> mData = new ArrayList<>();

    public TvShowAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmData(ArrayList<TvShow> mData) {
        this.mData.clear();
        this.mData.addAll(mData);
        notifyDataSetChanged();
    }

    public ArrayList<TvShow> getmData() {
        return mData;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_list, viewGroup,
                false);

        return new TvShowViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder tvShowViewHolder, final int position) {
        TvShow tvShow = mData.get(position);

        tvShowViewHolder.tvTitle.setText(tvShow.getTitle());
        tvShowViewHolder.tvDescription.setText(tvShow.getDescription());
        tvShowViewHolder.tvUserScore.setText(
                String.format("%s" + mContext.getString(R.string.user_score),
                        tvShow.getUserScore()));

        Glide.with(mContext)
                .load(tvShow.getImgPhoto())
                .apply(new RequestOptions().override(100, 150))
                .into(tvShowViewHolder.ivPoster);

        tvShowViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemDetailActivity(mData.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void openItemDetailActivity(TvShow tvShow, int position) {
        Intent startMoveDetailActivityyIntent = new Intent(mContext, ItemDetailActivity.class);
        Uri uri = Uri.parse(CONTENT_URI + "/" + getmData().get(position).getId());
        startMoveDetailActivityyIntent.setData(uri);
        startMoveDetailActivityyIntent.putExtra(ItemDetailActivity.EXTRA_TV_SHOW, tvShow);
        startMoveDetailActivityyIntent.putExtra(ItemDetailActivity.EXTRA_CATEGORY, "Tv Show");
        mContext.startActivity(startMoveDetailActivityyIntent);
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDescription, tvUserScore;
        ImageView ivPoster;
        CardView cardView;

        public TvShowViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvUserScore = itemView.findViewById(R.id.tv_user_score);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }
}
