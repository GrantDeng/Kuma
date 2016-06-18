package com.github.kuma.grocerymanager;

/**
 *
 */
public class ShopListSingleItem implements ShopListItem
{
    private final String name;
    private boolean isChecked;

    public ShopListSingleItem(String name)
    {
        this.name = name;
        isChecked = false;
    }
    public boolean isChecked(){return isChecked;}
    public void checkItem(){isChecked = true;}
    public void unCheckItem(){isChecked = false;}
    public String getName(){return name;}
    public int getViewType() {return 1;}
}
