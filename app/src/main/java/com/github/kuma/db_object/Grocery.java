package com.github.kuma.db_object;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Grocery extends Savable
{
    public static final int EMPTY = 0;
    public static final int LOW = 1;
    public static final int SOME = 2;
    public static final int FULL = 3;
    private String name;
    private int quantity;
    private int duration;
    private String location;
    private Date purchaseDate;
    private String dibs;
    private String dataType;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    private String relativeDataDocumentId;
    private boolean isAdded;

    /**
     *
     * @return
     *     The name
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     *
     * @return
     *     The quantity
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     *
     * @param quantity
     *     The quantity
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     *     The duration
     */
    public int getDuration()
    {
        return duration;
    }

    /**
     *
     * @param duration
     *     The duration
     */
    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    /**
     *
     * @return
     *     The location
     */
    public String getLocation()
    {
        return location;
    }

    /**
     *
     * @param location
     *     The location
     */
    public void setLocation(String location)
    {
        this.location = location;
    }

    /**
     *
     * @return
     *     The purchaseDate
     */
    public Date getPurchaseDate()
    {
        return purchaseDate;
    }

    /**
     *
     * @param purchaseDate
     *     The purchase_date
     */
    public void setPurchaseDate(Date purchaseDate)
    {
        this.purchaseDate = purchaseDate;
    }

    /**
     *
     * @return
     *     The dibs
     */
    public String getDibs()
    {
        return dibs;
    }

    /**
     *
     * @param dibs
     *     The dibs
     */
    public void setDibs(String dibs)
    {
        this.dibs = dibs;
    }

    /*
    public Map<String, Object> getAdditionalProperties()
    {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value)
    {
        this.additionalProperties.put(name, value);
    }*/

    /**
     *
     * @return
     *     The dataType
     */
    public String getDataType()
    {
        return dataType;
    }

    /**
     *
     * @param dataType
     *     The data_type
     */
    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    /**
     *
     * @return relativeDataDocumentId
     *      The related Data object's document ID
     */
    public String getRelativeDataDocumentId(){ return relativeDataDocumentId;}
}
