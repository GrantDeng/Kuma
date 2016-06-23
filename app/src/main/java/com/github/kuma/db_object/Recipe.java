package com.github.kuma.db_object;

import java.util.ArrayList;
import java.util.List;

public class Recipe extends Savable
{
    private String nutrition;
    private List<String> ingredientsNames = new ArrayList<String>();

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
}
