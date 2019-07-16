package com.android.example.moviewcatalogue.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.android.example.moviewcatalogue.model.Movie;
import com.android.example.moviewcatalogue.model.TvShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {

    private static final String API_KEY = "af47732ccfe067085f13970fee065143";
    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TvShow>> listTvShows = new MutableLiveData<>();

    public void setMovie() {
        // request API
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> listItem = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray movieResults = responseObject.getJSONArray("results");
                    for (int i = 0; i < movieResults.length(); i++) {
                        JSONObject currentMovie = movieResults.getJSONObject(i);
                        Movie movie = new Movie(currentMovie);
                        listItem.add(movie);
                    }
                    listMovies.postValue(listItem);
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

    void setTvShow() {
        // request API
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return listMovies;
    }

    public LiveData<ArrayList<TvShow>> getTvShows() {
        return listTvShows;
    }

}
