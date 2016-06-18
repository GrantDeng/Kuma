package com.github.kuma.grocerymanager;

/**
 * Thanks for: http://stackoverflow.com/questions/13590627/android-listview-headers
 */
public interface ShopListItem
{
    int getViewType();
    boolean isChecked();
    void checkItem();
    String getName();

}
