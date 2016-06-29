package com.github.kuma.db_object;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Mealplan extends Savable
{
    public static final String BREAKFAST = "breakfast";
    public static final String LUNCH = "lunch";
    public static final String DINNER = "dinner";
    private Date date;
    private String mealType;
    private List<String> recipesIds = new ArrayList<String>();
    private List<String> foodNames = new ArrayList<String>();

    /**
     *
     * @return
     *     The date
     */
    public Date getDate()
    {
        return date;
    }

    /**
     *
     * @param date
     *     The date
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     *
     * @return
     *     The mealType
     */
    public String getMealType()
    {
        return mealType;
    }

    /**
     *
     * @param mealType
     *     The meal_type
     */
    public void setMealType(String mealType)
    {
        this.mealType = mealType;
    }

    /**
     *
     * @return
     *     The recipesIds
     */
    public List<String> getRecipesIds()
    {
        return recipesIds;
    }

    /**
     *
     * @param recipesIds
     *     The recipes_ids
     */
    public void setRecipesIds(List<String> recipesIds)
    {
        this.recipesIds = recipesIds;
    }

    /**
     *
     * @return
     *     The foodNames
     */
    public List<String> getFoodNames()
    {
        return foodNames;
    }

    /**
     *
     * @param foodNames
     *     The food_names
     */
    public void setFoodNames(List<String> foodNames)
    {
        this.foodNames = foodNames;
    }
}
