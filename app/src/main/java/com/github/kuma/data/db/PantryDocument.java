package com.github.kuma.data.db;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;

import java.io.IOException;

public class PantryDocument extends DbDocument
{
    private final static String DOCUMENT_ID = "pantry";
    private static DbDocument ourInstance;

    static
    {
        try
        {
            ourInstance = new PantryDocument();
        }
        catch(Exception e)
        {
            Log.e(DbDocument.LOG_TAG, "PantryDocument creation failed!");
        }
    }

    private PantryDocument() throws CouchbaseLiteException, IOException
    {
        super(DOCUMENT_ID);
    }

    public static DbDocument getDocument()
    {
        return ourInstance;
    }
}
