package com.android.example.moviewcatalogue.database.tv_show;

import android.provider.BaseColumns;

public class TvShowContract {
    public static String TABLE_TV_SHOW = "tv_show";

    public static final class TvColumns implements BaseColumns {

        public static String title = "title";
        public static String description = "description";
        public static String dateOfRelease = "dateOfRelease";
        public static String imgPhoto = "imgPhoto";
        public static String backdropPhoto = "backdropPhoto";
        public static String userScore = "userScore";
        public static String genreId = "genreId";

    }
}
