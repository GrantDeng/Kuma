
package com.github.kuma.db_object;

import java.util.HashMap;
import java.util.Map;
//import javax.annotation.Generated;

//@Generated("org.jsonschema2pojo")
public class Shoppinglist {

    private boolean bought;
    private String dataName;
    private String objectType;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The bought
     */
    public boolean isBought() {
        return bought;
    }

    /**
     * 
     * @param bought
     *     The bought
     */
    public void setBought(boolean bought) {
        this.bought = bought;
    }

    /**
     * 
     * @return
     *     The dataName
     */
    public String getDataName() {
        return dataName;
    }

    /**
     * 
     * @param dataName
     *     The data_name
     */
    public void setDataName(String dataName) {
        this.dataName = dataName;
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
