package com.github.kuma.grocerymanager;

import android.content.Context;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PantryListViewHandler
{
    private PantryListAdapter adapter;
    private Context context;
    private ListView listview;
    private List<ShopAndPantryListItem> data;

    public PantryListViewHandler(ListView lv, Context context)
    {
        this.context = context;
        listview = lv;
        data = new ArrayList<ShopAndPantryListItem>();
    }

    public void setData(List<ShopAndPantryListItem> data)
    {
        this.data = data;
    }

    public void setPantryListAdapter()
    {
        adapter = new PantryListAdapter(context,data);
        listview.setAdapter(adapter);
    }
}
