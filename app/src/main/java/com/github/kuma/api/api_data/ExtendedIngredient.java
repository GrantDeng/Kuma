
package com.github.kuma.api.api_data;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExtendedIngredient {

    @SerializedName("aisle")
    @Expose
    private String aisle;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("amount")
    @Expose
    private double amount;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("unitShort")
    @Expose
    private String unitShort;
    @SerializedName("unitLong")
    @Expose
    private String unitLong;
    @SerializedName("originalString")
    @Expose
    private String originalString;
    @SerializedName("metaInformation")
    @Expose
    private List<Object> metaInformation = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The aisle
     */
    public String getAisle() {
        return aisle;
    }

    /**
     * 
     * @param aisle
     *     The aisle
     */
    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *     The amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * 
     * @return
     *     The unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 
     * @param unit
     *     The unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 
     * @return
     *     The unitShort
     */
    public String getUnitShort() {
        return unitShort;
    }

    /**
     * 
     * @param unitShort
     *     The unitShort
     */
    public void setUnitShort(String unitShort) {
        this.unitShort = unitShort;
    }

    /**
     * 
     * @return
     *     The unitLong
     */
    public String getUnitLong() {
        return unitLong;
    }

    /**
     * 
     * @param unitLong
     *     The unitLong
     */
    public void setUnitLong(String unitLong) {
        this.unitLong = unitLong;
    }

    /**
     * 
     * @return
     *     The originalString
     */
    public String getOriginalString() {
        return originalString;
    }

    /**
     * 
     * @param originalString
     *     The originalString
     */
    public void setOriginalString(String originalString) {
        this.originalString = originalString;
    }

    /**
     * 
     * @return
     *     The metaInformation
     */
    public List<Object> getMetaInformation() {
        return metaInformation;
    }

    /**
     * 
     * @param metaInformation
     *     The metaInformation
     */
    public void setMetaInformation(List<Object> metaInformation) {
        this.metaInformation = metaInformation;
    }

}
