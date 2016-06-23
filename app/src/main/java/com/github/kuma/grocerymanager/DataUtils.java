package com.github.kuma.grocerymanager;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.View;
import com.github.kuma.data.db.CouchbaseHandler;
import com.github.kuma.db_object.Data;

import java.io.IOException;

/**
 * Utilities for working with the Data class
 */
public final class DataUtils
{
    private DataUtils() {}

    /**
     * Retrieve a Data object by its name.
     * @param name The name of the desired Data object.
     * @param handler Couchbase handler to use.
     * @return The object if it exists, null otherwise.
     * @throws CouchbaseLiteException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Data getByName(String name, CouchbaseHandler handler) throws CouchbaseLiteException, IOException,
        ClassNotFoundException
    {
        View dataView = AvailableViews.getDataView(handler);
        QueryEnumerator queryEnumerator = dataView.createQuery().run();
        while(queryEnumerator.hasNext())
        {
            Data data = (Data) queryEnumerator.next().getValue();
            if(data.getName().equals(name))
            {
                return data;
            }
        }
        return null;
    }
}
