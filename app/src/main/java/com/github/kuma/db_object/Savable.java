package com.github.kuma.db_object;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Base database object class.
 */
public abstract class Savable
{
    private String id;
    private String type;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Generate a UUID.
     * @return The generated UUID.
     */
    public static String generateId()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * Return the type of this object.
     * @return The type of this object.
     */
    public String determineTypeString()
    {
        return this.getClass().toString();
    }

    /**
     *
     * @return
     *     The id
     */
    public String getId()
    {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(String id)
    {
        this.id = id;
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



    public Map<String, Object> getAdditionalProperties(){return additionalProperties;}
    public void setAdditionalProperty(String name, Object value)
    {
        this.additionalProperties.put(name, value);
    }

    public void setAdditionalProperties(Map<String, Object> map)
    {
        this.additionalProperties = map;
    }
}
