package com.android.example.moviewcatalogue.ui.main_menu.main_menu_fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.adapter.recycler_view.MovieAdapter;
import com.android.example.moviewcatalogue.model.Movie;
import com.android.example.moviewcatalogue.utils.LanguageFormater;
import com.android.example.moviewcatalogue.viewModel.MovieViewModel;

import java.util.ArrayList;

public class MovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieViewModel movieViewModel;
    private MovieAdapter movieAdapter;

    private ProgressBar pbLoadData;
    private TextView tvFailedLoadData;

    public MovieFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponent(view);
        initRecyclerView();
        setupActionbar();
        loadData();
    }

    private void loadData() {
        movieViewModel.setMovie(LanguageFormater.checkCurrentLanguage());
        showLoading(true);
    }

    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Movie> movies) {
            if (movies != null) {
                if (movies.size() != 0) {
                    movieAdapter.setmData(movies);
                    showLoading(false);
                } else {
                    tvFailedLoadData.setVisibility(View.VISIBLE);
                }
            } else {
                tvFailedLoadData.setVisibility(View.VISIBLE);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            pbLoadData.setVisibility(View.VISIBLE);
        } else {
            pbLoadData.setVisibility(View.GONE);
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        movieAdapter = new MovieAdapter(getContext());
        movieAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(movieAdapter);
    }

    private void initComponent(View container) {
        recyclerView = container.findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, getMovie);

        pbLoadData = container.findViewById(R.id.pb_loading_list_data);

        tvFailedLoadData = container.findViewById(R.id.tv_failed_load_data);
    }

    /*
     * Method fo setting custom title ActionBar depends on the fragment
     * */
    private void setupActionbar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.category_movie));
            actionBar.show();
        }
    }
}


