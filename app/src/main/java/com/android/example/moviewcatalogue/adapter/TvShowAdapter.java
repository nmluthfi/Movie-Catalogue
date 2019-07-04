package com.android.example.moviewcatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.model.TvShow;
import com.android.example.moviewcatalogue.ui.ItemDetailActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private Context mContext;
    private ArrayList<TvShow> mData;

    public TvShowAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<TvShow> getmData() {
        return mData;
    }

    public void setmData(ArrayList<TvShow> mData) {
        this.mData = mData;
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
        tvShowViewHolder.tvDescription.setText(Integer.parseInt(tvShow.getDescription()));

        Glide.with(mContext)
                .load(tvShow.getImgPhoto())
                .apply(new RequestOptions().override(100, 150))
                .into(tvShowViewHolder.ivPoster);

        tvShowViewHolder.btnView.setOnClickListener(new View.OnClickListener() {
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

    private void openItemDetailActivity(TvShow tvShow) {
        Intent startMoveDetailActivityyIntent = new Intent(mContext, ItemDetailActivity.class);
        startMoveDetailActivityyIntent.putExtra(ItemDetailActivity.EXTRA_TV_SHOW, tvShow);
        mContext.startActivity(startMoveDetailActivityyIntent);
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDescription;
        ImageView ivPoster;
        Button btnView;

        public TvShowViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ivPoster = itemView.findViewById(R.id.img_photo);
            btnView = itemView.findViewById(R.id.btn_see_moview);
        }
    }
}