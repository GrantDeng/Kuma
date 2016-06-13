package com.github.kuma.db_object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class Mealplan
{
    private Date date;
    private String mealType;
    private List<Integer> recipesIds = new ArrayList<Integer>();
    private String type;
    private List<String> foodNames = new ArrayList<String>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
    public List<Integer> getRecipesIds()
    {
        return recipesIds;
    }

    /**
     *
     * @param recipesIds
     *     The recipes_ids
     */
    public void setRecipesIds(List<Integer> recipesIds)
    {
        this.recipesIds = recipesIds;
    }

    /**
     *
     * @return
     *     The type
     */
    public String getType()
    {
        return type;
    }

    /**
     *
     * @param type
     *     The object_type
     */
    public void setType(String type)
    {
        this.type = type;
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

    public Map<String, Object> getAdditionalProperties()
    {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value)
    {
        this.additionalProperties.put(name, value);
    }
}
