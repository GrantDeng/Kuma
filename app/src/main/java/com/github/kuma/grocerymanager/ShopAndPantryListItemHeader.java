package com.github.kuma.grocerymanager;

/**
 *
 */
public class ShopAndPantryListItemHeader implements ShopAndPantryListItem
{
    private final String header;

    public ShopAndPantryListItemHeader(String header)
    {
        this.header = header;
    }

    public boolean isChecked(){return false;}
    public void checkItem(){return;}
    public String getName(){return header;}
    public int getViewType()
    {
        return 0;
    }
}
