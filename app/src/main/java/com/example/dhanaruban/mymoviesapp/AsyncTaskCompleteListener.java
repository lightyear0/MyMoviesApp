package com.example.dhanaruban.mymoviesapp;

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
