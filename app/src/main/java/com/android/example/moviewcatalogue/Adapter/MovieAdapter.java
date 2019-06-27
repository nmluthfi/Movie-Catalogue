package com.android.example.moviewcatalogue.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.example.moviewcatalogue.Model.Movie;
import com.android.example.moviewcatalogue.R;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context mContext) {
        this.mContext = mContext;
        movies = new ArrayList<>();
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_movies,
                    parent,
                    false);
        }

        MovieViewHolder movieViewHolder = new MovieViewHolder(convertView);
        Movie movie = (Movie) getItem(position);
        movieViewHolder.bind(movie);
        return convertView;
    }

    private class MovieViewHolder {
        private TextView tvTitle, tvDescription;
        private ImageView imgPhoto;

        MovieViewHolder(View view) {
            tvTitle = view.findViewById(R.id.tv_title);
            tvDescription = view.findViewById(R.id.tv_description);
            imgPhoto = view.findViewById(R.id.img_photo);
        }

        void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvDescription.setText(movie.getDescription());
            imgPhoto.setImageResource(movie.getImgPhoto());
        }
    }
}
