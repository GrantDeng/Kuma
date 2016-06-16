package com.github.kuma.data;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.github.kuma.data.db.CouchbaseHandler;
import com.github.kuma.data.db.DbDocument;
import com.github.kuma.db_object.Savable;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Simple utility class to handle various database accesses.
 */
public final class DbUtils
{
    private DbUtils() {}

    /**
     * Save the given object to the database.
     * @param object Object to save.
     * @param context Database context. Only pass application contexts!
     * @throws NoSuchMethodException If the object passed lacks a setType() function.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void saveToDatabase(Savable object, Context context) throws NoSuchMethodException,
        InvocationTargetException, IllegalAccessException, CouchbaseLiteException, IOException
    {
        String objectId = object.getId();
        object.setId(objectId != null ? objectId : Savable.generateId());
        object.setType(object.determineTypeString());
        new DbDocument(context, object.getId());
    }
}
