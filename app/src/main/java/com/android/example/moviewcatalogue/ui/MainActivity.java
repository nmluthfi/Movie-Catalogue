package com.android.example.moviewcatalogue.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.adapter.MovieAdapter;
import com.android.example.moviewcatalogue.data.MoviesData;
import com.android.example.moviewcatalogue.model.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        initRecyclerView();

        movies.addAll(MoviesData.getListData());
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MovieAdapter movieAdapter = new MovieAdapter(this);
        movieAdapter.setmData(movies);
        recyclerView.setAdapter(movieAdapter);
    }

    private void initComponent() {
        recyclerView = findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);
    }

}
