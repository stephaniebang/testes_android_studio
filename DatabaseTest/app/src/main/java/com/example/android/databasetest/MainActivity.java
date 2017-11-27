package com.example.android.databasetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    private EditText item_name;
    private EditText item_quant;
    private EditText item_descr;
    private EditText item_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        item_name = (EditText) findViewById(R.id.item_name);
        item_quant = (EditText) findViewById(R.id.item_quantity);
        item_descr = (EditText) findViewById(R.id.item_description);
        item_price = (EditText) findViewById(R.id.item_price);
    }

    public void addItem(View view) {
        db.addData(item_name.getText().toString(),
                Integer.parseInt(item_quant.getText().toString()),
                item_descr.getText().toString(),
                Double.parseDouble(item_price.getText().toString()));
        item_name.setText("");
        item_quant.setText("");
        item_descr.setText("");
        item_price.setText("");
    }

    public void showOne(View view) {
        Intent intent = new Intent(this, ListOneItem.class);
        intent.putExtra("search", item_name.getText().toString());
        item_name.setText("");
        startActivity(intent);
    }

    public void showDatabase(View view) {
        Intent intent = new Intent(this, ListDatabase.class);
        startActivity(intent);
    }

    public void deleteItem(View view) {
        db.deleteName("Bolo");
    }
/*
    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }*/
}
