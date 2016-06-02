package com.github.kuma.data.db;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;

import java.io.IOException;

public class GroceryDocument extends DbDocument
{
    private final static String DOCUMENT_ID = "groceries";
    private static DbDocument ourInstance;

    static
    {
        try
        {
            ourInstance = new GroceryDocument();
        }
        catch(Exception e)
        {
            Log.e(DbDocument.LOG_TAG, "GroceryDocument creation failed!");
        }
    }

    private GroceryDocument() throws CouchbaseLiteException, IOException
    {
        super(DOCUMENT_ID);
    }

    public static DbDocument getDocument()
    {
        return ourInstance;
    }
}
