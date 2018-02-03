package com.example.dhanaruban.mymoviesapp.utilities;

import com.example.dhanaruban.mymoviesapp.Movie;
import com.example.dhanaruban.mymoviesapp.MoviesDB;
import com.example.dhanaruban.mymoviesapp.Review;
import com.example.dhanaruban.mymoviesapp.ReviewDB;
import com.example.dhanaruban.mymoviesapp.Trailer;
import com.example.dhanaruban.mymoviesapp.TrailerDB;

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

    private static final String MOVIE_ID = "id";
    private static final String TRAILER_RESULTS = "results";
    private static final String TRAILER_ID = "id";
    private static final String ISO_639_1 = "iso_639_1";
    private static final String ISO_3166_1 = "iso_3166_1";
    private static final String KEY = "key";
    private static final String SITE= "site";
    private static final String SIZE = "size";
    private static final String TYPE = "type";

    private static final String MOVIE_REVIEW__ID = "id";
    private static final String REVIEW_PAGE = "page";
    private static final String REVIEW_RESULTS = "results";
    private static final String REVIEW_TOTAL_PAGES = "total_pages";
    private static final String REVIEW_TOTAL_RESULTS = "total_results";
    private static final String REVIEW_ID = "id";
    private static final String AUTHOR = "author";
    private static final String CONTENT = "content";
    private static final String URL = "url";





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
    public static TrailerDB getTrailerContentValuesFromJson(String trailerJsonStr)
            throws JSONException {

        JSONObject trailerJson = new JSONObject(trailerJsonStr);

        /* Is there an error? */
        if (trailerJson.has(OWM_MESSAGE_CODE)) {
            int errorCode = trailerJson.getInt(OWM_MESSAGE_CODE);

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


        JSONArray jsonTrailer = trailerJson.getJSONArray(RESULTS);

        TrailerDB trailerDB = new TrailerDB();

        trailerDB.setId(trailerJson.getInt(MOVIE_ID));



        ArrayList<Trailer> trailer = new ArrayList<>();


        for (int i = 0; i < jsonTrailer.length(); i++) {


            JSONObject video = jsonTrailer.getJSONObject(i);

            Trailer topTrailers = new Trailer();



            topTrailers.setId(video.getString(TRAILER_ID));
            topTrailers.setiso_639_1(video.getString(ISO_639_1));
            topTrailers.setiso_3166_1(video.getString(ISO_3166_1));
            topTrailers.setKey(video.getString(KEY));
            topTrailers.setSite(video.getString(SITE));
            topTrailers.setSize(video.getInt(SIZE));
            topTrailers.setType(video.getString(TYPE));



            trailer.add(topTrailers);
        }

        trailerDB.setResults(trailer);

        return trailerDB;
    }
    public static ReviewDB getReviewContentValuesFromJson(String reviewJsonStr)
            throws JSONException {

        JSONObject reviewJson = new JSONObject(reviewJsonStr);

        /* Is there an error? */
        if (reviewJson.has(OWM_MESSAGE_CODE)) {
            int errorCode = reviewJson.getInt(OWM_MESSAGE_CODE);

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


        JSONArray jsonReview = reviewJson.getJSONArray(RESULTS);

        ReviewDB reviewDB = new ReviewDB();

        reviewDB.setId(reviewJson.getInt(MOVIE_REVIEW__ID));
        reviewDB.setPage(reviewJson.getInt(REVIEW_PAGE));
        reviewDB.setTotalPages(reviewJson.getInt(REVIEW_TOTAL_PAGES));
        reviewDB.setTotalResults(reviewJson.getInt(REVIEW_TOTAL_RESULTS));



        ArrayList<Review> review = new ArrayList<>();


        for (int i = 0; i < jsonReview.length(); i++) {


            JSONObject comment = jsonReview.getJSONObject(i);

            Review topReviews = new Review();



            topReviews.setId(comment.getString(REVIEW_ID));
            topReviews.setAuthor(comment.getString(AUTHOR));
            topReviews.setContent(comment.getString(CONTENT));
            topReviews.setUrl(comment.getString(URL));



            review.add(topReviews);
        }

        reviewDB.setResults(review);

        return reviewDB;
    }
}
