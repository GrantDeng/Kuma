package com.github.kuma.db_object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe extends Savable
{
    private String nutrition;
    private List<String> ingredientsNames = new ArrayList<String>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The nutrition
     */
    public String getNutrition()
    {
        return nutrition;
    }

    /**
     *
     * @param nutrition
     *     The nutrition
     */
    public void setNutrition(String nutrition)
    {
        this.nutrition = nutrition;
    }

    /**
     *
     * @return
     *     The ingredientsNames
     */
    public List<String> getIngredientsNames()
    {
        return ingredientsNames;
    }

    /**
     *
     * @param ingredientsNames
     *     The ingredients_names
     */
    public void setIngredientsNames(List<String> ingredientsNames)
    {
        this.ingredientsNames = ingredientsNames;
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
