package com.github.kuma.db_object;

import java.util.HashMap;
import java.util.Map;

public class Shoppinglist
{
    private boolean bought;
    private String dataName;
    private String type;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The bought
     */
    public boolean isBought()
    {
        return bought;
    }

    /**
     *
     * @param bought
     *     The bought
     */
    public void setBought(boolean bought)
    {
        this.bought = bought;
    }

    /**
     *
     * @return
     *     The dataName
     */
    public String getDataName()
    {
        return dataName;
    }

    /**
     *
     * @param dataName
     *     The data_name
     */
    public void setDataName(String dataName)
    {
        this.dataName = dataName;
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
