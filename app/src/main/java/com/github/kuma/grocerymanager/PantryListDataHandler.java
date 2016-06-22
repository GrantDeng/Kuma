package com.github.kuma.grocerymanager;

import android.content.Context;

import com.github.kuma.data.DbUtils;
import com.github.kuma.data.db.DbDocument;
import com.github.kuma.db_object.Grocery;
import com.github.kuma.db_object.Savable;
import com.github.kuma.db_object.Shoppinglist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
**/
public class PantryListDataHandler
{
    List<DbDocument> data;
    HashMap<String,List<Grocery>> listData;
    Context context;
    int categoryCount;

    public PantryListDataHandler(Context context)
    {
        this.context = context;
        categoryCount = 0;
    }

    public List<ShopAndPantryListItem> generateList() throws Exception
    {
        List<ShopAndPantryListItem> list_of_listItem;
        data = DbUtils.getAllGroceryDocuments(context);

        loadListData();
        list_of_listItem = makeList();
        return list_of_listItem;
    }

    public void addItemToShopList(int pos) throws Exception
    {
        int realPos = pos - categoryCount;
        String dataId = data.get(realPos).getProperty("relatedDataId").toString();

        DbDocument dbDoc = new DbDocument(context,dataId);
        dbDoc.setProperty("isInShoppingList",true);

        // create and add shopping list item
        Shoppinglist shopListItem = new Shoppinglist();
        String id = Savable.generateId();
        shopListItem.setDataName(dbDoc.getProperty("name").toString());
        shopListItem.setBought(false);
        shopListItem.setAdditionalProperty("category","Other");
        shopListItem.setId(id);
        shopListItem.setRelatedDataId(dataId);

        DbUtils.saveShopListItemToDatabase(shopListItem,context);
        dbDoc.setProperty("shopListId",id);
    }

    public void removeItemFromShopList(int pos) throws Exception
    {
        int realPos = pos - categoryCount;
        String dataId = data.get(realPos).getProperty("relatedDataId").toString();

        DbDocument dbDoc = new DbDocument(context,dataId);
        dbDoc.setProperty("isInShoppingList",false);

        // remove shopping list item
        String shopListItemId = dbDoc.getProperty("shopListId").toString();
        DbDocument dbDoc_shoplist = new DbDocument(context,shopListItemId);
        dbDoc_shoplist.delete();
        dbDoc.setProperty("shopListId",null);
    }

    private void loadListData() throws Exception
    {
        listData = new HashMap<String, List<Grocery>>();

        for(DbDocument dbDoc : data)
        {
            String itemName = dbDoc.getProperty("name").toString();
            int duration = (Integer)dbDoc.getProperty("duration");
            String DataId = dbDoc.getProperty("relatedDataId").toString();
            String category = getDataCategory(DataId);

            Grocery item = new Grocery();
            item.setName(itemName);
            item.setDuration(duration);
            item.setAdditionalProperty("relatedDataId", DataId);

            if(listData.containsKey(category))
            {
                listData.get(category).add(item);

            }
            else    // init this category
            {
                List<Grocery> newList = new ArrayList<Grocery>();
                newList.add(item);
                listData.put(category,newList);
            }
        }
    }

    private String getDataCategory(String id) throws Exception
    {
        DbDocument dbDoc = new DbDocument(context,id);
        return dbDoc.getProperty("category").toString();
    }

    private List<ShopAndPantryListItem> makeList() throws Exception
    {
        List<ShopAndPantryListItem> genList = new ArrayList<ShopAndPantryListItem>();
        Set<String> categorySet = listData.keySet();
        categoryCount = categorySet.size();

        for(String category : categorySet)
        {
            List<Grocery> itemlist = listData.get(category);
            ShopAndPantryListItemHeader header = new ShopAndPantryListItemHeader(category);
            genList.add(header);

            for(Grocery item: itemlist)
            {
                ShopAndPantryListSingleItem singleItem = new ShopAndPantryListSingleItem(item.getName());
                DbDocument dbDoc = new DbDocument(context,item.getRelatedDataId());
                boolean isInShoppingList = (Boolean)dbDoc.getProperty("isInShoppingList");

                if(isInShoppingList)
                {
                    singleItem.checkItem();
                }
                genList.add(singleItem);
            }
        }
        return genList;
    }
}