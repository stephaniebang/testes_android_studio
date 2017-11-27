package com.example.android.databasetest;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class mBaseAdapter extends BaseAdapter {
    private final List<Produtos> produtos;
    private final Activity act;

    public mBaseAdapter(List<Produtos> produtos, Activity act) {
        this.produtos = produtos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int i) {
        return produtos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.activity_list_database, parent, false);
        Produtos produto = produtos.get(i);

        return null;
    }
}
