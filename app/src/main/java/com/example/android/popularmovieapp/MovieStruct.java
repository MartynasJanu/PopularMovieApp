package com.example.android.popularmovieapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieStruct {
    public int id;
    public String original_language;
    public String original_title;
    public String title;
    public String overview;

    public double popularity;
    public int vote_count;
    public double vote_average;

    public String poster_path;
    public String backdrop_path;

    public boolean video;
    public boolean adult;
    public Date release_date;
    public GenreStruct[] genres;

    public MovieStruct(JSONObject movieObj) {
        try {
            this.id = movieObj.getInt("id");
            this.original_language = movieObj.getString("original_language");
            this.original_title = movieObj.getString("original_title");
            this.title = movieObj.getString("title");
            this.overview = movieObj.getString("overview");
            this.popularity = movieObj.getDouble("popularity");
            this.vote_count = movieObj.getInt("vote_count");
            this.vote_average = movieObj.getDouble("vote_average");
            this.poster_path = movieObj.getString("poster_path");
            this.backdrop_path = movieObj.getString("backdrop_path");
            this.video = movieObj.getBoolean("video");
            this.adult = movieObj.getBoolean("adult");
            //this.genres = movieObj.getBoolean("genres");

            this.setDateFromString(movieObj.getString("release_date"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setDateFromString(String dateString) {
        setDateFromString(dateString, "yyyy-MM-dd");
    }

    public void setDateFromString(String dateString, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            this.release_date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
