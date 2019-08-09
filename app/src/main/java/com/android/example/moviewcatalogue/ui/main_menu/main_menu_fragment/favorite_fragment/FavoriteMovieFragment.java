package com.android.example.moviewcatalogue.ui.main_menu.main_menu_fragment.favorite_fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.adapter.recycler_view.favorite_item.FavoriteMovieAdapter;
import com.android.example.moviewcatalogue.database.movie.MovieHelper;
import com.android.example.moviewcatalogue.interfaces.LoadMovieCallback;
import com.android.example.moviewcatalogue.model.Movie;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment implements LoadMovieCallback {

    private static final String EXTRA_STATE = "EXTRA_STATE";

    private RecyclerView rvNotes;
    private ProgressBar progressBar;
    private TextView tvEmptyState;

    private FavoriteMovieAdapter adapter;
    private MovieHelper movieHelper;


    public FavoriteMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initComponent(view, savedInstanceState);
        setupActionbar();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getListMovies());
    }

    @Override
    public void preExecute() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                tvEmptyState.setVisibility(View.GONE);
                Log.d("preExecute", "PreExecute");
            }
        });
    }

    @Override
    public void postExecute(ArrayList<Movie> movies) {
        progressBar.setVisibility(View.GONE);
        if (movies != null) {
            adapter.setListMovies(movies);
            tvEmptyState.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            Log.d("MOVIE TIDAK NULL", " MOVIE TIDAK NULL");
        } else {
            tvEmptyState.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            Log.d("MOVIE NULL", " MOVIE NULL");
        }
        Log.d("postExecute", "postExecute");
    }

    private void initComponent(View view, Bundle savedInstanceState) {
        progressBar = view.findViewById(R.id.pb_load_favorite_movie);

        tvEmptyState = view.findViewById(R.id.tv_empty_favorite_movie);

        rvNotes = view.findViewById(R.id.rv_favorite_movie);
        rvNotes.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNotes.setHasFixedSize(true);

        movieHelper = MovieHelper.getInstance(getContext());
        movieHelper.open();

        adapter = new FavoriteMovieAdapter(getActivity());
        rvNotes.setAdapter(adapter);

        if (savedInstanceState == null) {
            new LoadMovieAsync(movieHelper, this).execute();
        } else {
            ArrayList<Movie> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                adapter.setListMovies(list);
            }
        }
    }

    private void setupActionbar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.category_favorite));
            actionBar.show();
        }
    }


    private static class LoadMovieAsync extends AsyncTask<Void, Void, ArrayList<Movie>> {

        private final WeakReference<MovieHelper> weakMovieHelper;
        private final WeakReference<LoadMovieCallback> weakCallback;

        private LoadMovieAsync(MovieHelper movieHelper, LoadMovieCallback callback) {
            weakMovieHelper = new WeakReference<>(movieHelper);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
            Log.d("onPreExecute", "onPreExecute");
        }
        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            Log.d("doInBackground", "doInBackground");
            return weakMovieHelper.get().getAllFavoriteMovie();
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);
            weakCallback.get().postExecute(movies);
            Log.d("onPostExecute", "onPostExecute");
        }
    }
}
