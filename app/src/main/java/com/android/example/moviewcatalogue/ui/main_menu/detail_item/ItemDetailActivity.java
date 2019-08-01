package com.android.example.moviewcatalogue.ui.main_menu.detail_item;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.model.Movie;
import com.android.example.moviewcatalogue.model.TvShow;
import com.android.example.moviewcatalogue.utils.GenreChecks;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ItemDetailActivity extends AppCompatActivity {

    // This is submission 4
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TV_SHOW = "extra_tv_show";

    private TextView tvTitle, tvDescription, tvUserScore, tvDateOfRelease, tvFailedLoadData, tvGenre;
    private ImageView ivBackdrop;
    private ProgressBar pbLoadData;
    private Menu menu;
    private Dialog dialogShowPhotoFullscreen;

    private boolean isFavorite = false;

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
               showMovieData(movie);
            } else if (tvShow != null) {
                showTvShowData(tvShow);
            } else {
                tvFailedLoadData.setVisibility(View.VISIBLE);
            }
        }

        pbLoadData.setVisibility(View.GONE);
    }


    private void showTvShowData(final TvShow tvShow) {
        tvTitle.setText(tvShow.getTitle());
        tvDescription.setText(tvShow.getDescription());
        tvUserScore.setText(String.format("%s" + getString(R.string.user_score), tvShow.getUserScore()));
        tvDateOfRelease.setText(tvShow.getDateOfFirstAir());

        ArrayList<String> genreInString = GenreChecks.TvShowGenre(tvShow.getGenreId());
        for (int i = 0; i < genreInString.size(); i++) {
            tvGenre.setText(genreInString.get(i));
        }

        Glide.with(this)
                .load(tvShow.getBackdropPhoto())
                .into(ivBackdrop);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(tvShow.getTitle());
        }

        ivBackdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFullscreenPhoto(tvShow.getBackdropPhoto());
            }
        });
    }

    private void showMovieData(final Movie movie) {
        tvTitle.setText(movie.getTitle());
        tvDescription.setText(movie.getDescription());
        tvUserScore.setText(String.format("%s" + getString(R.string.user_score), movie.getUserScore()));
        tvDateOfRelease.setText(movie.getDateOfRelease());

        ArrayList<String> genreInString = GenreChecks.MovieGenre(movie.getGenreId());
        for (int i = 0; i < genreInString.size(); i++) {
            tvGenre.setText(genreInString.get(i));
        }

        Glide.with(this)
                .load(movie.getBackdropPhoto())
                .into(ivBackdrop);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(movie.getTitle());
        }

        ivBackdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFullscreenPhoto(movie.getBackdropPhoto());
            }
        });
    }

    /*
     * Method for launch dialogShowPhotoFullscreen contains full screen image
     * */
    public void showFullscreenPhoto(String backdropPhoto) {
        dialogShowPhotoFullscreen.setContentView(R.layout.dialog_poster_fullscreen);

        ImageView ivPhotoFullScreen = dialogShowPhotoFullscreen.findViewById(R.id.iv_photo_fullscreen);

        Glide.with(getBaseContext())
                .load(backdropPhoto)
                .override(600, 250)
                .into(ivPhotoFullScreen);

        dialogShowPhotoFullscreen.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogShowPhotoFullscreen.show();

        dialogShowPhotoFullscreen.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_detail, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {
            MenuItem favorite = menu.findItem(R.id.action_favorite);
            if (!isFavorite) {
                favorite.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white));
                isFavorite = true;
            } else if (isFavorite) {
                favorite.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white));
                isFavorite = false;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initComponent() {
        tvTitle = findViewById(R.id.tv_title);
        tvDateOfRelease = findViewById(R.id.tv_date_of_release);
        tvDescription = findViewById(R.id.tv_description);
        tvUserScore = findViewById(R.id.tv_user_score);
        tvGenre = findViewById(R.id.tv_genre);

        tvFailedLoadData = findViewById(R.id.tv_failed_load_data);

        ivBackdrop = findViewById(R.id.iv_poster_backdrop);

        pbLoadData = findViewById(R.id.pb_loading_detail_data);

        dialogShowPhotoFullscreen = new Dialog(this, android.R.style.Theme_Light);

    }

}
