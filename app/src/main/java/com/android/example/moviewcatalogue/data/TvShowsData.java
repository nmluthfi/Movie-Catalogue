package com.android.example.moviewcatalogue.data;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.model.TvShow;

import java.util.ArrayList;
import java.util.List;

public class TvShowsData {

    public static String[][] data = new String[][]{
            {"Aquaman", "2018", "70", String.valueOf(R.drawable.poster_aquaman), String.valueOf(R.string.aquaman_description)},
            {"A Star is Born", "2018", "65", String.valueOf(R.drawable.poster_a_star), String.valueOf(R.string.a_star_is_born_description)},
            {"Avengers Infinity War", "2018", "50", String.valueOf(R.drawable.poster_avengerinfinity), String.valueOf(R.string.avengers_infinity_description)},
            {"Bird Box", "2017", "90", String.valueOf(R.drawable.poster_birdbox), String.valueOf(R.string.bird_box_description)},
            {"Bohemian Rhapsody", "2019", "83", String.valueOf(R.drawable.poster_bohemian), String.valueOf(R.string.boheiman_rhapsody_description)},
            {"Bumble Bee", "2018", "74", String.valueOf(R.drawable.poster_bumblebee), String.valueOf(R.string.bumble_bee_description)},
            {"CREED II", "2018", "67", String.valueOf(R.drawable.poster_creed), String.valueOf(R.string.creed_description)},
            {"Once Upon A Deadpool", "2018", "55", String.valueOf(R.drawable.poster_deadpool), String.valueOf(R.string.deadpool_description)},
            {"How to Train Your Dragon: The Hidden World", "2019", "90", String.valueOf(R.drawable.poster_dragon), String.valueOf(R.string.how_to_train_description)},
            {"Dragon Ball Broly", "2018", "100", String.valueOf(R.drawable.poster_dragonball), String.valueOf(R.string.dragon_ball_description)}
    };

    public static List<TvShow> getListData() {
        TvShow tvShow = null;
        List<TvShow> list = new ArrayList<>();
        for (String[] aData : data) {
            tvShow = new TvShow();
            tvShow.setTitle(aData[0]);
            tvShow.setDateOfRelease(aData[1]);
            tvShow.setUserScore(aData[2]);
            tvShow.setImgPhoto(Integer.parseInt(aData[3]));
            tvShow.setDescription(aData[4]);

            list.add(tvShow);
        }

        return list;
    }
}
