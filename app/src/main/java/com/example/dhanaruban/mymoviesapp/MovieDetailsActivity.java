package com.example.dhanaruban.mymoviesapp;

/**
 * Created by dhanaruban on 14/01/18.
 */

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;




public class MovieDetailsActivity extends AppCompatActivity {
    /**
     * For logging purposes
     */
    private final String LOG_TAG = MovieDetailsActivity.class.getSimpleName();

    private static final String BASE_PATH = "http://image.tmdb.org/t/p/w185/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        TextView tvOriginalTitle = (TextView) findViewById(R.id.textview_original_title);
        ImageView ivPoster = (ImageView) findViewById(R.id.imageview_poster);
        TextView tvOverView = (TextView) findViewById(R.id.textview_overview);
        TextView tvVoteAverage = (TextView) findViewById(R.id.textview_vote_average);
        TextView tvReleaseDate = (TextView) findViewById(R.id.textview_release_date);

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra(getString(R.string.parcel_movie));

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
    }
}