package com.github.kuma.data.db;

import com.couchbase.lite.CouchbaseLiteException;

import java.io.IOException;

public class FoodDataDocument extends DbDocument
{
    private final static String DOCUMENT_ID = "food_data";

    private FoodDataDocument() throws CouchbaseLiteException, IOException
    {
        super(DOCUMENT_ID);
    }
}
