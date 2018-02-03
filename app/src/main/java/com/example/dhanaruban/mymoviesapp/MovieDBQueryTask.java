package com.example.dhanaruban.mymoviesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import com.example.dhanaruban.mymoviesapp.utilities.MovieJsonUtils;
import com.example.dhanaruban.mymoviesapp.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

/**
 * Created by thenu on 17-01-2018.
 */

public class MovieDBQueryTask extends AsyncTask<URL, Void, String> {

    private Context context;
    private AsyncTaskCompleteListener<String> listener;

    public MovieDBQueryTask(Context context, AsyncTaskCompleteListener<String> listener){
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(URL... params) {
        URL searchUrl = params[0];
        String searchResults = null;
        try {
            searchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResults;
    }

    @Override
    protected void onPostExecute(String searchResults) {
        super.onPostExecute(searchResults);
        listener.onTaskComplete(searchResults);
    }
}
