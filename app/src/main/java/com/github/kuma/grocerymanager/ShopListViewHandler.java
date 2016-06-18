package com.github.kuma.grocerymanager;

import android.content.Context;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ShopListViewHandler
{
    private ListAdapter adapter;
    private Context context;
    private ListView listview;
    private List<ShopListItem> data;

    public ShopListViewHandler(ListView lv, Context context)
    {
        this.context = context;
        listview = lv;
        data = new ArrayList<ShopListItem>();
    }

    public void setData(List<ShopListItem> data)
    {
        this.data = data;
    }

    public void setListAdapter()
    {
        adapter = new ListAdapter(context,data);
        listview.setAdapter(adapter);
    }
}
