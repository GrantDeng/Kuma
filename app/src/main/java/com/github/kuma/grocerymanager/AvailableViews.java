package com.github.kuma.grocerymanager;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.View;
import com.github.kuma.data.db.CouchbaseHandler;
import com.github.kuma.data.db.TypeConnector;
import com.github.kuma.db_object.Data;
import com.github.kuma.db_object.Grocery;
import com.github.kuma.db_object.Mealplan;
import com.github.kuma.db_object.Recipe;
import com.github.kuma.db_object.Savable;
import com.github.kuma.db_object.Shoppinglist;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * AvailableViews provides predefined Views of various data types.
 */
public final class AvailableViews
{
    private static final String VERSION_NUMBER = "1";

    private static Map<Class<?>, View> viewMap = new HashMap<Class<?>, View>();

    private AvailableViews() {}

    /**
     * getViewForClass returns a View that spits out Objects of the desired class.
     * @param handler CouchbaseHandler to retrieve data from.
     * @param klass Class of data you're interested in.
     * @return A View that maps names (document ID's) to objects of the given class.
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws CouchbaseLiteException
     */
    private static View getViewForClass(CouchbaseHandler handler, Class<? extends Savable> klass) throws ClassNotFoundException,
                IOException, CouchbaseLiteException
    {
        View view = AvailableViews.viewMap.get(klass);

        if(view == null)
        {
            String classString = klass.toString();
            view = handler.getDbInstance().getView(classString);
            view.setDocumentType(classString);
            view.setMap(
                TypeConnector.typeMapper(klass),
                AvailableViews.VERSION_NUMBER
            );
            AvailableViews.viewMap.put(klass, view);
        }

        return view;
    }

    /**
     * Return a predefined view for the Data class.
     * @param handler The CouchbaseHandler to retrieve data from.
     * @return A predefined view mapping names (document ID's) to objects of the Data class.
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws CouchbaseLiteException
     */
    public static View getDataView(CouchbaseHandler handler) throws ClassNotFoundException, IOException,
        CouchbaseLiteException
    {
        return AvailableViews.getViewForClass(handler, Data.class);
    }

    /**
     * Return a predefined view for the Grocery class.
     * @param handler The CouchbaseHandler to retrieve data from.
     * @return A predefined view mapping names (document ID's) to objects of the Grocery class.
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws CouchbaseLiteException
     */
    public static View getGroceryView(CouchbaseHandler handler) throws ClassNotFoundException, IOException,
        CouchbaseLiteException
    {
        return AvailableViews.getViewForClass(handler, Grocery.class);
    }

    /**
     * Return a predefined view for the MealPlan class.
     * @param handler The CouchbaseHandler to retrieve data from.
     * @return A predefined view mapping names (document ID's) to objects of the MealPlan class.
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws CouchbaseLiteException
     */
    public static View getMealPlanView(CouchbaseHandler handler) throws ClassNotFoundException, IOException,
        CouchbaseLiteException
    {
        return AvailableViews.getViewForClass(handler, Mealplan.class);
    }

    /**
     * Return a predefined view for the Recipe class.
     * @param handler The CouchbaseHandler to retrieve data from.
     * @return A predefined view mapping names (document ID's) to objects of the Recipe class.
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws CouchbaseLiteException
     */
    public static View getRecipeView(CouchbaseHandler handler) throws ClassNotFoundException, IOException,
        CouchbaseLiteException
    {
        return AvailableViews.getViewForClass(handler, Recipe.class);
    }

    /**
     * Return a predefined view for the ShoppingList class.
     * @param handler The CouchbaseHandler to retrieve data from.
     * @return A predefined view mapping names (document ID's) to objects of the ShoppingList class.
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws CouchbaseLiteException
     */
    public static View getShoppingListView(CouchbaseHandler handler) throws ClassNotFoundException, IOException,
        CouchbaseLiteException
    {
        return AvailableViews.getViewForClass(handler, Shoppinglist.class);
    }
}
