package com.github.kuma.grocerymanager;

/**
 *
 */
public class ShopAndPantryListSingleItem implements ShopAndPantryListItem
{
    private final String name;
    private int expiry;
    private String storageLevel;
    private boolean isChecked;
    private int numOfCategoryPassing;

    /* CONSTRUCTOR FOR SHOPPING LIST ITEM */
    public ShopAndPantryListSingleItem(String name)
    {
        this.name = name;
        isChecked = false;
        numOfCategoryPassing = 0;
    }

    /* CONSTRUCTOR FOR PANTRY ITEM */
    public ShopAndPantryListSingleItem(String name, int expiry, String storageLevel, boolean isChecked)
    {
        this.name = name;
        this.expiry = expiry;
        this.storageLevel = storageLevel;
        this.isChecked = isChecked;
    }

    public boolean isChecked(){return isChecked;}
    public void checkItem(){isChecked = true;}
    public int getViewType() {return 1;}

    public void unCheckItem(){isChecked = false;}
    public String getName(){return name;}
    public int getExpiry(){return expiry;}
    public String getStorageLevel(){return storageLevel;}
    public void setNumOfCategoryPassing(int num) { numOfCategoryPassing = num; }
    public void setExpiry(int e){expiry = e;}
    public int getNumOfCategoryPassing() {return numOfCategoryPassing;}
}
