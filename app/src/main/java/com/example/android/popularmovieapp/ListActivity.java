package com.example.android.popularmovieapp;

import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    public static Resources resources;

    public static int page = 1;

    public static TextView tvMovies = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        resources = getResources();
        tvMovies = (TextView) findViewById(R.id.tv_debug);

        updateUI();
    }

    public void updateUI() {
        new ListActivityTask().execute();
    }

    public static void redrawMovies(ArrayList<MovieStruct> movies) {
        ListActivity.tvMovies.setText("");

        for (MovieStruct movie : movies) {
            ListActivity.tvMovies.append(movie.title + "\n\n");
        }
    }

    class ListActivityTask extends AsyncTask<Void, Void, ArrayList<MovieStruct>> {
        @Override
        protected ArrayList<MovieStruct> doInBackground(Void... params) {
            ArrayList<MovieStruct> movies = MovieDbUtil.getPopular(ListActivity.page);
            return movies;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieStruct> movies) {
            super.onPostExecute(movies);
            ListActivity.redrawMovies(movies);
        }
    }

    public String getMovies() {
        URL url = buildUrl();

        return url.toString();
    }

    public URL buildUrl() {
        // http://api.themoviedb.org/3/movie/popular?api_key=510534db34a94e8c595a4d0382d336f0

        Uri uri = Uri.parse("http://api.themoviedb.org/3/movie/popular")
                .buildUpon()
                .appendQueryParameter("api_key", getString(R.string.API_KEY))
                .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v("movie app", "Built URI " + url);

        return url;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
