package com.github.kuma.data.db;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DbDocument
{
    private Document document;
    private Database db;
    private static final String LOG_TAG = "document";

    public DbDocument(Context context, String documentId) throws CouchbaseLiteException, IOException
    {
        this.db = new CouchbaseHandler(context).getDbInstance();
        this.document = this.db.getDocument(documentId);
        this.document.putProperties(new HashMap<String, Object>());
    }

    public String getName()
    {
        return this.document.getId();
    }

    public Object getProperty(String key)
    {
        return this.document.getProperties().get(key);
    }

    public void setProperty(String key, Object value)
    {
        Map<String, Object> properties = this.document.getProperties();
        properties.put(key, value);
    }

    public void delete() throws CouchbaseLiteException
    {
        if(this.document.isDeleted())
        {
            return;
        }
        this.document.delete();
    }
}
