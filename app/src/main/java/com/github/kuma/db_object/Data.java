package com.github.kuma.db_object;

import java.util.HashMap;
import java.util.Map;

public class Data extends Savable
{
    private String name;
    private int guessDuration;
    private String dataType;
    private int totalQuantity;
    private String category;
    private String image;
    private boolean isInShoppingList;
    private String shopListId;

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
     *     The guessDuration
     */
    public int getGuessDuration()
    {
        return guessDuration;
    }

    /**
     *
     * @param guessDuration
     *     The guess_duration
     */
    public void setGuessDuration(int guessDuration)
    {
        this.guessDuration = guessDuration;
    }

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
     * @return
     *     The totalQuantity
     */
    public int getTotalQuantity()
    {
        return totalQuantity;
    }

    /**
     *
     * @param totalQuantity
     *     The total_quantity
     */
    public void setTotalQuantity(int totalQuantity)
    {
        this.totalQuantity = totalQuantity;
    }

    /**
     *
     * @return
     *     The category
     */
    public String getCategory()
    {
        return category;
    }

    /**
     *
     * @param category
     *     The category
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     *
     * @return
     *     The image
     */
    public String getImage()
    {
        return image;
    }

    /**
     *
     * @param image
     *     The image
     */
    public void setImage(String image)
    {
        this.image = image;
    }

    /**
     *
     * @return isInShoppingList
     *      Determine whether this food is moved into shopping list
     */
    public boolean getIsInShoppingList() { return isInShoppingList; }

    /**
     *
     * @param isInShoppingList
     *      Set the boolean value for isInShoppingList
     */
    public void setIsInShoppingList(boolean isInShoppingList) { this.isInShoppingList = isInShoppingList; }

    /**
     *
     * @return shopListId
     *      Get the relative Shopping List item's document ID
     */
    public String getShopListId() { return shopListId; }

    /**
     *
     * @param id
     *      Set the Shopping List item's document ID
     */
    public void setShopListId(String id) { shopListId = id; }
}
