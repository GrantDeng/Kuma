package com.github.kuma.data.db;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.UnsavedRevision;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * DbDocument wraps a Couchbase Document.
 */
public class DbDocument
{
    private Document document;
    private CouchbaseHandler handler;
    private static final String LOG_TAG = "document";

    /**
     * Constructor initializes a document and its keyset. If the document does not yet exist, it is created.
     * @param context Associated context (needed by the Couchbase handler). Only pass application contexts!
     * @param documentId Name of the document to open
     */
    public DbDocument(Context context, String documentId) throws CouchbaseLiteException, IOException
    {
        this.handler = new CouchbaseHandler(context);
        this.document = this.handler.getDbInstance().getDocument(documentId);

        if(this.document.getCurrentRevision() == null)
        {
            this.document.putProperties(new HashMap<String, Object>());
        }
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
        return this.document.getUserProperties().get(key);
    }

    /**
     * Sets the property with the given key to the given value.
     * Don't use this method if you have multiple properties to add; use setProperties() instead.
     * @param key The key to set the property value for.
     * @param value The value to set the property to.
     */
    public void setProperty(final String key, final Object value) throws CouchbaseLiteException
    {
        Map<String, Object> singleMap = new HashMap<String, Object>();
        singleMap.put(key, value);
        this.setProperties(singleMap);
    }

    /**
     * Sets the given properties.
     * @param newProperties Properties to add.
     * @throws CouchbaseLiteException
     */
    public void setProperties(final Map<String, Object> newProperties) throws CouchbaseLiteException
    {
        this.document.update(new Document.DocumentUpdater()
        {
            @Override
            public boolean update(UnsavedRevision newRevision)
            {
                Map<String, Object> properties = newRevision.getUserProperties();
                properties.putAll(newProperties);
                newRevision.setUserProperties(properties);
                return true;
            }
        });
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

    /**
     * Return the handler for the database from which this document originated.
     * @return The Couchbase handler for the database from which this document originated.
     */
    public CouchbaseHandler getHandler()
    {
        return this.handler;
    }

    /**
     * Return the data type of this document.
     * @return The data type of this document, or null if it is not defined.
     */
    public String getDataType()
    {
        return DbDocument.getDataType(this);
    }

    /**
     * Return the data type of a given document.
     * @return The data type of the given document, or null if it is not defined.
     */
    public static String getDataType(DbDocument document)
    {
        return (String) document.getProperty("type");
    }

    /**
     * Return the data type of a given document.
     * @return The data type of the given document, or null if it is not defined.
     */
    public static String getDataType(Document document)
    {
        return (String) document.getProperty("type");
    }
}
