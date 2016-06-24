package com.github.kuma.db_object;

public class Shoppinglist extends Savable
{
    private boolean bought;
    private String dataName;
    private String relatedDataId;
    private String category;
    /**
     *
     * @return
     *     The bought
     */
    public boolean getBought()
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

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }
}
