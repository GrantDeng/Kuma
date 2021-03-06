package com.github.kuma.grocerymanager;

import android.content.Context;

import com.github.kuma.data.db.CouchbaseHandler;
import com.github.kuma.data.db.DbDocument;
import com.github.kuma.data.db.SimpleDbInterface;
import com.github.kuma.db_object.Data;
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
    List<Shoppinglist> javaData;
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
        data = SimpleDbInterface.getAllShopListDocuments(context);
        javaData = new ArrayList<Shoppinglist>();

        loadListData();
        list_of_listItem = makeList();
        return list_of_listItem;
    }

    public void checkItem(int pos,int numOfCategoryPass) throws Exception
    {
        int realPos = pos - numOfCategoryPass;

        javaData.get(realPos).setBought(true);
        DbDocument dbDoc = new DbDocument(context,javaData.get(realPos).getId());
        dbDoc.setProperty("bought",true);
    }

    public void deleteItem(int pos,int numOfCategoryPass) throws Exception
    {
        int realPos = pos - numOfCategoryPass;

        // Uncheck relative food item on pantry
        Object dataIdObject = javaData.get(realPos).getRelatedDataId();
        if(dataIdObject != null)
        {
            DbDocument data_dbDoc = new DbDocument(context,dataIdObject.toString());
            data_dbDoc.setProperty("isInShoppingList",false);
        }

        DbDocument shopListDoc = new DbDocument(context,javaData.get(realPos).getId());
        shopListDoc.delete();
    }

    public void addItem(String name) throws Exception
    {
        Shoppinglist newItem = new Shoppinglist();
        newItem.setBought(false);
        newItem.setDataName(name);

        // search from food data
        CouchbaseHandler ch = CouchbaseHandler.getCouchbaseHandler(context);

        Data foodData = DataUtils.getByName(name,ch);
        if(foodData != null)
        {
            String category = foodData.getCategory();
            if(category != null)
            {
                newItem.setCategory(category);
            }
        }
        SimpleDbInterface.saveToDatabase(newItem,context);
    }

    private List<ShopAndPantryListItem> makeList()
    {
        List<ShopAndPantryListItem> genList = new ArrayList<ShopAndPantryListItem>();
        Set<String> categorySet = listData.keySet();
        categoryCount = 0;

        for(String category : categorySet)
        {
            List<Shoppinglist> itemlist = listData.get(category);
            ShopAndPantryListItemHeader header = new ShopAndPantryListItemHeader(category);
            genList.add(header);
            categoryCount++;

            for(Shoppinglist item: itemlist)
            {
                ShopAndPantryListSingleItem singleItem = new ShopAndPantryListSingleItem(item.getDataName());
                singleItem.setNumOfCategoryPassing(categoryCount);

                if(item.getBought())
                {
                    singleItem.checkItem();
                }
                javaData.add(item);
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
            String category = (String) dbDoc.getProperty("category");
            if(category == null)
            {
                category = "Other";
            }
            String itemName = dbDoc.getProperty("dataName").toString();
            Boolean bought = (Boolean)dbDoc.getProperty("bought");

            Shoppinglist item = new Shoppinglist();
            item.setDataName(itemName);
            item.setBought(bought);
            item.setCategory(category);
            item.setId((String)dbDoc.getProperty("id"));
            item.setRelatedDataId((String)dbDoc.getProperty("relatedDataId"));

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
