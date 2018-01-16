package com.example.dhanaruban.mymoviesapp.utilities;

import com.example.dhanaruban.mymoviesapp.Movie;
import com.example.dhanaruban.mymoviesapp.MoviesDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by dhanaruban on 09/01/18.
 */

public final class MovieJsonUtils {

    /* Location information */
    private static final String PAGE = "page";
    private static final String TOTAL_RESULTS = "total_results";
    private static final String TOTAL_PAGES = "total_pages";
    private static final String RESULTS = "results";

    private static final String VOTE_COUNT = "vote_count";
    private static final String ID = "id";
    private static final String VIDEO = "video";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String TITLE = "title";
    private static final String POPULARITY = "popularity";
    private static final String POSTER_PATH = "poster_path";
    private static final String ORIGINAL_LANGUAGE = "original_language";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String GENRE_IDS = "genre_ids";
    private static final String BACKDROP_PATH = "backdrop_path";
    private static final String ADULT = "adult";
    private static final String OVERVIEW = "overview";
    private static final String RELEASE_DATE = "release_date";

    private static final String OWM_MESSAGE_CODE = "cod";

    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the weather over various days from the forecast.
     * <p/>
     * Later on, we'll be parsing the JSON into structured data within the
     * getFullWeatherDataFromJson function, leveraging the data we have stored in the JSON. For
     * now, we just convert the JSON into human-readable strings.
     *
     * @param forecastJsonStr JSON response from server
     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static MoviesDB getMovieContentValuesFromJson( String forecastJsonStr)
            throws JSONException {

        JSONObject forecastJson = new JSONObject(forecastJsonStr);

        /* Is there an error? */
        if (forecastJson.has(OWM_MESSAGE_CODE)) {
            int errorCode = forecastJson.getInt(OWM_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    /* Location invalid */
                    return null;
                default:
                    /* Server probably down */
                    return null;
            }
        }


        JSONArray jsonMovies = forecastJson.getJSONArray(RESULTS);

        MoviesDB moviesDB = new MoviesDB();

        moviesDB.setPage(forecastJson.getInt(PAGE));
        moviesDB.setTotal_pages(forecastJson.getInt(TOTAL_PAGES));
        moviesDB.setTotal_results(forecastJson.getInt(TOTAL_RESULTS));

        ArrayList<Movie> movies = new ArrayList<>();


        for (int i = 0; i < jsonMovies.length(); i++) {


            JSONObject movie = jsonMovies.getJSONObject(i);

            Movie topMovie = new Movie();


            topMovie.setVote_count(movie.getInt(VOTE_COUNT));
            topMovie.setId(movie.getInt(ID));
            //topMovie.setVideo(movie.getInt(VIDEO));
            topMovie.setVote_average(movie.getDouble(VOTE_AVERAGE));
            topMovie.setTitle(movie.getString(TITLE));
            topMovie.setPopularity(movie.getDouble(POPULARITY));
            topMovie.setPoster_path(movie.getString(POSTER_PATH));
            topMovie.setOriginal_language(movie.getString(ORIGINAL_LANGUAGE));
            topMovie.setOriginal_title(movie.getString(ORIGINAL_TITLE));
            topMovie.setBackdrop_path(movie.getString(BACKDROP_PATH));
            topMovie.setAdult(movie.getBoolean(ADULT));
            topMovie.setOverview(movie.getString(OVERVIEW));
            topMovie.setRelease_date(movie.getString(RELEASE_DATE));


            movies.add(topMovie);
        }

        moviesDB.setResults(movies);

        return moviesDB;
    }
}
