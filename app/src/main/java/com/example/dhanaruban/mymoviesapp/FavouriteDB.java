package com.example.dhanaruban.mymoviesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dhanaruban.mymoviesapp.MovieContract.MovieEntry;

/**
 * DB Helper. currently just a single table
 */
public class FavouriteDB extends SQLiteOpenHelper{

    private  static  final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "movies";

    public FavouriteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieEntry.TABLE_NAME + " (" +
                MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MovieEntry.MOVIE_ID + " TEXT UNIQUE NOT NULL," +
                MovieEntry.MOVIE_BACKDROP_URI + " TEXT NOT NULL," +
                MovieEntry.MOVIE_TITLE + " TEXT NOT NULL," +
                MovieEntry.MOVIE_POSTER + " TEXT NOT NULL," +
                MovieEntry.MOVIE_OVERVIEW + " TEXT NOT NULL," +
                MovieEntry.MOVIE_VOTE_AVERAGE + " TEXT NOT NULL," +
                MovieEntry.MOVIE_RELEASE_DATE + " TEXT NOT NULL," +
                MovieEntry.MOVIE_REVIEWS + " TEXT NOT NULL," +
                MovieEntry.MOVIE_TRAILERS + " TEXT NOT NULL," +
                "UNIQUE (" + MovieEntry.MOVIE_ID +") ON CONFLICT IGNORE"+
                " );";

//        final String SQL_CREATE_REVIEW_TABLE = "CREATE TABLE " + ReviewsEntry.TABLE_NAME + " (" +
//                ReviewsEntry._ID + " INTEGER PRIMARY KEY," +
//                ReviewsEntry.REVIEW_AUTHOR + " TEXT NOT NULL," +
//                ReviewsEntry.REVIEW_CONTENT + " TEXT NOT NULL," +
//                " FOREIGN KEY (" + ReviewsEntry._ID  + ") REFERENCES " +
//                MovieEntry.TABLE_NAME + " (" + MovieEntry._ID + "), " +
//                " );";
//
//        final String SQL_CREATE_TRAILER_TABLE = "CREATE TABLE " + TrailersEntry.TABLE_NAME + " (" +
//                TrailersEntry._ID + " INTEGER PRIMARY KEY," +
//                TrailersEntry.VIDEO_TITLE + " TEXT NOT NULL," +
//                " FOREIGN KEY (" + TrailersEntry._ID  + ") REFERENCES " +
//                MovieEntry.TABLE_NAME + " (" + MovieEntry._ID + "), " +
//                " );";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
//        db.execSQL(SQL_CREATE_REVIEW_TABLE);
//        db.execSQL(SQL_CREATE_TRAILER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);

        onCreate(db);
    }
}
