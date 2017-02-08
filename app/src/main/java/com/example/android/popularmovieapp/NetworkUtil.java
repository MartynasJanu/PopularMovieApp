package com.example.android.popularmovieapp;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

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

    public String fetchContent(URI uri) {
        return "";
    }
}
