package com.example.android.databasetest;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "FeedReader.db";

    private static final String SQL_CREATES_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY, " +
                    FeedReaderContract.FeedEntry.col_name + " TEXT, " +
                    FeedReaderContract.FeedEntry.col_quant + " INTEGER, " +
                    FeedReaderContract.FeedEntry.col_descr + " TEXT, " +
                    FeedReaderContract.FeedEntry.col_price + " DOUBLE)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATES_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void addData(String name, int quant, String descr, double
            price) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.col_name, name);
        values.put(FeedReaderContract.FeedEntry.col_quant, quant);
        values.put(FeedReaderContract.FeedEntry.col_descr, descr);
        values.put(FeedReaderContract.FeedEntry.col_price, price);

        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);

        Log.d("addData: ", "'" + name + "'");
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor readData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.col_name,
                FeedReaderContract.FeedEntry.col_quant,
                FeedReaderContract.FeedEntry.col_descr,
                FeedReaderContract.FeedEntry.col_price
        };

        String selection = FeedReaderContract.FeedEntry.col_name + " = ?";
        String[] selectionArgs = { "name" };

        String sortOrder = FeedReaderContract.FeedEntry.col_price + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        return cursor;
    }

    public Cursor getData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + FeedReaderContract.FeedEntry.col_price + " FROM " +
                FeedReaderContract.FeedEntry.TABLE_NAME + " WHERE " +
                FeedReaderContract.FeedEntry.col_name + " = '" + name + "'";

        Cursor data = db.rawQuery(query, null);

        return data;
    }


    public void deleteName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + FeedReaderContract.FeedEntry.TABLE_NAME + " WHERE " +
                FeedReaderContract.FeedEntry.col_name + " = '" + name + "'";

        db.execSQL(query);
    }
}
