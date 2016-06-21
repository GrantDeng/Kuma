package com.github.kuma.data;

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
import java.util.Iterator;
import java.util.List;

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

        DbDocument dbDoc = new DbDocument(context, object.getId());
    }

    public static void saveShopListItemToDatabase(Savable object, Context context) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, CouchbaseLiteException, IOException
    {
        String objectId = object.getId();
        object.setId(objectId != null ? objectId : Savable.generateId());
        object.setType(object.determineTypeString());

        Shoppinglist shoplistobject = (Shoppinglist)object;

        DbDocument dbDoc = new DbDocument(context, object.getId());

        dbDoc.setProperties(object.getAdditionalProperties());
        dbDoc.setProperty("dataName",shoplistobject.getDataName());
        dbDoc.setProperty("bought",shoplistobject.isBought());
    }

    /**
     * Retrieve all documents stored inside database
     * @param context
     * @return list of document
     * @throws Exception
     */
    public static List<DbDocument> getAllDocuments(Context context) throws Exception    // don't know what exceptions to throw
    {
        CouchbaseHandler ch = new CouchbaseHandler(context);
        Database db =  ch.getDbInstance();
        List<DbDocument> list_of_doc = new ArrayList<DbDocument>();

        Query query = db.createAllDocumentsQuery();
        query.setAllDocsMode(Query.AllDocsMode.ALL_DOCS);

        QueryEnumerator result = query.run();

        for (Iterator<QueryRow> it = result; it.hasNext(); ) {

            QueryRow row = it.next();
            DbDocument dbDoc = new DbDocument(context,row.getDocumentId());
            if(dbDoc.getProperty(("dataName")) != null){
                list_of_doc.add(dbDoc);
            }
        }


       /* CouchbaseHandler ch = new CouchbaseHandler(context);
        Database db =  ch.getDbInstance();
        List<DbDocument> list_of_doc = new ArrayList<DbDocument>();

        View shoppingListView = AvailableViews.getShoppingListView(ch);
        Query query = shoppingListView.createQuery();
        QueryEnumerator result = query.run();

        for (Iterator<QueryRow> it = result; it.hasNext(); ) {
            QueryRow row = it.next();
            DbDocument dbDoc = new DbDocument(context,row.getDocumentId());
            list_of_doc.add(dbDoc);

        }
*/
        return list_of_doc;
    }
}
