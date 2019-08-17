package com.android.example.moviewcatalogue.ui.main_menu.main_menu_fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.adapter.recycler_view.TvShowAdapter;
import com.android.example.moviewcatalogue.model.TvShow;
import com.android.example.moviewcatalogue.utils.LanguageFormater;
import com.android.example.moviewcatalogue.viewModel.TvShowViewModel;

import java.util.ArrayList;

public class TvShowFragment extends Fragment {

    private static final String KEY_QUERY = "query";

    private RecyclerView recyclerView;

    private TvShowViewModel tvShowViewModel, searchTvShowViewModel;
    private TvShowAdapter tvShowAdapter;

    private ProgressBar pbLoadData;
    private TextView tvFailedLoadData;
    private EditText etSearch;

    private String query;

    public TvShowFragment() {
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

        if (savedInstanceState == null) {
            loadData();
        } else {
            query = savedInstanceState.getString(KEY_QUERY);
            searchTvShow(query);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(KEY_QUERY, query);
        super.onSaveInstanceState(outState);
    }

    private void loadData() {
        tvShowViewModel.setTvShow(LanguageFormater.checkCurrentLanguage());
        showLoading(true);
    }

    private Observer<ArrayList<TvShow>> getTvShow = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShow> tvShows) {
            if (tvShows != null) {
                if (tvShows.size() != 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    tvShowAdapter.setmData(tvShows);
                    showLoading(false);
                } else {
                    tvFailedLoadData.setVisibility(View.VISIBLE);
                }
            } else {
                tvFailedLoadData.setVisibility(View.VISIBLE);
            }
        }
    };

    private void searchTvShow(String editable) {
        searchTvShowViewModel.searchTvShow(LanguageFormater.checkCurrentLanguage(), editable);
        showLoading(true);
    }

    private Observer<ArrayList<TvShow>> getSearchTvShow = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShow> tvShows) {
            if (tvShows != null) {
                if (tvShows.size() != 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    tvShowAdapter.setmData(tvShows);
                    showLoading(false);
                } else {
                    tvFailedLoadData.setVisibility(View.VISIBLE);
                }
            } else {
                tvFailedLoadData.setVisibility(View.VISIBLE);
            }

            tvShowAdapter.filterList(tvShows);
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
        tvShowAdapter = new TvShowAdapter(getContext());
        tvShowAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(tvShowAdapter);
    }

    private void initComponent(View container) {
        recyclerView = container.findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);

        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        tvShowViewModel.getTvShows().observe(this, getTvShow);

        searchTvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        searchTvShowViewModel.getSearchTvShows().observe(this, getSearchTvShow);

        pbLoadData = container.findViewById(R.id.pb_loading_list_data);

        tvFailedLoadData = container.findViewById(R.id.tv_failed_load_data);

        etSearch = container.findViewById(R.id.et_search_item);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                query = editable.toString();
                recyclerView.setVisibility(View.GONE);
                if (query.equalsIgnoreCase("")) {
                    loadData();
                } else {
                    searchTvShow(editable.toString());
                }
            }
        });
    }

}
