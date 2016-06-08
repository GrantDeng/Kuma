
package com.github.kuma.db_object;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import javax.annotation.Generated;

//@Generated("org.jsonschema2pojo")
public class Grocery {

    private int id;
    private int quantity;
    private int duration;
    private String location;
    private Date purchaseDate;
    private String dibs;
    private String dataType;
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
     *     The quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * 
     * @param quantity
     *     The quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * 
     * @return
     *     The duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * 
     * @return
     *     The location
     */
    public String getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 
     * @return
     *     The purchaseDate
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * 
     * @param purchaseDate
     *     The purchase_date
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * 
     * @return
     *     The dibs
     */
    public String getDibs() {
        return dibs;
    }

    /**
     * 
     * @param dibs
     *     The dibs
     */
    public void setDibs(String dibs) {
        this.dibs = dibs;
    }

    /**
     * 
     * @return
     *     The dataType
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * 
     * @param dataType
     *     The data_type
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
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
