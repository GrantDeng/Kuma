package com.github.kuma.grocerymanager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.github.kuma.data.db.CouchbaseHandler;
import com.github.kuma.data.db.DbDocument;

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
                dummyDocuments[i].setProperty("a", "b");
                System.err.println(dummyDocuments[i].getProperty("a"));
            }
            catch(Exception e)
            {
                System.err.println("NOT GOOD");
                e.printStackTrace();
            }
        }

    }
}
