package com.example.android.databasetest;


import android.provider.BaseColumns;


public final class FeedReaderContract {
    private FeedReaderContract() {}

    // Definicao do conteudo da table
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "products";
        public static final String col_name = "name";
        public static final String col_quant = "quantity";
        public static final String col_descr = "description";
        public static final String col_price = "price";
    }
}
