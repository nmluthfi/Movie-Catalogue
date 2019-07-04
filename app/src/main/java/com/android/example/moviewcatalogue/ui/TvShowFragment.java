package com.android.example.moviewcatalogue.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.adapter.TvShowAdapter;
import com.android.example.moviewcatalogue.data.TvShowsData;
import com.android.example.moviewcatalogue.model.TvShow;

import java.util.ArrayList;

public class TvShowFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<TvShow> tvShows = new ArrayList<>();

    public TvShowFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_item, container, false);

        initComponent(rootView);
        initRecyclerView();

        tvShows.addAll(TvShowsData.getListData());

        return rootView;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TvShowAdapter tvShowAdapter = new TvShowAdapter(getContext());
        tvShowAdapter.setmData(tvShows);
        recyclerView.setAdapter(tvShowAdapter);
    }

    private void initComponent(View container) {
        recyclerView = container.findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);
    }
}
