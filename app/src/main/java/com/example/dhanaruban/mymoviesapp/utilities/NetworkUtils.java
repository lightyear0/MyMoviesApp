package com.example.dhanaruban.mymoviesapp.utilities;

/**
 * Created by dhanaruban on 04/01/18.
 */

import android.net.Uri;

import com.example.dhanaruban.mymoviesapp.BuildConfig;
import com.example.dhanaruban.mymoviesapp.MainActivity;
import com.example.dhanaruban.mymoviesapp.Movie;

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

    private final static String API_KEY_TOKEN = "api_key";

    private final static String PAGE = "page";



    private final static String TRAILER_PATH ="movie/";
    private final static String TRAILER_PATH_VIDEO ="/videos";

    private final static String REVIEW_PATH ="movie/";
    private final static String REVIEW = "/reviews";

    /**
     * Builds the URL used to query GitHub.
     *
     * @param pageNo The keyword that will be queried for.
     * @return The URL to use to query the GitHub.
     */
    public static URL buildUrl(String pageNo, String api_key) {
        String restPath;
        //TODO added API Key to here
          final String API_KEY = BuildConfig.API_KEY;

        if (MainActivity.getmSortOption() == 1) {
            restPath = PATH_MOVIE_POPULAR;
        } else {
            restPath = PATH_MOVIE_TOP_RATED;
        }

        Uri builtUri = Uri.parse(MOVIESDB_BASE_URL).buildUpon()
                .appendEncodedPath(restPath)
                .appendQueryParameter(API_KEY_TOKEN,API_KEY)
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
    public static URL buildTrailerUrl(String id) {

        //TODO added API Key to here
        final String API_KEY = BuildConfig.API_KEY;

        Uri builtUri = Uri.parse(MOVIESDB_BASE_URL).buildUpon()
                .appendEncodedPath(TRAILER_PATH)
                .appendEncodedPath(id)
                .appendEncodedPath(TRAILER_PATH_VIDEO)
                .appendQueryParameter(API_KEY_TOKEN,API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static URL buildReviewUrl(String id) {

        //TODO added API Key to here
        final String API_KEY = BuildConfig.API_KEY;



        Uri builtUri = Uri.parse(MOVIESDB_BASE_URL).buildUpon()
                .appendEncodedPath(REVIEW_PATH)
                .appendEncodedPath(id)
                .appendEncodedPath(REVIEW)
                .appendQueryParameter(API_KEY_TOKEN,API_KEY)
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