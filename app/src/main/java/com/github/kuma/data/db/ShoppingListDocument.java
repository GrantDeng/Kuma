package com.github.kuma.data.db;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;

import java.io.IOException;

public class ShoppingListDocument extends DbDocument
{
    private final static String DOCUMENT_ID = "shopping_list";
    private static DbDocument ourInstance;

    static
    {
        try
        {
            ourInstance = new ShoppingListDocument();
        }
        catch(Exception e)
        {
            Log.e(DbDocument.LOG_TAG, "ShoppingListDocument creation failed!");
        }
    }

    private ShoppingListDocument() throws CouchbaseLiteException, IOException
    {
        super(DOCUMENT_ID);
    }

    public static DbDocument getDocument()
    {
        return ourInstance;
    }
}
