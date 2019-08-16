package com.android.example.moviewcatalogue.ui.main_menu.main_menu_fragment.favorite_fragment;


import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
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

import static com.android.example.moviewcatalogue.database.movie.MovieContract.MovieColumns.CONTENT_URI;
import static com.android.example.moviewcatalogue.utils.MappingHelper.mapCursorToArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment implements LoadMovieCallback {

    private static final String EXTRA_STATE = "EXTRA_STATE";

    private RecyclerView rvFavoriteMovie;
    private ProgressBar progressBar;
    private TextView tvEmptyState;

    private FavoriteMovieAdapter adapter;
    private MovieHelper movieHelper;

    private static HandlerThread handlerThread;
    private DataObserver myObserver;

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
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getListMovies());
        super.onSaveInstanceState(outState);
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
    public void postExecute(Cursor cursor) {
        progressBar.setVisibility(View.GONE);
        ArrayList<Movie> movies = mapCursorToArrayList(cursor);
        if (movies != null) {
            if (movies.size() > 0) {
                adapter.setListMovies(movies);
                tvEmptyState.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                Log.d("MOVIE TIDAK NULL", " MOVIE TIDAK NULL");
            } else {
                tvEmptyState.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                Log.d("MOVIE NULL", " MOVIE NULL");
            }
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

        rvFavoriteMovie = view.findViewById(R.id.rv_favorite_movie);
        rvFavoriteMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavoriteMovie.setHasFixedSize(true);

        movieHelper = MovieHelper.getInstance(getContext());
        movieHelper.open();

        adapter = new FavoriteMovieAdapter(getActivity());
        rvFavoriteMovie.setAdapter(adapter);

        handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        myObserver = new DataObserver(handler, getContext());
        getActivity().getContentResolver().registerContentObserver(CONTENT_URI, true, myObserver);


        if (savedInstanceState == null) {
            new LoadMovieAsync(getContext(), this).execute();
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
            actionBar.setTitle(getString(R.string.your_favorite));
            actionBar.show();
        }
    }


    private static class LoadMovieAsync extends AsyncTask<Void, Void,Cursor> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadMovieCallback> weakCallback;

        private LoadMovieAsync(Context context, LoadMovieCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
            Log.d("onPreExecute", "onPreExecute");
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            Log.d("doInBackground", "doInBackground");
            Context context = weakContext.get();
            return context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            weakCallback.get().postExecute(cursor);
            Log.d("onPostExecute", "onPostExecute");
        }
    }

    public static class DataObserver extends ContentObserver {
        final Context context;

        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
        }
    }
}
