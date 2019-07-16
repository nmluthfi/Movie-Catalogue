package com.android.example.moviewcatalogue.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.android.example.moviewcatalogue.model.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {

    private static final String API_KEY = "af47732ccfe067085f13970fee065143";
    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();

    public void setMovie(String currentLanguage) {
        // request API
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> listItem = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + API_KEY +
                "&language=" + currentLanguage;
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
                Log.e("ERROR MOVIE VIEW MODEL", "ERROR MOVIE VIEW MODEL", error);
            }
        });
    }


    public LiveData<ArrayList<Movie>> getMovies() {
        return listMovies;
    }

}
