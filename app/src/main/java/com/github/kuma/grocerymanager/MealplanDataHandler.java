package com.github.kuma.grocerymanager;

import android.content.Context;
import android.util.Pair;

import com.github.kuma.data.db.DbDocument;
import com.github.kuma.data.db.SimpleDbInterface;
import com.github.kuma.db_object.Mealplan;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MealplanDataHandler
{
    private Context context;
    private List<DbDocument> data;
    private HashMap<Pair<Integer,Integer>,List<MealplanRecipe>> viewData;
    private HashMap<Pair<Integer,Integer>, String> map_of_mealplanId;
    private Date today;

    public MealplanDataHandler(Context context)
    {
        this.context = context;
        viewData = new HashMap<Pair<Integer, Integer>, List<MealplanRecipe>>();
        map_of_mealplanId = new HashMap<Pair<Integer, Integer>, String>();
        today = new Date();
    }

    public void deleteRecipe (int day, int period, String name) throws Exception
    {
        String mealplanId = map_of_mealplanId.get(new Pair<Integer, Integer>(day,period));
        DbDocument mealplan_dbdoc = new DbDocument(context,mealplanId);

        // check matched recipe
        List<String> recipeIds = (List<String>)mealplan_dbdoc.getProperty("recipesIds");
        for(String id : recipeIds)
        {
            DbDocument recipe_dbdoc = new DbDocument(context,id);
            String recipeName = (String)recipe_dbdoc.getProperty("name");
            if(name.equals(recipeName))
            {
                recipeIds.remove(id);
                break;
            }
        }
        // check matched food
        List<String> foods = (List<String>) mealplan_dbdoc.getProperty("foodNames");
        for(String foodName : foods)
        {
            if(name.equals(foodName))
            {
                foods.remove(foodName);
                break;
            }
        }

        // update meal plan data
        HashMap<String,Object> newProperties = new HashMap<String, Object>();
        newProperties.put("recipeIds",recipeIds);
        newProperties.put("foodNames",foods);
        mealplan_dbdoc.setProperties(newProperties);

    }

    public void loadData() throws  Exception
    {
        data = SimpleDbInterface.getAllMealPlanDocuments(context);
    }

    public HashMap<Pair<Integer,Integer>,List<MealplanRecipe>> getViewData() throws Exception
    {
        validateData(); // remove any meal plan data before today and generate view data, map of mealplan id
        return viewData;
    }

    private int getMealTypeNum (String mealType)
    {
        if(mealType.equals(Mealplan.BREAKFAST)) return 1;
        else if(mealType.equals(Mealplan.LUNCH)) return 2;
        else return 3;

    }

    private void validateData() throws Exception
    {
        if (data != null)
        {
            for(DbDocument dbDoc : data)
            {
                Date planDate = (Date) dbDoc.getProperty("date");
                int daysBetween = DateUtils.getDaysBetween(today,planDate);
                String mealType = (String)dbDoc.getProperty("mealType");
                int mealTypeNum = getMealTypeNum(mealType);
                String mealplanId = (String) dbDoc.getProperty("id");

                Pair<Integer,Integer> mapkey = new Pair<Integer, Integer>(daysBetween,mealTypeNum);
                map_of_mealplanId.put(mapkey,mealplanId);

                if(daysBetween < 0)     // when mealplan expires more than two weeks (14 days,default), remove the meal plan entry
                                        // days can also be modified in setting
                {
                    if(daysBetween <= -14)
                    {
                        dbDoc.delete();
                    }
                }
                else{
                    // construct viewData
                    List<String> list_of_recipe_ids = (List<String>) dbDoc.getProperty("recipesIds");
                    List<String> list_of_foodNames = (List<String>) dbDoc.getProperty("foodNames");

                    List<MealplanRecipe> list_of_recipe = viewData.get(mapkey);

                    if(list_of_recipe == null)
                    {
                        list_of_recipe = new ArrayList<MealplanRecipe>();
                    }
                    // add recipes
                    for(String recipeId : list_of_recipe_ids)
                    {
                        DbDocument recipeDbDoc = new DbDocument(context,recipeId);
                        String mealName = (String)recipeDbDoc.getProperty("name");

                        MealplanRecipe meal = new MealplanRecipe(daysBetween,mealTypeNum,mealName);

                        if(list_of_recipe == null)
                        {
                            list_of_recipe = new ArrayList<MealplanRecipe>();
                        }

                        list_of_recipe.add(meal);
                        viewData.put(mapkey,list_of_recipe);
                    }
                    // add foods (i.e. apples, eggs)
                    for(String food : list_of_foodNames)
                    {
                        if(list_of_recipe == null)
                        {
                            list_of_recipe = new ArrayList<MealplanRecipe>();
                        }
                        MealplanRecipe meal = new MealplanRecipe(daysBetween,mealTypeNum,food);

                        list_of_recipe.add(meal);
                        viewData.put(mapkey,list_of_recipe);
                    }
                }
            }
        }
    }
}
