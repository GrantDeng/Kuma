package com.github.kuma.data.db;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;

import java.io.IOException;

public class FoodDataDocument extends DbDocument
{
    private final static String DOCUMENT_ID = "food_data";
    private static DbDocument ourInstance;

    static
    {
        try
        {
            ourInstance = new FoodDataDocument();
        }
        catch(Exception e)
        {
            Log.e(DbDocument.LOG_TAG, "FoodDataDocument creation failed!");
        }
    }

    private FoodDataDocument() throws CouchbaseLiteException, IOException
    {
        super(DOCUMENT_ID);
    }

    public static DbDocument getDocument()
    {
        return ourInstance;
    }
}
