package com.github.kuma.grocerymanager;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryRow;
import com.couchbase.lite.View;
import com.github.kuma.data.db.DbDocument;
import com.github.kuma.data.db.ViewUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dummy data
        DbDocument[] dummyDocuments = new DbDocument[5];
        String[] keys = { "pizza", "apple", "cottage cheese", "peanut butter", "whole wheat bread" };
        for(int i = 0; i < 5; i++)
        {
            try
            {
                dummyDocuments[i] = new DbDocument(this.getApplicationContext(), keys[i]);
                dummyDocuments[i].setProperty("data_type", "food_data");
                dummyDocuments[i].setProperty("name", keys[i]);
                System.err.println("name: " + dummyDocuments[i].getProperty("name") +
                    ", data type: " + dummyDocuments[i].getProperty("data_type"));
            }
            catch(Exception e)
            {
                System.err.println("NOT GOOD");
                e.printStackTrace();
            }
        }

        // view test
        Database db = dummyDocuments[0].getDb();
        View foodDataView = db.getView("food_data");
        HashSet<String> keys2 = new HashSet<String>();
        keys2.add("name");
        foodDataView.setMap(ViewUtils.dataTypeMapper("food_data", keys2), "1");
        Query totalQuery = foodDataView.createQuery();
        try
        {
            Iterator<QueryRow> queryData = totalQuery.run();
            while(queryData.hasNext())
            {
                Map<String, Object> data = queryData.next().asJSONDictionary();
                for(String key: data.keySet())
                {
                    System.err.println("Key: " + key + ", value: " + data.get(key));
                }
            }
        }
        catch(CouchbaseLiteException cle)
        {
            System.err.println("Very inexcusably bad");
        }
    }
}
