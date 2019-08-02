package com.android.example.moviewcatalogue.database.movie;

import android.provider.BaseColumns;

public class MovieContract {

    public static String TABLE_MOVIE = "movie";

    public static final class MovieColumns implements BaseColumns {

        public static String title = "title";
        public static String description = "description";
        public static String dateOfRelease = "dateOfRelease";
        public static String imgPhoto = "imgPhoto";
        public static String backdropPhoto = "backdropPhoto";
        public static String userScore = "userScore";
        public static String genreId = "genreId";

    }

}
