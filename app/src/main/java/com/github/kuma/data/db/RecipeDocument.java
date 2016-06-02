package com.github.kuma.data.db;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;

import java.io.IOException;

public class RecipeDocument extends DbDocument
{
    private final static String DOCUMENT_ID = "recipes";
    private static DbDocument ourInstance;

    static
    {
        try
        {
            ourInstance = new RecipeDocument();
        }
        catch(Exception e)
        {
            Log.e(DbDocument.LOG_TAG, "RecipeDocument creation failed!");
        }
    }

    private RecipeDocument() throws CouchbaseLiteException, IOException
    {
        super(DOCUMENT_ID);
    }

    public static DbDocument getDocument()
    {
        return ourInstance;
    }
}
