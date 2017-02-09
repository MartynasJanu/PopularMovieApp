package com.example.android.popularmovieapp;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
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
    public static final int GRID_PORTRAIT_SPANS = 2;
    public static final int GRID_LANDSCAPE_SPANS = 4;

    public static Resources resources;

    public static int page = 1;

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private GridLayoutManager gridLayout;

    public static Context context;

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
                updateUI();
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
            }
        });

        wireUpRecyclerView();

        resources = getResources();
        context = this.getApplicationContext();

        updateUI();
    }

    public void updateUI() {
        gridLayout.removeAllViews();
        new ListActivityTask().execute();
    }

    private void wireUpRecyclerView() {
        int orientation = this.getResources().getConfiguration().orientation;

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_movies);
        updateGridSize(orientation);
        recyclerView.setHasFixedSize(true);
        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setVisibility(recyclerView.VISIBLE);
    }

    private void updateGridSize(int orientation) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridLayout = new GridLayoutManager(this, GRID_LANDSCAPE_SPANS);
            recyclerView.setLayoutManager(gridLayout);
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT){
            gridLayout = new GridLayoutManager(this, GRID_PORTRAIT_SPANS);
            recyclerView.setLayoutManager(gridLayout);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateGridSize(newConfig.orientation);
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
            movieAdapter.setData(movies);
        }
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
