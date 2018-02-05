package com.example.dhanaruban.mymoviesapp;

/**
 * Created by dhanaruban on 14/01/18.
 */

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
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


public class MovieDetailsActivity extends Fragment implements AsyncTaskCompleteListener<String> {
    /**
     * For logging purposes
     */
    private final String LOG_TAG = MovieDetailsActivity.class.getSimpleName();

    private static final String BASE_PATH = "http://image.tmdb.org/t/p/w185/";

    private Movie movie;

    private LayoutInflater mLayoutInflater;
    private View rootView;
    private View tvOriginalTitle;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        mLayoutInflater = inflater;

        Bundle arguments = getArguments();
        Intent intent = getActivity().getIntent();

        if(arguments != null || intent != null && intent.hasExtra("movies_details")){

            rootView = mLayoutInflater.inflate(R.layout.activity_movie_details, container, false);
            if (arguments != null) {
                movie = (Movie)getArguments().getParcelable("movies_details");
            }else{
                movie = (Movie)intent.getParcelableExtra("movies_details");
            }
            // display the main movie info
            DisplayInfo();


        }else{
            rootView = mLayoutInflater.inflate(R.layout.placeholder,
                    container, false);
        }

        return rootView;
    }

    private void DisplayInfo() {

        TextView tvOriginalTitle = (TextView)rootView.findViewById(R.id.textview_original_title);
        ImageView ivPoster = (ImageView)rootView.findViewById(R.id.imageview_poster);
        TextView tvOverView = (TextView)rootView.findViewById(R.id.textview_overview);
        ImageButton addToFav = (ImageButton) rootView.findViewById(R.id.add_to_fav_view);
        toggleFavorites();
        TextView tvVoteAverage = (TextView)rootView.findViewById(R.id.textview_vote_average);
        TextView tvReleaseDate = (TextView)rootView.findViewById(R.id.textview_release_date);
        addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inFavorites = checkFavorites();
                if (inFavorites) {
                    deleteFromFavorites();
                } else {
                    addToFavorites();
                }
                toggleFavorites();
            }
        });


        Intent intent =getActivity().getIntent();
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
        tvVoteAverage.setText(Double.toString(movie.getVote_average()));

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


    }

    private void toggleFavorites() {
        boolean inFavorites = checkFavorites();
        ImageButton addToFav = (ImageButton) rootView.findViewById(R.id.add_to_fav_view);

        if (inFavorites) {
            addToFav.setImageResource(R.drawable.favorite_added);
        } else {
            addToFav.setImageResource(R.drawable.favorite_removed);
        }
    }

//    private void makeTrailerDBSearchQuery(int id) {
//
//        URL TrailerDBSearchUrl = NetworkUtils.buildTrailerUrl(Integer.toString(id));
//        ConnectivityManager cm =
//                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        boolean isConnected = activeNetwork != null &&
//                activeNetwork.isConnectedOrConnecting();
//        if (isConnected) {
//            //new MovieDBQueryTask().execute(MovieDBSearchUrl);
//            new MovieDBQueryTask(this, this).execute(TrailerDBSearchUrl);
//        } else {
//            Toast.makeText(this, R.string.NoNetwork, Toast.LENGTH_SHORT).show();
//        }
//
//    }




   @Override
   public void onTaskComplete(String searchResults) {
//        //mLoadingIndicator.setVisibility(View.INVISIBLE);
//        if (searchResults != null && !searchResults.equals("")) {
//
//
//            try {
//                trailer = MovieJsonUtils.getMovieContentValuesFromJson(searchResults);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            assert trailer != null;
//            MoviesDBAdapter moviesDBAdapter = new MoviesDBAdapter(MovieDetailsActivity.this, trailer.getResults());
//
//            // Get a reference to the ListView, and attach this adapter to it.
//            ScrollView scrollView = findViewById(R.id.sv_trailer);
//            LinearLayout ll;
//
//            scrollView.setAd
//            gridView.setAdapter(moviesDBAdapter);
//
//        }
   }
    private void addToFavorites() {

        Uri uri = MovieContract.MovieEntry.CONTENT_URI;
        ContentResolver resolver = getActivity().getContentResolver();
        ContentValues values = new ContentValues();
        values.clear();

        values.put(MovieContract.MovieEntry.MOVIE_ID, movie.getId());
        values.put(MovieContract.MovieEntry.MOVIE_BACKDROP_URI, movie.getTitle());
        values.put(MovieContract.MovieEntry.MOVIE_TITLE, movie.getTitle());
        values.put(MovieContract.MovieEntry.MOVIE_POSTER, movie.getPoster_path());
        values.put(MovieContract.MovieEntry.MOVIE_OVERVIEW, movie.getOverview());
        values.put(MovieContract.MovieEntry.MOVIE_VOTE_AVERAGE, movie.getVote_average());
        values.put(MovieContract.MovieEntry.MOVIE_RELEASE_DATE, movie.getRelease_date());



        Uri check = resolver.insert(uri, values);
    }
    private void deleteFromFavorites() {

        Uri uri = MovieContract.MovieEntry.CONTENT_URI;
        ContentResolver resolver = getActivity().getContentResolver();

        long noDeleted = resolver.delete(uri,
                MovieContract.MovieEntry.MOVIE_ID + " = ? ",
                new String[]{ movie.getId() + "" });

    }
    private boolean checkFavorites() {

        Uri uri = MovieContract.MovieEntry.buildMovieUri(movie.getId());
        ContentResolver resolver = getActivity().getContentResolver();
        Cursor cursor = null;

        try {

            cursor = resolver.query(uri, null, null, null, null);
            if (cursor.moveToFirst())
                return true;

        } finally {

            if(cursor != null)
                cursor.close();

        }

        return false;
    }

}