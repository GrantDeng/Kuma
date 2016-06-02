package com.github.kuma.data.db;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.github.kuma.grocerymanager.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DBDocumentDictionary
{
    private Map<String, DbDocument> documentMap;
    private Context context;

    private static DBDocumentDictionary ourInstance;

    private DBDocumentDictionary(Context context)
    {
        this.context = context;
    }

    public static DBDocumentDictionary getDictionary(Context context)
    {
        if(ourInstance == null)
        {
            ourInstance = new DBDocumentDictionary(context);
            ourInstance.initDictionary();
        }
        return ourInstance;
    }

    private void initDictionary()
    {
        this.documentMap = new HashMap<String, DbDocument>();
        InputStream documentInputStream = this.context.getResources().openRawResource(R.raw.documents);
        Scanner scanner = new Scanner(new InputStreamReader(documentInputStream));
        while(scanner.hasNextLine())
        {
            String key = scanner.nextLine();
            documentMap.put(key, null);
        }
    }

    public DbDocument getDocument(String key) throws CouchbaseLiteException, IOException
    {
        if(! this.documentMap.containsKey(key))
        {
            // FIXME: throw an exception
            assert(false);
        }
        DbDocument document = this.documentMap.get(key);
        if(document == null)
        {
            document = new DbDocument(this.context, key);
            documentMap.put(key, document);
        }
        return document;
    }
}
