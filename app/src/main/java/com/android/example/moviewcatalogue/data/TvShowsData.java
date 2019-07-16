package com.android.example.moviewcatalogue.data;

import com.android.example.moviewcatalogue.R;

public class TvShowsData {

    public static String[][] data = new String[][]{
            {"Arrow", "2012", "58", String.valueOf(R.drawable.poster_arrow), String.valueOf(R.string.arrow_description)},
            {"Doom Patrol", "2019", "61", String.valueOf(R.drawable.poster_doom_patrol), String.valueOf(R.string.doom_patrol_description)},
            {"Fairy Tail ", "2009", "64", String.valueOf(R.drawable.poster_fairytail), String.valueOf(R.string.fairy_tail_description)},
            {"Family Guy ", "1999", "65", String.valueOf(R.drawable.poster_family_guy), String.valueOf(R.string.family_guy_description)},
            {"The Flash", "2014", "67", String.valueOf(R.drawable.poster_flash), String.valueOf(R.string.flash_description)},
            {"Game of Thrones", "2011", "81", String.valueOf(R.drawable.poster_god), String.valueOf(R.string.game_of_thrones_description)},
            {"Gotham", "2014", "69", String.valueOf(R.drawable.poster_gotham), String.valueOf(R.string.gotham_description)},
            {"Grey's Anatomy ", "2005", "62", String.valueOf(R.drawable.poster_grey_anatomy), String.valueOf(R.string.grey_description)},
            {"Hanna", "2019", "64", String.valueOf(R.drawable.poster_hanna), String.valueOf(R.string.hanna_description)},
            {"Marvel's Iron Fist", "2017", "61", String.valueOf(R.drawable.poster_iron_fist), String.valueOf(R.string.iron_fist_description)}
    };

//    public static List<TvShow> getListData() {
//        TvShow tvShow = null;
//        List<TvShow> list = new ArrayList<>();
//        for (String[] aData : data) {
//            tvShow = new TvShow();
//            tvShow.setTitle(aData[0]);
//            tvShow.setDateOfFirstAir(aData[1]);
//            tvShow.setUserScore(aData[2]);
//            tvShow.setImgPhoto(Integer.parseInt(aData[3]));
//            tvShow.setDescription(aData[4]);
//
//            list.add(tvShow);
//        }
//
//        return list;
//    }
}
