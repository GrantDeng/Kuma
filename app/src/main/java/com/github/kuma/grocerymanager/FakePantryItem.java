package com.github.kuma.grocerymanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class FakePantryItem
{
    private String name;
    private Date expiry;
    private String storage_level;
    private String category;

    public FakePantryItem(String category, String name, Date expiry, String storage)
    {
        this.name = name;
        this.expiry = expiry;
        this.storage_level = storage;
        this.category = category;
    }
    public String getName(){return name;}
    public String getDateString()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(expiry);

        return strDate;
    }
    public String getStorageLevel(){return storage_level;}
    public String getCategory(){return category;}
}
