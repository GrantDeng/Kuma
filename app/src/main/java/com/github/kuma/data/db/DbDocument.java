package com.github.kuma.data.db;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * DbDocument wraps a Couchbase Document.
 */
public class DbDocument
{
    private Document document;
    private Database db;
    private static final String LOG_TAG = "document";

    /**
     * Constructor initializes a document and its keyset. If the document does not yet exist, it is created.
     * @param context Associated context (needed by the Couchbase handler). Only pass application contexts!
     * @param documentId Name of the document to open
     */
    public DbDocument(Context context, String documentId) throws CouchbaseLiteException, IOException
    {
        this.db = new CouchbaseHandler(context).getDbInstance();
        this.document = this.db.getDocument(documentId);
        this.document.putProperties(new HashMap<String, Object>());
    }

    /**
     * Returns the name of this document.
     * @return The name of this document.
     */
    public String getName()
    {
        return this.document.getId();
    }

    /**
     * Returns the property with the given key.
     * @param key The key to get the property for.
     * @return The property with the given key. Null if no such property exists.
     */
    public Object getProperty(String key)
    {
        return this.document.getProperties().get(key);
    }

    /**
     * Sets the property with the given key to the given value.
     * @param key The key to set the property value for.
     * @param value The value to set the property to.
     */
    public void setProperty(String key, Object value)
    {
        Map<String, Object> properties = this.document.getProperties();
        properties.put(key, value);
    }

    /**
     * Deletes this document if it has not been deleted yet.
     */
    public void delete() throws CouchbaseLiteException
    {
        if(this.document.isDeleted())
        {
            return;
        }
        this.document.delete();
    }
}
