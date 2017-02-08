package com.example.android.popularmovieapp;

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
}
