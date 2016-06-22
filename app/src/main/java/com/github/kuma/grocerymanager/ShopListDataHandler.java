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
    HashMap<String,List<Shoppinglist>> listData;
    Context context;
    int categoryCount;

    public ShopListDataHandler(Context context)
    {
        this.context = context;
        categoryCount = 0;
    }

    public List<ShopAndPantryListItem> generateList() throws Exception
    {
        List<ShopAndPantryListItem> list_of_listItem;
        data = DbUtils.getAllShopListDocuments(context);

        loadListData();
        list_of_listItem = makeList();
        return list_of_listItem;
    }

    public void checkItem(int pos) throws Exception
    {
        int realPos = pos - categoryCount;
        data.get(realPos).setProperty("bought",true);
    }

    public void deleteItem(int pos) throws Exception
    {
        int realPos = pos - categoryCount;

        // Uncheck relative food item on pantry
        Object dataIdObject = data.get(realPos).getProperty("relatedDataId");
        if(dataIdObject != null)
        {
            DbDocument data_dbDoc = new DbDocument(context,dataIdObject.toString());
            data_dbDoc.setProperty("isInShoppingList",false);
        }

        data.get(realPos).delete();
    }

    public void addItem(String name) throws Exception
    {
        Shoppinglist newItem = new Shoppinglist();
        newItem.setBought(false);
        newItem.setDataName(name);
        newItem.setAdditionalProperty("category","Other");

        DbUtils.saveShopListItemToDatabase(newItem,context);
    }

    private List<ShopAndPantryListItem> makeList()
    {
        List<ShopAndPantryListItem> genList = new ArrayList<ShopAndPantryListItem>();
        Set<String> categorySet = listData.keySet();
        categoryCount = categorySet.size();

        for(String category : categorySet)
        {
            List<Shoppinglist> itemlist = listData.get(category);
            ShopAndPantryListItemHeader header = new ShopAndPantryListItemHeader(category);
            genList.add(header);

            for(Shoppinglist item: itemlist)
            {
                ShopAndPantryListSingleItem singleItem = new ShopAndPantryListSingleItem(item.getDataName());
                if(item.isBought())
                {
                    singleItem.checkItem();
                }
                genList.add(singleItem);
            }
        }
        return genList;
    }

    private void loadListData()
    {
        listData = new HashMap<String, List<Shoppinglist>>();

        for(DbDocument dbDoc : data)
        {
            String category = dbDoc.getProperty("category").toString();
            String itemName = dbDoc.getProperty("dataName").toString();
            Boolean bought = (Boolean)dbDoc.getProperty("bought");

            Shoppinglist item = new Shoppinglist();
            item.setDataName(itemName);
            item.setBought(bought);

            if(listData.containsKey(category))
            {
                listData.get(category).add(item);

            }
            else    // init this category
            {
                List<Shoppinglist> newList = new ArrayList<Shoppinglist>();
                newList.add(item);
                listData.put(category,newList);
            }
        }
    }
}
