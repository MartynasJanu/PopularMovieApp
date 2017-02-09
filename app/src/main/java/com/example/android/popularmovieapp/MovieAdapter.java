package com.example.android.popularmovieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
    private ArrayList<MovieStruct> movies;

    public MovieAdapter() {

    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder movieAdapterViewHolder, int position) {
        String title = movies.get(position).original_title;
        //movieAdapterViewHolder.tvMovie.setText(title);
        Picasso.with(ListActivity.context)
            .load(movies.get(position).getFullPosterPath())
            .into(movieAdapterViewHolder.imgPoster);
    }

    @Override
    public int getItemCount() {
        if (movies == null) {
            return 0;
        }

        return movies.size();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder {
        //public final TextView tvMovie;
        public final ImageView imgPoster;

        public MovieAdapterViewHolder(View view) {
            super(view);
            //tvMovie = (TextView) view.findViewById(R.id.tv_movie_title);
            imgPoster = (ImageView) view.findViewById(R.id.img_movie_poster);
        }
    }

    public void setData(ArrayList<MovieStruct> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }
}
