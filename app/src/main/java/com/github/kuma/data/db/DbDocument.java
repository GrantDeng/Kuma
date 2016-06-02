package com.github.kuma.data.db;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;

import java.io.IOException;

public class DbDocument
{
    private Document document;
    private Database db;
    private static final String LOG_TAG = "document";

    public DbDocument(Context context, String documentId) throws CouchbaseLiteException, IOException
    {
        this.db = new CouchbaseHandler(context).getDbInstance();
        this.document = this.db.getDocument(documentId);
    }

    public String getName()
    {
        return this.document.getId();
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
