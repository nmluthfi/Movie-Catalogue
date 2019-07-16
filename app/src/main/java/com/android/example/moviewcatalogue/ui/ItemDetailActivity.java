package com.android.example.moviewcatalogue.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.model.Movie;
import com.android.example.moviewcatalogue.model.TvShow;
import com.android.example.moviewcatalogue.utils.GenreChecks;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ItemDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TV_SHOW = "extra_tv_show";

    private TextView tvTitle, tvDescription, tvUserScore, tvDateOfRelease, tvFailedLoadData, tvGenre;
    private ImageView imgPoster;
    private ProgressBar pbLoadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initComponent();

        Intent intentThatStartThisActivity = getIntent();
        if (intentThatStartThisActivity != null) {
            Movie movie = intentThatStartThisActivity.getParcelableExtra(EXTRA_MOVIE);
            TvShow tvShow = intentThatStartThisActivity.getParcelableExtra(EXTRA_TV_SHOW);
            pbLoadData.setVisibility(View.VISIBLE);

            if (movie != null) {
                tvTitle.setText(movie.getTitle());
                tvDescription.setText(movie.getDescription());
                tvUserScore.setText(String.format("%s" + getString(R.string.user_score), movie.getUserScore()));
                tvDateOfRelease.setText(movie.getDateOfRelease());

                ArrayList<String> genreInString = GenreChecks.CheckGenre(movie.getGenreId());
                for (int i = 0; i < genreInString.size(); i++) {
                    tvGenre.setText(genreInString.get(i));
                }

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
                tvDescription.setText(tvShow.getDescription());
                tvUserScore.setText(String.format("%s" + getString(R.string.user_score), tvShow.getUserScore()));
                tvDateOfRelease.setText(tvShow.getDateOfFirstAir());

                Glide.with(this)
                        .load(tvShow.getImgPhoto())
                        .apply(new RequestOptions().override(150, 250))
                        .into(imgPoster);

                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setTitle(tvShow.getTitle());
                }
            } else {
                tvFailedLoadData.setVisibility(View.VISIBLE);
            }
        }

        pbLoadData.setVisibility(View.GONE);
    }

    private void initComponent() {
        tvTitle = findViewById(R.id.tv_title);
        tvDateOfRelease = findViewById(R.id.tv_date_of_release);
        tvDescription = findViewById(R.id.tv_description);
        tvUserScore = findViewById(R.id.tv_user_score);
        tvGenre = findViewById(R.id.tv_genre);

        tvFailedLoadData = findViewById(R.id.tv_failed_load_data);

        imgPoster = findViewById(R.id.img_photo);

        pbLoadData = findViewById(R.id.pb_loading_detail_data);

    }
}
