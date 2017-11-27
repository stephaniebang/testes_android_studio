package com.example.android.databasetest;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListOneItem extends AppCompatActivity {
    private ListView listView;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_one_item);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        listView = (ListView) findViewById(R.id.list_one_item);
        db = new DatabaseHelper(this);

        showItem(search);
    }

    void showItem(String s) {
        Cursor data = db.getAllData();
        Log.d("showItem: ", "searching for '" + s + "'");
        ArrayList<String> list = new ArrayList<>();
        List<Produtos> produtos = new ArrayList<>();

        while (data.moveToNext()) {
            if (data.getString(data.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.col_name)).equals(s)) {
                list.add(data.getString(data.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.col_name)));
                list.add(data.getString(data.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.col_quant)));
                list.add(data.getString(data.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.col_descr)));
                list.add(data.getString(data.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.col_price)));

                produtos.add(new Produtos(
                        data.getString(data.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.col_name)),
                        data.getInt(data.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.col_quant)),
                        data.getString(data.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.col_descr)),
                        data.getDouble(data.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.col_price))));
            }
        }

        ArrayAdapter<Produtos> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produtos);

        listView.setAdapter(adapter);
    }
}
