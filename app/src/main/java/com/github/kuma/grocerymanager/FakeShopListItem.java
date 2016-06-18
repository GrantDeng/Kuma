package com.github.kuma.grocerymanager;

/**
 *
 */
public class FakeShopListItem
{
    private String itemName;
    private String category;
    private boolean isChecked;

    public FakeShopListItem(String category, String name)
    {
        this.category = category;
        itemName = name;
        isChecked = false;
    }

    public String getName(){return itemName;}
}
