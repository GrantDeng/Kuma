
package com.github.kuma.db_object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import javax.annotation.Generated;

//@Generated("org.jsonschema2pojo")
public class Recipe {

    private int id;
    private String nutrition;
    private List<String> ingredientsNames = new ArrayList<String>();
    private String objectType;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The nutrition
     */
    public String getNutrition() {
        return nutrition;
    }

    /**
     * 
     * @param nutrition
     *     The nutrition
     */
    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    /**
     * 
     * @return
     *     The ingredientsNames
     */
    public List<String> getIngredientsNames() {
        return ingredientsNames;
    }

    /**
     * 
     * @param ingredientsNames
     *     The ingredients_names
     */
    public void setIngredientsNames(List<String> ingredientsNames) {
        this.ingredientsNames = ingredientsNames;
    }

    /**
     * 
     * @return
     *     The objectType
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * 
     * @param objectType
     *     The objectType
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
