package com.android.example.moviewcatalogue.utils;

import java.util.ArrayList;

public class GenreChecks {

    public static ArrayList<String> MovieGenre(ArrayList<Integer> genre) {
        ArrayList<String> genreInString = new ArrayList<>();
        String currentLanguage = LanguageFormater.checkCurrentLanguage();
        for (int i = 0; i < genre.size(); i++) {
            if (genre.get(i) == 28) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Action");
                } else {
                    genreInString.add("Aksi");
                }
            }
            if (genre.get(i) == 12) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Adventure");
                } else {
                    genreInString.add("Petualangan");
                }
            }
            if (genre.get(i) == 16) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Animation");
                } else {
                    genreInString.add("Animasi");
                }
            }
            if (genre.get(i) == 35) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Comedy");
                } else {
                    genreInString.add("Komedi");
                }
            }
            if (genre.get(i) == 80) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Crime");
                } else {
                    genreInString.add("Kejahatan");
                }
            }
            if (genre.get(i) == 99) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Documentary");
                } else {
                    genreInString.add("Dokumentasi");
                }
            }
            if (genre.get(i) == 18) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Drama");
                } else {
                    genreInString.add("Drama");
                }
            }
            if (genre.get(i) == 10751) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Family");
                } else {
                    genreInString.add("Keluarga");
                }
            }
            if (genre.get(i) == 14) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Fantasy");
                } else {
                    genreInString.add("Fantasi");
                }
            }
            if (genre.get(i) == 36) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("History");
                } else {
                    genreInString.add("Sejarah");
                }
            }
            if (genre.get(i) == 27) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Horror");
                } else {
                    genreInString.add("Hantu");
                }
            }
            if (genre.get(i) == 10402) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Music");
                } else {
                    genreInString.add("Musik");
                }
            }
            if (genre.get(i) == 9468) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Mystery");
                } else {
                    genreInString.add("Ilmiah");
                }
            }
            if (genre.get(i) == 878) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Science Fiction");
                } else {
                    genreInString.add("Fiksi Ilmiah");
                }
            }
            if (genre.get(i) == 10770) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("TV Movie");
                } else {
                    genreInString.add("Tayangan TV");
                }
            }
            if (genre.get(i) == 53) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Thriller");
                } else {
                    genreInString.add("Menegangkan");
                }
            }
            if (genre.get(i) == 10752) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("War");
                } else {
                    genreInString.add("Perang");
                }
            }
            if (genre.get(i) == 37) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Western");
                } else {
                    genreInString.add("Budaya barat");
                }
            }
        }
        return genreInString;
    }

    public static ArrayList<String> TvShowGenre(ArrayList<Integer> genre) {
        ArrayList<String> genreInString = new ArrayList<>();
        String currentLanguage = LanguageFormater.checkCurrentLanguage();
        for (int i = 0; i < genre.size(); i++) {
            if (genre.get(i) == 10759) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Action and Adventure");
                } else {
                    genreInString.add("Aksi dan Petualangan");
                }
            }
            if (genre.get(i) == 16) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Animation");
                } else {
                    genreInString.add("Animasi");
                }
            }
            if (genre.get(i) == 35) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Comedy");
                } else {
                    genreInString.add("Komedi");
                }
            }
            if (genre.get(i) == 80) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Crime");
                } else {
                    genreInString.add("Kejahatan");
                }
            }
            if (genre.get(i) == 99) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Documentary");
                } else {
                    genreInString.add("Dokumentasi");
                }
            }
            if (genre.get(i) == 18) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Drama");
                } else {
                    genreInString.add("Drama");
                }
            }
            if (genre.get(i) == 10751) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Family");
                } else {
                    genreInString.add("Keluarga");
                }
            }
            if (genre.get(i) == 14) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Fantasy");
                } else {
                    genreInString.add("Fantasi");
                }
            }
            if (genre.get(i) == 36) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("History");
                } else {
                    genreInString.add("Sejarah");
                }
            }
            if (genre.get(i) == 27) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Horror");
                } else {
                    genreInString.add("Hantu");
                }
            }
            if (genre.get(i) == 10402) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Music");
                } else {
                    genreInString.add("Musik");
                }
            }
            if (genre.get(i) == 9468) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Mystery");
                } else {
                    genreInString.add("Ilmiah");
                }
            }
            if (genre.get(i) == 878) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Science Fiction");
                } else {
                    genreInString.add("Fiksi Ilmiah");
                }
            }
            if (genre.get(i) == 10770) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("TV Movie");
                } else {
                    genreInString.add("Tayangan TV");
                }
            }
            if (genre.get(i) == 53) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Thriller");
                } else {
                    genreInString.add("Menegangkan");
                }
            }
            if (genre.get(i) == 10752) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("War");
                } else {
                    genreInString.add("Perang");
                }
            }
            if (genre.get(i) == 37) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Western");
                } else {
                    genreInString.add("Budaya barat");
                }
            }
        }
        return genreInString;
    }
}
