package com.github.kuma.data.db;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;

import java.io.IOException;

public class MealPlanDocument extends DbDocument
{
    private final static String DOCUMENT_ID = "meal_plan";
    private static DbDocument ourInstance;

    static
    {
        try
        {
            ourInstance = new MealPlanDocument();
        }
        catch(Exception e)
        {
            Log.e(DbDocument.LOG_TAG, "MealPlanDocument creation failed!");
        }
    }

    private MealPlanDocument() throws CouchbaseLiteException, IOException
    {
        super(DOCUMENT_ID);
    }

    public static DbDocument getDocument()
    {
        return ourInstance;
    }
}
