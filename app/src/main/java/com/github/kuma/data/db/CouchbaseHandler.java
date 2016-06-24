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
    private static CouchbaseHandler handler;
    private static Manager manager;
    private static Database db;
    private static Context context;

    private static final String DB_NAME = "kuma"; // must be lowercase to prevent exception
    private static final String LOG_TAG = "couchbase";

    private CouchbaseHandler() {}

    /**
     * Singleton  starts up logging for the CouchbaseHandler
     * @param context The context associated with Couchbase. Only pass application contexts!
     */
    public static CouchbaseHandler getCouchbaseHandler(Context context)
    {
        // FIXME: this should go into a specific configuration handler
        final int LOG_LEVEL = Log.DEBUG;
        Log.enableLogging(LOG_TAG, LOG_LEVEL);
        if(CouchbaseHandler.context == null)
        {
            CouchbaseHandler.context = context;
        }
        if(CouchbaseHandler.handler == null)
        {
            CouchbaseHandler.handler = new CouchbaseHandler();
        }
        return CouchbaseHandler.handler;
    }

    /**
     * Get singleton access to the database.
     * @return The database.
     * @throws CouchbaseLiteException
     * @throws IOException
     */
    public Database getDbInstance() throws CouchbaseLiteException, IOException
    {
        if(CouchbaseHandler.db == null)
        {
            if(CouchbaseHandler.manager == null)
            {
                CouchbaseHandler.manager = getManagerInstance();
            }

            CouchbaseHandler.db = CouchbaseHandler.manager.getDatabase(CouchbaseHandler.DB_NAME);
        }

        return CouchbaseHandler.db;
    }

    /**
     * Returns singleton access to the database manager.
     * @return the database manager
     * @throws IOException
     */
    public Manager getManagerInstance() throws IOException
    {
        if(CouchbaseHandler.manager == null)
        {
            CouchbaseHandler.manager = new Manager(
                new AndroidContext(CouchbaseHandler.context),
                Manager.DEFAULT_OPTIONS
            );
        }

        return CouchbaseHandler.manager;
    }
}
