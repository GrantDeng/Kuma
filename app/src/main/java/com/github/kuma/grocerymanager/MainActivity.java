package com.github.kuma.grocerymanager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.github.kuma.data.db.DBDocumentDictionary;
import com.github.kuma.data.db.DbDocument;

public class MainActivity extends ActionBarActivity
{
    // TEMPORARY - DO NOT DO THIS LONG TERM
    private final String[] docsNeeded = { "food_data", "pantry" };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TEMPORARY - DO NOT DO THIS LONG TERM
        DBDocumentDictionary dbDocumentDictionary = DBDocumentDictionary.getDictionary(this.getApplicationContext());
        for(String doc: docsNeeded)
        {
            try
            {
                DbDocument document = dbDocumentDictionary.getDocument(doc);
                System.err.println(document.getName());
            }
            catch(Exception e)
            {
                // FIXME: catch correct exception types
                e.printStackTrace();
            }
        }
    }
}
