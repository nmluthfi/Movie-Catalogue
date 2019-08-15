package com.android.example.moviewcatalogue.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.android.example.moviewcatalogue.model.TvShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvShowViewModel extends ViewModel {

    private static final String API_KEY = "af47732ccfe067085f13970fee065143";
    private MutableLiveData<ArrayList<TvShow>> listTvShows = new MutableLiveData<>();

    public void setTvShow(String currentLanguage) {
        // request API
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShow> listItem = new ArrayList<>();
        String url = "https://api.themoviedb.org/4/discover/tv?api_key=" + API_KEY +
                "&language=" + currentLanguage;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray tvShowResult = responseObject.getJSONArray("results");
                    for (int i = 0; i < tvShowResult.length(); i++) {
                        JSONObject currentTvShow = tvShowResult.getJSONObject(i);
                        TvShow tvShow = new TvShow(currentTvShow);
                        listItem.add(tvShow);
                    }
                    listTvShows.postValue(listItem);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("ERROR MAIN VIEW MODEL", "ERROR MAIN VIEW MODEL", error);
            }
        });
    }

    public LiveData<ArrayList<TvShow>> getTvShows() {
        return listTvShows;
    }
}
