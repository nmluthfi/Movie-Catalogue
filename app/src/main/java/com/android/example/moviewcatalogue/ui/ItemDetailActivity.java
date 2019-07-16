package com.android.example.moviewcatalogue.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.model.Movie;
import com.android.example.moviewcatalogue.model.TvShow;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ItemDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TV_SHOW = "extra_tv_show";

    private TextView tvTitle, tvDescription, tvUserScore, tvDateOfRelease;
    private ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initComponent();

        Intent intentThatStartThisActivity = getIntent();
        if (intentThatStartThisActivity != null) {
            Movie movie = intentThatStartThisActivity.getParcelableExtra(EXTRA_MOVIE);
            TvShow tvShow = intentThatStartThisActivity.getParcelableExtra(EXTRA_TV_SHOW);

            if (movie != null) {
                tvTitle.setText(movie.getTitle());
                tvDescription.setText(movie.getDescription());
                tvUserScore.setText(String.format("%s" + getString(R.string.user_score), movie.getUserScore()));
                tvDateOfRelease.setText(movie.getDateOfRelease());

                Glide.with(this)
                        .load(movie.getImgPhoto())
                        .apply(new RequestOptions().override(150, 250))
                        .into(imgPoster);

                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setTitle(movie.getTitle());
                }
            } else if (tvShow != null) {
                tvTitle.setText(tvShow.getTitle());
                tvDescription.setText(Integer.parseInt(tvShow.getDescription()));
                tvUserScore.setText(String.format("%s" + getString(R.string.user_score), tvShow.getUserScore()));
                tvDateOfRelease.setText(tvShow.getDateOfRelease());

                Glide.with(this)
                        .load(tvShow.getImgPhoto())
                        .apply(new RequestOptions().override(150, 250))
                        .into(imgPoster);

                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setTitle(tvShow.getTitle());
                }
            }
        }

    }

    private void initComponent() {
        tvTitle = findViewById(R.id.tv_title);
        tvDateOfRelease = findViewById(R.id.tv_date_of_release);
        tvDescription = findViewById(R.id.tv_description);
        tvUserScore = findViewById(R.id.tv_user_score);

        imgPoster = findViewById(R.id.img_photo);
    }
}
