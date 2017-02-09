package com.example.android.popularmovieapp;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class NetworkUtil {
    public static URL buildUrl(String host) {
        Uri uri = Uri.parse(host)
                .buildUpon()
                .scheme("http")
                .build();

        return NetworkUtil.Uri2URL(uri);
    }

    public static URL buildUrl(String host, String path) {
        Uri uri = Uri.parse(host)
                .buildUpon()
                .scheme("http")
                .appendEncodedPath(path)
                .build();

        return NetworkUtil.Uri2URL(uri);
    }

    public static URL buildUrl(String host, String path, ArrayList<String[]> params) {
        Uri.Builder uriBuilder = Uri.parse(host)
                .buildUpon()
                .scheme("http")
                .appendEncodedPath(path);

        for (String[] keyValue : params) {
            uriBuilder.appendQueryParameter(keyValue[0], keyValue[1]);
        }

        Uri uri = uriBuilder.build();

        return NetworkUtil.Uri2URL(uri);
    }

    private static URL Uri2URL(Uri uri) {
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String fetchRawContentFromUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
