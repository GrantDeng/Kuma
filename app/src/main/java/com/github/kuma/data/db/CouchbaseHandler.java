package com.github.kuma.data.db;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.util.Log;

import java.io.IOException;

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
     * Constructor starts up logging for the CouchbaseHandler
     * @param context The context associated with Couchbase. Only pass application contexts!
     */
    public CouchbaseHandler(Context context)
    {
        // FIXME: this should go into a specific configuration handler
        final int LOG_LEVEL = Log.DEBUG;
        Log.enableLogging(LOG_TAG, LOG_LEVEL);
        this.context = context;
    }

    /**
     * Get singleton access to the database.
     * @return The database.
     * @throws CouchbaseLiteException
     * @throws IOException
     */
    public Database getDbInstance() throws CouchbaseLiteException, IOException
    {
        if(this.db == null)
        {
            if(this.manager == null)
            {
                this.manager = getManagerInstance();
            }

            this.db = this.manager.getDatabase(this.DB_NAME);
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
            this.manager = new Manager(new AndroidContext(this.context), Manager.DEFAULT_OPTIONS);
        }

        return this.manager;
    }
}
