package com.github.kuma.grocerymanager;

import android.content.Context;
import com.github.kuma.data.db.DbDocument;
import com.github.kuma.data.db.SimpleDbInterface;
import com.github.kuma.db_object.Mealplan;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MealplanDataHandler
{
    private Context context;
    private List<DbDocument> data;
    private HashMap<Integer,MealplanRecipe> viewData;
    private HashMap<Integer,Mealplan> javaData;
    private Date today;

    public MealplanDataHandler(Context context) throws Exception
    {
        data = SimpleDbInterface.getAllMealPlanDocuments(context);
        viewData = new HashMap<Integer, MealplanRecipe>();
        today = new Date();

        validateData(); // remove any meal plan data before today
    }

    public static int getDaysBetween(Date firstdate, Date seconddate)
    {
        DateTime firstDate_joda = new DateTime(firstdate);
        DateTime secondDate_joda = new DateTime(seconddate);
        int daysBetween = Days.daysBetween(firstDate_joda.withTimeAtStartOfDay(),secondDate_joda.withTimeAtStartOfDay()).getDays();
        return daysBetween;
    }

    private void validateData() throws Exception
    {
        if (data != null)
        {
            for(DbDocument dbDoc : data)
            {
                Date planDate = (Date) dbDoc.getProperty("date");
                int daysBetween = getDaysBetween(today,planDate);

                if(daysBetween < 0)
                {
                    dbDoc.delete();
                }
                else{
                    // construct javaData and viewData
                    List<String> list_of_recipe_names = (List<String>) dbDoc.getProperty("foodNames");

                    for(String recipe : list_of_recipe_names)
                    {

                    }

                }

            }
        }
    }
}
