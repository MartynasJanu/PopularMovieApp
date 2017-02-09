package com.example.android.popularmovieapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

public class MovieDbUtil {
    private static final String URL_HOST = "api.themoviedb.org";
    private static final String URL_PATH_POPULAR = "3/movie/popular";

    public static ArrayList<MovieStruct> getPopular() {
        return getPopular(1);
    }

    public static ArrayList<MovieStruct> getPopular(int page) {
        String raw = fetchRawPopularContent(page);
        if (raw == null) {
            return null;
        }

        return parseRawJSON(raw);
    }

    private static ArrayList<MovieStruct> parseRawJSON(String jsonString) {
        ArrayList<MovieStruct> movies = new ArrayList<MovieStruct>();

        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray results = json.getJSONArray("results");

            int c = results.length();

            for (int i = 0; i < c; ++i) {
                JSONObject movieObj = results.getJSONObject(i);
                MovieStruct movie = new MovieStruct(movieObj);
                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return movies;
    }

    private static String fetchRawPopularContent(int page) {
        ArrayList<String[]> params = getDefaultQueryParams();
        addPageParamToParams(params, page);

        URL url = NetworkUtil.buildUrl(URL_HOST, URL_PATH_POPULAR, params);
        String raw;
        try {
            return NetworkUtil.fetchRawContentFromUrl(url);
        } catch (IOException e) {
            return null;
        }
    }

    private static ArrayList<String[]> getDefaultQueryParams() {
        String[] apiKey = {
                "api_key",
                ListActivity.resources.getString(R.string.API_KEY)
        };


        ArrayList<String[]> params = new ArrayList<String[]>();
        params.add(apiKey);

        return params;
    }

    private static ArrayList<String[]> addPageParamToParams(ArrayList<String[]> params, int page) {
        String[] pageParam = {"page", String.valueOf(page)};
        params.add(pageParam);
        return params;
    }
}
