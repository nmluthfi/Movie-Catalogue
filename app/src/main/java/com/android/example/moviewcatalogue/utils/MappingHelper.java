package com.android.example.moviewcatalogue.utils;

import android.database.Cursor;

import com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns;
import com.android.example.moviewcatalogue.database.tv_show.TvShowContract.TvColumns;
import com.android.example.moviewcatalogue.model.Movie;
import com.android.example.moviewcatalogue.model.TvShow;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.backdropPhoto;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.dateOfRelease;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.genreId;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.imgPhoto;


public class MappingHelper {

    public static ArrayList<Movie> mapCursorMovieToArrayList(Cursor cursor) {
        ArrayList<Movie> movies = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            double userScore = cursor.getDouble(cursor.getColumnIndexOrThrow(MovieColumns.userScore));

            String title = cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.title));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.description));
            String dateOfRelase = cursor.getString(cursor.getColumnIndexOrThrow(dateOfRelease));
            String photoUrl = cursor.getString(cursor.getColumnIndexOrThrow(imgPhoto));
            String backdropUrl = cursor.getString(cursor.getColumnIndexOrThrow(backdropPhoto));
            int firstGenre = cursor.getInt(cursor.getColumnIndexOrThrow(genreId));
            movies.add(new Movie(id, firstGenre, userScore, title, description, dateOfRelase,
                    photoUrl, backdropUrl));
        }
        return movies;
    }

    public static ArrayList<TvShow> mapCursorTvShowToArrayList(Cursor cursor) {
        ArrayList<TvShow> tvShows = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            double userScore = cursor.getDouble(cursor.getColumnIndexOrThrow(TvColumns.userScore));

            String title = cursor.getString(cursor.getColumnIndexOrThrow(TvColumns.title));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(TvColumns.description));
            String dateOfRelase = cursor.getString(cursor.getColumnIndexOrThrow(TvColumns.dateOfRelease));
            String photoUrl = cursor.getString(cursor.getColumnIndexOrThrow(TvColumns.imgPhoto));
            String backdropUrl = cursor.getString(cursor.getColumnIndexOrThrow(TvColumns.backdropPhoto));
            int firstGenre = cursor.getInt(cursor.getColumnIndexOrThrow(TvColumns.genreId));
            tvShows.add(new TvShow(id, firstGenre, userScore, title, description, dateOfRelase,
                    photoUrl, backdropUrl));
        }
        return tvShows;
    }
}
