package com.android.example.moviewcatalogue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.example.moviewcatalogue.Model.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    private String title;
    private TextView tvTitle, tvDescription, tvUserScore, tvDateOfRelease;
    private ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initComponent();

        Intent intentThatStartThisActivity = getIntent();
        Movie movie = intentThatStartThisActivity.getParcelableExtra(EXTRA_MOVIE);
        if (intentThatStartThisActivity != null) {
//            title = intentThatStartThisActivity.getStringExtra(EXTRA_MOVIE_TITLE);
//            String description  = intentThatStartThisActivity.getStringExtra(EXTRA_MOVIE_DESCRIPTION);
//            int dateOfRelease = intentThatStartThisActivity.getIntExtra(EXTRA_MOVIE_DATE_OF_RELEASE, 0);
//            int userScore = intentThatStartThisActivity.getIntExtra(EXTRA_MOVIE_USER_SCORE,0);
//            String photo = intentThatStartThisActivity.getStringExtra(EXTRA_MOVIE_POSTER);

            tvTitle.setText(movie.getTitle());
            tvDescription.setText(movie.getDescription());
            tvUserScore.setText(movie.getUserScore() + " of 100");
            tvDateOfRelease.setText(movie.getDateOfRelease() );

            Glide.with(this)
                    .load(movie.getImgPhoto())
                    .apply(new RequestOptions().override(150, 250))
                    .into(imgPoster);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(movie.getTitle());
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
