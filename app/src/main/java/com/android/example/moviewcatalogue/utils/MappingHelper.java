package com.android.example.moviewcatalogue.utils;

import android.database.Cursor;

import com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns;
import com.android.example.moviewcatalogue.model.Movie;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.backdropPhoto;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.dateOfRelease;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.genreId;
import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.imgPhoto;


public class MappingHelper {

    public static ArrayList<Movie> mapCursorToArrayList(Cursor cursor) {
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
}
