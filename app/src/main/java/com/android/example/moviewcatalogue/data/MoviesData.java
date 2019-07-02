package com.android.example.moviewcatalogue.data;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesData {

    public static String[][] data = new String[][]{
            {"Aquaman", "2018", "70", String.valueOf(R.drawable.poster_aquaman), "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orms half-human, half-Atlantean brother and true heir to the throne."},
            {"A Star is Born", "2018", "65", String.valueOf(R.drawable.poster_a_star), "Seasoned musician Jackson Maine discovers and falls in love with struggling artist Ally. She has just about given up on her dream to make it big as a singer until Jack coaxes her into the spotlight. But even as Allys career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons."},
            {"Avengers Infinity War", "2018", "50", String.valueOf(R.drawable.poster_avengerinfinity), "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain."},
            {"Bird Box", "2017", "90", String.valueOf(R.drawable.poster_birdbox), "Five years after an ominous unseen presence drives most of society to suicide, a survivor and her two children make a desperate bid to reach safety."},
            {"Bohemian Rhapsody", "2019", "83", String.valueOf(R.drawable.poster_bohemian), "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock n roll band Queen in 1970. Hit songs become instant classics. When Mercurys increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet finding a way to keep the band together amid the success and excess."},
            {"Bumble Bee", "2018", "74", String.valueOf(R.drawable.poster_bumblebee), "On the run in the year 1987, Bumblebee finds refuge in a junkyard in a small Californian beach town. Charlie, on the cusp of turning 18 and trying to find her place in the world, discovers Bumblebee, battle-scarred and broken. When Charlie revives him, she quickly learns this is no ordinary yellow VW bug. Featured Crew"},
            {"CREED II", "2018", "67", String.valueOf(R.drawable.poster_creed), "Between personal obligations and training for his next big fight against an opponent with ties to his familys past, Adonis Creed is up against the challenge of his life."},
            {"Once Upon A Deadpool", "2018", "55", String.valueOf(R.drawable.poster_deadpool), "A kidnapped Fred Savage is forced to endure Deadpools PG-13 rendition of Deadpool 2 as a Princess Bride-esque story thats full of magic, wonder and zero Fs."},
            {"How to Train Your Dragon: The Hidden World", "2019", "90", String.valueOf(R.drawable.poster_dragon), "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind."},
            {"Dragon Ball Broly", "2018", "100", String.valueOf(R.drawable.poster_dragonball), "As Goku investigates the destruction of the Southern Galaxy, Vegeta is taken to be King of the New Planet Vegeta, and to destroy the Legendary Super Saiyan, Broly."}
    };

    public static List<Movie> getListData() {
        Movie movie = null;
        List<Movie> list = new ArrayList<>();
        for (String[] aData : data) {
            movie = new Movie();
            movie.setTitle(aData[0]);
            movie.setDateOfRelease(aData[1]);
            movie.setUserScore(aData[2]);
            movie.setImgPhoto(Integer.parseInt(aData[3]));
            movie.setDescription(aData[4]);

            list.add(movie);
        }

        return list;
    }
}
