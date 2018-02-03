package com.example.dhanaruban.mymoviesapp;

/**
 * Created by dhanaruban on 14/01/18.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.dhanaruban.mymoviesapp.utilities.MovieJsonUtils;
import com.example.dhanaruban.mymoviesapp.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.net.URL;


public class MovieDetailsActivity extends AppCompatActivity implements AsyncTaskCompleteListener<String> {
    /**
     * For logging purposes
     */
    private final String LOG_TAG = MovieDetailsActivity.class.getSimpleName();

    private static final String BASE_PATH = "http://image.tmdb.org/t/p/w185/";

    private Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        TextView tvOriginalTitle = findViewById(R.id.textview_original_title);
        ImageView ivPoster = findViewById(R.id.imageview_poster);
        TextView tvOverView = findViewById(R.id.textview_overview);
        TextView tvVoteAverage = findViewById(R.id.textview_vote_average);
        TextView tvReleaseDate = findViewById(R.id.textview_release_date);


        Intent intent = getIntent();
        movie = intent.getParcelableExtra(getString(R.string.parcel_movie));

        tvOriginalTitle.setText(movie.getOriginal_title());

        Picasso.with(this)
                .load(BASE_PATH + movie.getPoster_path())
                .into(ivPoster);

        String overView = movie.getOverview();
        if (overView == null) {
            tvOverView.setTypeface(null, Typeface.ITALIC);
            overView = getResources().getString(R.string.no_summary_found);
        }
        tvOverView.setText(overView);
        tvVoteAverage.setText( Double.toString(movie.getVote_average()));

        // First get the release date from the object - to be used if something goes wrong with
        // getting localized release date (catch).
        String releaseDate = movie.getRelease_date();
//        if(releaseDate != null) {
//            try {
//                releaseDate = DateTimeHelper.getLocalizedDate(this,
//                        releaseDate, movie.getDateFormat());
//            } catch (ParseException e) {
//                Log.e(LOG_TAG, "Error with parsing movie release date", e);
//            }
//        } else {
//            tvReleaseDate.setTypeface(null, Typeface.ITALIC);
//            releaseDate = getResources().getString(R.string.no_release_date_found);
//        }
        tvReleaseDate.setText(releaseDate);

        //vvTrailer.setVideoPath(BASE_PATH + "/movie/" + movie.getId() + "/videos");
        makeTrailerDBSearchQuery(movie.getId());

    }

    private void makeTrailerDBSearchQuery(int id) {

        URL TrailerDBSearchUrl = NetworkUtils.buildTrailerUrl(Integer.toString(id) );
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected) {
            //new MovieDBQueryTask().execute(MovieDBSearchUrl);
            new MovieDBQueryTask(this, this ).execute(TrailerDBSearchUrl);
        } else{
            Toast.makeText(this, R.string.NoNetwork, Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onTaskComplete(String searchResults) {
        //mLoadingIndicator.setVisibility(View.INVISIBLE);
        if (searchResults != null && !searchResults.equals("")) {


            try {
                trailer = MovieJsonUtils.getMovieContentValuesFromJson(searchResults);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            assert trailer != null;
            MoviesDBAdapter moviesDBAdapter = new MoviesDBAdapter(MovieDetailsActivity.this, trailer.getResults());

            // Get a reference to the ListView, and attach this adapter to it.
            ScrollView scrollView = findViewById(R.id.sv_trailer);
            LinearLayout ll;

            scrollView.setAd
            gridView.setAdapter(moviesDBAdapter);

        }
    }
}