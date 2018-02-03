package com.example.dhanaruban.mymoviesapp;

import java.util.ArrayList;

/**
 * Created by thenu on 03-02-2018.
 */

public class TrailerDB {
    private int id;
    private ArrayList<Trailer> results;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public ArrayList<Trailer> getResults() {
        return results;
    }

    public void setResults(ArrayList<Trailer> results) {
        this.results = results;
    }
}
