package com.github.kuma.db_object;

import java.util.HashMap;
import java.util.Map;

public class Shoppinglist extends Savable
{
    private boolean bought;
    private String dataName;
    private String relatedDataId;
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
    public String getDataName() {return dataName;}

    /**
     *
     * @param dataName
     *     The data_name
     */
    public void setDataName(String dataName)
    {
        this.dataName = dataName;
    }

    public String getRelatedDataId(){ return relatedDataId; }

    public void setRelatedDataId(String id) { relatedDataId = id; }
}
