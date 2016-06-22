package com.github.kuma.data;

import android.content.Context;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;
import com.github.kuma.data.db.CouchbaseHandler;
import com.github.kuma.data.db.DbDocument;
import com.github.kuma.db_object.Savable;
import com.github.kuma.db_object.Shoppinglist;

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
     *
     * @param object  Object to save.
     * @param context Database context. Only pass application contexts!
     * @throws NoSuchMethodException     If the object passed lacks a setType() function.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void saveToDatabase(Savable object, Context context) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, CouchbaseLiteException, IOException {
        String objectId = object.getId();
        object.setId(objectId != null ? objectId : Savable.generateId());
        object.setType(object.determineTypeString());

        new DbDocument(context, object.getId());
    }

    public static void saveShopListItemToDatabase(Savable object, Context context) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, CouchbaseLiteException, IOException {
        String objectId = object.getId();
        object.setId(objectId != null ? objectId : Savable.generateId());
        object.setType(object.determineTypeString());

        Shoppinglist shoplistobject = (Shoppinglist) object;

        DbDocument dbDoc = new DbDocument(context, object.getId());
        String objectType = object.determineTypeString();

        dbDoc.setProperties(object.getAdditionalProperties());
        dbDoc.setProperty("dataName", shoplistobject.getDataName());
        dbDoc.setProperty("bought", shoplistobject.isBought());
        dbDoc.setProperty("ObjectType",objectType);
    }

    /**
     * Retrieve all shop list documents stored inside database
     *
     * @param context
     * @return list of document
     * @throws Exception
     */
    public static List<DbDocument> getAllShopListDocuments(Context context) throws Exception    // don't know what exceptions to throw
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

            Object returnProperty = dbDoc.getProperty("ObjectType");
            if(returnProperty != null)
            {
                String objectType = returnProperty.toString();
                if (objectType.contains("Shoppinglist")) {
                    list_of_doc.add(dbDoc);
                }
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

        for (Iterator<QueryRow> it = result; it.hasNext(); ) {
            QueryRow row = it.next();
            DbDocument dbDoc = new DbDocument(context, row.getDocumentId());
            String objectType = dbDoc.getProperty("ObjectType").toString();

            if (objectType.contains("Grocery")) {
                list_of_doc.add(dbDoc);
            }
        }
        return list_of_doc;
    }
}
