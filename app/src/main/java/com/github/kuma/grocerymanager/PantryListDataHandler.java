package com.github.kuma.grocerymanager;

import android.content.Context;

import com.github.kuma.data.db.SimpleDbInterface;
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
    List<Grocery> javaData;
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
        data = SimpleDbInterface.getAllGroceryDocuments(context);
        javaData = new ArrayList<Grocery>();

        loadListData();
        list_of_listItem = makeList();
        return list_of_listItem;
    }

    public void addItemToShopList(int pos, int numOfCategoryPassing) throws Exception
    {
        int realPos = pos - numOfCategoryPassing;

        String dataId = javaData.get(realPos).getRelatedDataId();

        DbDocument dbDoc = new DbDocument(context,dataId);
        dbDoc.setProperty("isInShoppingList",true);

        // create and add shopping list item
        Shoppinglist shopListItem = new Shoppinglist();
        String id = Savable.generateId();
        shopListItem.setDataName((String)dbDoc.getProperty("name"));
        shopListItem.setBought(false);
        shopListItem.setCategory((String)dbDoc.getProperty("category"));
        shopListItem.setId(id);
        shopListItem.setRelatedDataId(dataId);

        SimpleDbInterface.saveToDatabase(shopListItem,context);
        dbDoc.setProperty("shopListId",id);
    }

    public void removeItemFromShopList(int pos,int numOfCategoryPassing) throws Exception
    {
        int realPos = pos - numOfCategoryPassing;

        String dataId = javaData.get(realPos).getRelatedDataId();

        DbDocument dbDoc = new DbDocument(context,dataId);
        dbDoc.setProperty("isInShoppingList",false);
        System.err.println("remove start");
        // remove shopping list item
        String shopListItemId = (String)dbDoc.getProperty("shopListId");
        DbDocument dbDoc_shoplist = new DbDocument(context,shopListItemId);
        dbDoc_shoplist.delete();
        dbDoc.setProperty("shopListId",null);
        System.err.println("remove finish");
    }

    private void loadListData() throws Exception
    {
        listData = new HashMap<String, List<Grocery>>();

        for(DbDocument dbDoc : data)
        {
            String itemName = (String) dbDoc.getProperty("name");
            int duration = (Integer)dbDoc.getProperty("duration");
            System.err.println("load list data: "+duration);
            String DataId = (String)dbDoc.getProperty("relatedDataId");
            String category = getDataCategory(DataId);

            Grocery item = new Grocery();
            item.setName(itemName);
            item.setDuration(duration);
            item.setRelatedDataId(DataId);

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
        return (String)dbDoc.getProperty("category");
    }

    private List<ShopAndPantryListItem> makeList() throws Exception
    {
        List<ShopAndPantryListItem> genList = new ArrayList<ShopAndPantryListItem>();
        Set<String> categorySet = listData.keySet();
        categoryCount = 1;

        for(String category : categorySet)
        {
            List<Grocery> itemlist = listData.get(category);
            ShopAndPantryListItemHeader header = new ShopAndPantryListItemHeader(category);
            genList.add(header);

            for(Grocery item: itemlist)
            {
                ShopAndPantryListSingleItem singleItem = new ShopAndPantryListSingleItem(item.getName());

                singleItem.setNumOfCategoryPassing(categoryCount);
                singleItem.setExpiry(item.getDuration());

                DbDocument dbDoc = new DbDocument(context,item.getRelatedDataId());
                boolean isInShoppingList = (Boolean)dbDoc.getProperty("isInShoppingList");

                if(isInShoppingList)
                {
                    singleItem.checkItem();
                }

                javaData.add(item);
                genList.add(singleItem);
            }

            categoryCount++;
        }
        return genList;
    }
}
