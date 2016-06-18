package com.github.kuma.grocerymanager;

/**
 *
 */
public class ShopListItemHeader implements ShopListItem
{
    private final String header;

    public ShopListItemHeader(String header)
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
