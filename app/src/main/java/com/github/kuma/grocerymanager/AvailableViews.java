package com.github.kuma.grocerymanager;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.View;
import com.github.kuma.data.db.CouchbaseHandler;
import com.github.kuma.data.db.ViewUtils;
import com.github.kuma.db_object.Data;
import com.github.kuma.db_object.Grocery;
import com.github.kuma.db_object.Mealplan;
import com.github.kuma.db_object.Recipe;
import com.github.kuma.db_object.Shoppinglist;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class AvailableViews
{
    private static final String VERSION_NUMBER = "1";

    private static Map<Class<?>, View> viewMap = new HashMap<Class<?>, View>();

    private static View getViewForClass(CouchbaseHandler handler, Class<?> klass) throws ClassNotFoundException,
        IOException, CouchbaseLiteException
    {
        View view = AvailableViews.viewMap.get(klass);
        if(view == null)
        {
            String classString = klass.toString();
            view = handler.getDbInstance().getView(classString);
            view.setDocumentType(classString);
            view.setMap(
                ViewUtils.typeMapper(klass),
                AvailableViews.VERSION_NUMBER
            );
            AvailableViews.viewMap.put(klass, view);
        }
        return view;
    }

    public static View getDataView(CouchbaseHandler handler) throws ClassNotFoundException, IOException,
        CouchbaseLiteException
    {
        return AvailableViews.getViewForClass(handler, Data.class);
    }

    public static View getGroceryView(CouchbaseHandler handler) throws ClassNotFoundException, IOException,
        CouchbaseLiteException
    {
        return AvailableViews.getViewForClass(handler, Grocery.class);
    }

    public static View getMealPlanView(CouchbaseHandler handler) throws ClassNotFoundException, IOException,
        CouchbaseLiteException
    {
        return AvailableViews.getViewForClass(handler, Mealplan.class);
    }

    public static View getRecipeView(CouchbaseHandler handler) throws ClassNotFoundException, IOException,
        CouchbaseLiteException
    {
        return AvailableViews.getViewForClass(handler, Recipe.class);
    }

    public static View getShoppingListView(CouchbaseHandler handler) throws ClassNotFoundException, IOException,
        CouchbaseLiteException
    {
        return AvailableViews.getViewForClass(handler, Shoppinglist.class);
    }
}
