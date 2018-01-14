package com.example.dhanaruban.mymoviesapp.utilities;

/**
 * Created by dhanaruban on 04/01/18.
 */

import android.net.Uri;

import com.example.dhanaruban.mymoviesapp.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    private final static String MOVIESDB_BASE_URL = "https://api.themoviedb.org/3";

    private final static String PATH_MOVIE_POPULAR = "movie/popular";

    private final static String PATH_MOVIE_TOP_RATED = "movie/top_rated";

    private final static String API_KEY = "api_key";

    private final static String PAGE = "page";

    /**
     * Builds the URL used to query GitHub.
     *
     * @param pageNo The keyword that will be queried for.
     * @return The URL to use to query the GitHub.
     */
    public static URL buildUrl(String pageNo, String api_key) {
        String restPath;
        //TODO added API Key to here
        //String api_key = getString(R.string.api_key);
        //String api_key = "29346dc366c5346cd0d8d8a4a9b8cc8d";

        if (MainActivity.getmSortOption() == 1) {
            restPath = PATH_MOVIE_POPULAR;
        } else {
            restPath = PATH_MOVIE_TOP_RATED;
        }

        Uri builtUri = Uri.parse(MOVIESDB_BASE_URL).buildUpon()
                .appendEncodedPath(restPath)
                .appendQueryParameter(API_KEY, api_key)
                .appendQueryParameter(PAGE, pageNo)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
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