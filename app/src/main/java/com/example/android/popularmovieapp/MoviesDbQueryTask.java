package com.example.android.popularmovieapp;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

public class MoviesDbQueryTask extends AsyncTask<URL, Void, String> {
    @Override
    protected String doInBackground(URL... params) {
        URL url = params[0];
        String content = "";
        try {
            content = NetworkUtil.fetchRawContentFromUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // COMPLETED (3) Override onPostExecute to display the results in the TextView
    @Override
    protected void onPostExecute(String githubSearchResults) {
//        if (githubSearchResults != null && !githubSearchResults.equals("")) {
//            mSearchResultsTextView.setText(githubSearchResults);
//        }
    }
}
