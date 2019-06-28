package com.android.example.moviewcatalogue;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.example.moviewcatalogue.Adapter.MovieAdapter;
import com.android.example.moviewcatalogue.Model.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] movieTitles, movieDescriptions, movieUserScores, movieDateOfReleases;
    private TypedArray movieImg;
    private MovieAdapter mMovieAdapter;
    private ListView mListView;
    private ArrayList<Movie> movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieAdapter = new MovieAdapter(this);
        mListView = findViewById(R.id.lv_list);
        mListView.setAdapter(mMovieAdapter);

        prepare();
        addItem();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent startMoveDetailActivityyIntent = new Intent(MainActivity.this,
                        MovieDetailActivity.class);
                startMoveDetailActivityyIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movies.get(position));
                startActivity(startMoveDetailActivityyIntent);
            }
        });
    }

    private void addItem() {
        movies = new ArrayList<>();
        for (int i = 0; i < movieTitles.length; i++) {
            Movie movie= new Movie();
            movie.setImgPhoto(movieImg.getResourceId(i, -1));
            movie.setTitle(movieTitles[i]);
            movie.setDescription(movieDescriptions[i]);
            movie.setDateOfRelease(movieDateOfReleases[i]);
            movie.setUserScore(movieUserScores[i]);
            movies.add(movie);
        }
        mMovieAdapter.setMovies(movies);
    }

    private void prepare() {
        movieTitles = getResources().getStringArray(R.array.movie_title);
        movieDescriptions = getResources().getStringArray(R.array.movie_description);
        movieDateOfReleases = getResources().getStringArray(R.array.movie_date_of_release);
        movieUserScores = getResources().getStringArray(R.array.movie_user_score);
        movieImg = getResources().obtainTypedArray(R.array.movie_img);
    }
}
