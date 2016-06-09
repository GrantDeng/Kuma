package com.github.kuma.db_object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe
{
    private int id;
    private String nutrition;
    private List<String> ingredientsNames = new ArrayList<String>();
    private String type;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The id
     */
    public int getId()
    {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(int id)
    {
        this.id = id;
    }

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
     *     The type
     */
    public void setType(String type)
    {
        this.type = type;
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
