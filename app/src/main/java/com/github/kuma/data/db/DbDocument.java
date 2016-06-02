package com.github.kuma.data.db;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;

import java.io.IOException;

public abstract class DbDocument<T>
{
    protected Document document;
    protected Database db;
    protected static final String LOG_TAG = "document";

    protected DbDocument(String documentId) throws CouchbaseLiteException, IOException
    {
        this.db = CouchbaseHandler.getInstance().getDbInstance();
        this.document = this.db.getDocument(documentId);
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
