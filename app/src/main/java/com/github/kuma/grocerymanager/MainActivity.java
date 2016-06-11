package com.github.kuma.grocerymanager;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryRow;
import com.couchbase.lite.View;
import com.github.kuma.data.db.DbDocument;
import com.github.kuma.db_object.Data;

import java.util.Iterator;

public class MainActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dummy data
        DbDocument[] dummyDocuments = new DbDocument[2];
        String[] keys = { "jam", "red grapes" };
        for(int i = 0; i < 2; i++)
        {
            try
            {
                dummyDocuments[i] = new DbDocument(this.getApplicationContext(), keys[i]);
                dummyDocuments[i].setProperty("type", Data.class.toString());
                dummyDocuments[i].setProperty("name", keys[i]);
            }
            catch(Exception e)
            {
                System.err.println("NOT GOOD");
                e.printStackTrace();
            }
        }

        // view test
        try
        {
            View foodDataView = AvailableViews.getDataView(dummyDocuments[0].getHandler());
            Query totalQuery = foodDataView.createQuery();
            try
            {
                Iterator<QueryRow> queryData = totalQuery.run();
                while(queryData.hasNext())
                {
                    QueryRow row = queryData.next();
                    System.err.println("Document: " + row.getDocumentId() + ", Key: " + row.getKey() + ", Value: " + row.getValue());
                    System.err.println("=======");
                }
            } catch(CouchbaseLiteException cle)
            {
                System.err.println("Very inexcusably bad");
                cle.printStackTrace();
            }
        }
        catch(Exception e)
        {
            System.err.println("Good grief.");
            e.printStackTrace();
        }
    }
}
