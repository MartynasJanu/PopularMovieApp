package com.example.android.popularmovieapp;

import java.net.URL;
import java.util.ArrayList;

public class MovieDbUtil {
    private static final String URL_HOST = "api.themoviedb.org";
    private static final String URL_PATH_POPULAR = "3/movie/popular";

    public static MovieStruct[] getPopular() {
        return getPopular(1);
    }

    public static MovieStruct[] getPopular(int page) {
        String a = fetchPopularContentAsArray(page);
        return null;
        //String rawData = NetworkUtils.fetchRawContent();
    }

    private static String fetchPopularContentAsArray(int page) {
        ArrayList<String[]> params = getDefaultQueryParams();
        addPageParamToParams(params, page);

        URL url = NetworkUtil.buildUrl(URL_HOST, URL_PATH_POPULAR, params);
        return null;
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
