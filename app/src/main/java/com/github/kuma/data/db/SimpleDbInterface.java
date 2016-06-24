package com.github.kuma.data.db;

import android.content.Context;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;
import com.couchbase.lite.View;
import com.github.kuma.data.db.CouchbaseHandler;
import com.github.kuma.data.db.DbDocument;
import com.github.kuma.db_object.Savable;
import com.github.kuma.db_object.Shoppinglist;
import com.github.kuma.grocerymanager.AvailableViews;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Simple database interface class.
 */
public final class SimpleDbInterface
{
    private SimpleDbInterface() {}

    /**
     * Save the given object to the database.
     *
     * @param object Object to save.
     * @param context Database context. Only pass application contexts!
     * @throws NoSuchMethodException If the object passed lacks a setType() function.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void saveToDatabase(Savable object, Context context) throws NoSuchMethodException,
        InvocationTargetException, IllegalAccessException, CouchbaseLiteException, IOException, NullDocumentException
    {
        String objectId = object.getId();
        object.setId(objectId != null ? objectId : Savable.generateId());
        object.setType(object.determineTypeString());
        TypeConnector.savable2DbDocument(context, object);
    }

    public static void saveShopListItemToDatabase(Savable object, Context context) throws NoSuchMethodException,
        InvocationTargetException, IllegalAccessException, CouchbaseLiteException, IOException, NullDocumentException
    {
        String objectId = object.getId();
        object.setId(objectId != null ? objectId : Savable.generateId());
        object.setType(object.determineTypeString());

        Shoppinglist shoplistobject = (Shoppinglist) object;

        DbDocument dbDoc = new DbDocument(context, object.getId());
        String objectType = object.determineTypeString();

        HashMap<String,Object> fields = new HashMap<String, Object>();
        fields.putAll(object.getAdditionalProperties());
        fields.put("dataName",shoplistobject.getDataName());
        fields.put("bought",shoplistobject.getBought());
        fields.put("type",objectType);
        fields.put("relatedDataId",shoplistobject.getRelatedDataId());

        dbDoc.setProperties(fields);
    }

    /**
     * Retrieve all shop list documents stored inside database
     *
     * @param context
     * @return list of document
     * @throws Exception
     */
    public static List<DbDocument> getAllShopListDocuments(Context context) throws Exception
    {
        CouchbaseHandler ch = new CouchbaseHandler(context);
        Database db = ch.getDbInstance();
        List<DbDocument> list_of_doc = new ArrayList<DbDocument>();

        Query query = db.createAllDocumentsQuery();
        query.setAllDocsMode(Query.AllDocsMode.ALL_DOCS);
        QueryEnumerator result = query.run();

        for (Iterator<QueryRow> it = result; it.hasNext(); )
        {
            QueryRow row = it.next();
            DbDocument dbDoc = new DbDocument(context, row.getDocumentId());

            String returnProperty = dbDoc.getDataType();

            if(returnProperty != null)
            {
                String objectType = returnProperty;
                if (objectType.contains("Shoppinglist"))
                {
                    list_of_doc.add(dbDoc);
                }
            }
        }
        return list_of_doc;
    }

    /**
     * Retrieve all grocery documents stored inside database
     *
     * @param context
     * @return list of document
     * @throws Exception
     */
    public static List<DbDocument> getAllGroceryDocuments(Context context) throws Exception    // don't know what exceptions to throw
    {
        CouchbaseHandler ch = new CouchbaseHandler(context);
        Database db = ch.getDbInstance();
        List<DbDocument> list_of_doc = new ArrayList<DbDocument>();

        Query query = db.createAllDocumentsQuery();
        query.setAllDocsMode(Query.AllDocsMode.ALL_DOCS);
        QueryEnumerator result = query.run();

        for (Iterator<QueryRow> it = result; it.hasNext(); )
        {
            QueryRow row = it.next();
            DbDocument dbDoc = new DbDocument(context, row.getDocumentId());

            String returnProperty = dbDoc.getDataType();
            if(returnProperty != null)
            {
                String objectType = returnProperty;
                if (objectType.contains("Grocery"))
                {
                    list_of_doc.add(dbDoc);
                    Log.e("getall",objectType);
                }
            }
        }
        return list_of_doc;
    }

    public static void deleteDB(Context context) throws Exception    // don't know what exceptions to throw
    {
        CouchbaseHandler ch = new CouchbaseHandler(context);
        Database db = ch.getDbInstance();
        List<DbDocument> list_of_doc = new ArrayList<DbDocument>();

        Query query = db.createAllDocumentsQuery();
        query.setAllDocsMode(Query.AllDocsMode.ALL_DOCS);
        QueryEnumerator result = query.run();

        for (Iterator<QueryRow> it = result; it.hasNext(); ) {
            QueryRow row = it.next();
            DbDocument dbDoc = new DbDocument(context, row.getDocumentId());
            dbDoc.delete();
        }
    }
}
