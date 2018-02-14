package com.example.dhanaruban.mymoviesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by thenu on 17-01-2018.
 */

public interface AsyncTaskCompleteListener<T>
{


    /**
     * Invoked when the AsyncTask has completed its execution.
     * @param result The resulting object from the AsyncTask.
     */
    public void onTaskComplete(T result);
}
