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
import com.android.example.moviewcatalogue.adapter.recycler_view.favorite_item.FavoriteTvShowAdapter;
import com.android.example.moviewcatalogue.database.tv_show.TvShowHelper;
import com.android.example.moviewcatalogue.interfaces.LoadTvShowCallback;
import com.android.example.moviewcatalogue.model.TvShow;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvShowFragment extends Fragment implements LoadTvShowCallback {


    private static final String EXTRA_STATE = "EXTRA_STATE";

    private RecyclerView rvFavoriteTvShow;
    private ProgressBar progressBar;
    private TextView tvEmptyState;

    private FavoriteTvShowAdapter adapter;
    private TvShowHelper tvShowHelper;


    public FavoriteTvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);
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
        tvShowHelper.close();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getListTvShows());
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
    public void postExecute(ArrayList<TvShow> tvShows) {
        progressBar.setVisibility(View.GONE);
        if (tvShows.size() != 0) {
            adapter.setListTvShows(tvShows);
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
        progressBar = view.findViewById(R.id.pb_load_favorite_tv_show);

        tvEmptyState = view.findViewById(R.id.tv_empty_favorite_tv_show);

        rvFavoriteTvShow = view.findViewById(R.id.rv_favorite_tv_show);
        rvFavoriteTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavoriteTvShow.setHasFixedSize(true);

        tvShowHelper = TvShowHelper.getInstance(getContext());
        tvShowHelper.open();

        adapter = new FavoriteTvShowAdapter(getActivity());
        rvFavoriteTvShow.setAdapter(adapter);

        if (savedInstanceState == null) {
            new LoadTvShowAsync(tvShowHelper, this).execute();
        } else {
            ArrayList<TvShow> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                adapter.setListTvShows(list);
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


    private static class LoadTvShowAsync extends AsyncTask<Void, Void, ArrayList<TvShow>> {

        private final WeakReference<TvShowHelper> weakTvShowHelper;
        private final WeakReference<LoadTvShowCallback> weakCallback;

        private LoadTvShowAsync(TvShowHelper tvShowHelper, LoadTvShowCallback callback) {
            weakTvShowHelper = new WeakReference<>(tvShowHelper);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
            Log.d("onPreExecute", "onPreExecute");
        }
        @Override
        protected ArrayList<TvShow> doInBackground(Void... voids) {
            Log.d("doInBackground", "doInBackground");
            return weakTvShowHelper.get().getAllFavoriteTvShow();
        }

        @Override
        protected void onPostExecute(ArrayList<TvShow> tvShows) {
            super.onPostExecute(tvShows);
            weakCallback.get().postExecute(tvShows);
            Log.d("onPostExecute", "onPostExecute");
        }
    }
}
