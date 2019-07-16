package com.android.example.moviewcatalogue.utils;

import java.util.ArrayList;

public class GenreChecks {

    public static ArrayList<String> CheckGenre(ArrayList<Integer> genre) {
        ArrayList<String> genreInString = new ArrayList<>();
        for (int i = 0; i < genre.size(); i++) {
            if (genre.get(i) == 28) {
                genreInString.add("Action");
            }
            if (genre.get(i) == 12) {
                genreInString.add("Adventure");
            }
            if (genre.get(i) == 16) {
                genreInString.add("Animation");
            }
            if (genre.get(i) == 35) {
                genreInString.add("Comedy");
            }
            if (genre.get(i) == 80) {
                genreInString.add("Crime");
            }
            if (genre.get(i) == 99) {
                genreInString.add("Documentary");
            }
            if (genre.get(i) == 18) {
                genreInString.add("Drama");
            }
            if (genre.get(i) == 10751) {
                genreInString.add("Family");
            }
            if (genre.get(i) == 14) {
                genreInString.add("Fantasy");
            }
            if (genre.get(i) == 36) {
                genreInString.add("History");
            }
            if (genre.get(i) == 27) {
                genreInString.add("Horror");
            }
            if (genre.get(i) == 10402) {
                genreInString.add("Music");
            }
            if (genre.get(i) == 9468) {
                genreInString.add("Mystery");
            }
            if (genre.get(i) == 878) {
                genreInString.add("Science Fiction");
            }
            if (genre.get(i) == 10770) {
                genreInString.add("TV Movie");
            }
            if (genre.get(i) == 53) {
                genreInString.add("Thriller");
            }
            if (genre.get(i) == 10752) {
                genreInString.add("War");
            }
            if (genre.get(i) == 37) {
                genreInString.add("Western");
            }
        }
        return genreInString;
    }
}
