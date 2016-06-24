
package com.github.kuma.api.api_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsedIngredient {

    @SerializedName("aisle")
    @Expose
    private String aisle;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;

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

}
