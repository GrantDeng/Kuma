package com.github.kuma.grocerymanager;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Mapper;
import com.couchbase.lite.View;
import com.github.kuma.data.db.CouchbaseHandler;
import com.github.kuma.data.db.ViewUtils;
import com.github.kuma.db_object.Data;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public final class AvailableViews
{
    private static final String VERSION_NUMBER = "1";

    private static View dataView;

    // FIXME: SHOULDN'T BE HERE!
    static Set<String> getFieldNamesForClass(Class<?> klass) throws ClassNotFoundException
    {
        Field[] fields = klass.getDeclaredFields();
        Set<String> fieldNames = new HashSet<String>();
        for(Field field: fields)
        {
            fieldNames.add(field.toString());
        }
        return fieldNames;
    }

    public static View getDataView(CouchbaseHandler handler) throws ClassNotFoundException, IOException,
        CouchbaseLiteException
    {
        if(AvailableViews.dataView == null)
        {
            String classString = Data.class.toString();
            AvailableViews.dataView = handler.getDbInstance().getView(classString);
            Mapper mapper = ViewUtils.dataTypeMapper(classString, AvailableViews.getFieldNamesForClass(Data.class));
            dataView.setMap(mapper, AvailableViews.VERSION_NUMBER);
        }
        return AvailableViews.dataView;
    }
}
