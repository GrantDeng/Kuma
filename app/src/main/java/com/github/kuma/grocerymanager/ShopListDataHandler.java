package com.github.kuma.grocerymanager;

import android.content.Context;

import com.github.kuma.data.DbUtils;
import com.github.kuma.data.db.DbDocument;
import com.github.kuma.db_object.Shoppinglist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *  Shopping List data handler to handle operations:
 *  - check item
 *  - delete item
 *  - generate List of ShopAndPantryListItems for list view
 */
public class ShopListDataHandler
{
    List<DbDocument> data;
    HashMap<String,List<String>> listData;
    Context context;

    public ShopListDataHandler(Context context)
    {
        this.context = context;
    }

    public List<ShopAndPantryListItem> generateList() throws Exception
    {
        List<ShopAndPantryListItem> list_of_listItem = null;
        data = DbUtils.getAllDocuments(context);
        loadListData();
        list_of_listItem = makeList();
        return list_of_listItem;
    }

    public void checkItem(int pos) throws Exception
    {
        data.get(pos).setProperty("bought",true);
    }

    public void deleteItem(int pos) throws Exception
    {
        data.get(pos).delete();
    }

    public void addItem(String name)
    {
        Shoppinglist newItem = new Shoppinglist();
        newItem.setBought(false);
        newItem.setDataName(name);
        newItem.setAdditionalProperty("category","Other");
    }

    private List<ShopAndPantryListItem> makeList()
    {
        List<ShopAndPantryListItem> genList = new ArrayList<ShopAndPantryListItem>();
        Set<String> categorySet = listData.keySet();
        for(String category : categorySet)
        {
            List<String> itemlist = listData.get(category);
            ShopAndPantryListItemHeader header = new ShopAndPantryListItemHeader(category);
            genList.add(header);

            for(String itemName : itemlist)
            {
                ShopAndPantryListSingleItem singleItem = new ShopAndPantryListSingleItem(itemName);
                genList.add(singleItem);
            }
        }
        return genList;
    }

    private void loadListData()
    {
        listData = new HashMap<String, List<String>>();
        for(DbDocument dbDoc : data)
        {
            String category = dbDoc.getProperty("category").toString();
            String itemName = dbDoc.getName();

            if(listData.containsKey(category))
            {
                listData.get(category).add(itemName);
            }
            else    // init this category
            {
                List<String> newList = new ArrayList<String>();
                newList.add(itemName);
                listData.put(category,newList);
            }
        }
    }
}
