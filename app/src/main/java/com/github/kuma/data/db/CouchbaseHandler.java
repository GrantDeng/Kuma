package com.github.kuma.data.db;

import android.app.Activity;
import android.content.Context;

import com.couchbase.lite.Attachment;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.db;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.Revision;
import com.couchbase.lite.SavedRevision;
import com.couchbase.lite.UnsavedRevision;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * CouchbaseHandler provides singleton access to a Couchbase Database and Manager.
 * Thanks to http://developer.couchbase.com/documentation/mobile/1.2/develop/training/build-first-android-app/starter-code-android/index.html
 */
public class CouchbaseHandler
{
    private Manager manager;
    private Database db;
    private Context context;

    private static final String DB_NAME = "kuma"; // must be lowercase to prevent exception
    private static final String LOG_TAG = "couchbase";

    /**
     * Initialize the CouchbaseHandler for a given Android Activity.
     * @param context Context to use to start up the manager.
     * @param logLevel Logger level for the databases.
     */
    public CouchbaseHandler(Context context, int logLevel)
    {
        this.context = context;
        Log.enableLogging(this.LOG_TAG, logLevel);
    }

    /**
     * Get singleton access to the database.
     * @return The database.
     * @throws CouchbaseLiteException
     * @throws IOException
     */
    public Database getdbInstance() throws CouchbaseLiteException, IOException
    {
        if(this.db == null)
        {
            if(this.manager == null)
            {
                this.manager = this.getManagerInstance();
            }
            this.db = manager.getDatabase(DB_NAME);
        }
        return this.db;
    }

    /**
     * Returns singleton access to the database manager.
     * @return the database manager
     * @throws IOException
     */
    public Manager getManagerInstance() throws IOException
    {
        if(this.manager == null)
        {
            this.manager = new Manager(new AndroidContext(this.activity), Manager.DEFAULT_OPTIONS);
        }
        return this.manager;
    }
}
