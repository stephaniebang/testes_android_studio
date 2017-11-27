package com.example.android.databasetest;


import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ListDatabase extends AppCompatActivity {
    DatabaseHelper db;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_database);

        listView = (ListView) findViewById(R.id.list_items);
        db = new DatabaseHelper(this);

        showItem();
    }

    public void showItem() {
        Cursor data = db.getAllData();
        ArrayList<String> list = new ArrayList<>();
        List<Produtos> produtos = new ArrayList<>();

        while (data.moveToNext()) {
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

        ArrayAdapter<Produtos> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produtos);


/*
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                data,
                new String[] {FeedReaderContract.FeedEntry.col_name, FeedReaderContract.FeedEntry.col_price},
                new int[] {android.R.id.text1, android.R.id.text2});

                /*new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                list
        ) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) findViewById(android.R.id.text1);
                TextView text2 = (TextView) findViewById(android.R.id.text2);

                text1.setText();

                return view;
            }
        };*/

        listView.setAdapter(adaptador);
    }
}