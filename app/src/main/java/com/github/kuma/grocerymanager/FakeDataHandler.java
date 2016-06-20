package com.github.kuma.grocerymanager;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class FakeDataHandler
{
    private HashMap<String,List<FakeShopListItem>> hm_shoplist;
    private HashMap<String,List<FakePantryItem>> hm_pantry;
    private String db;

    public FakeDataHandler(String db_name)
    {
        db = db_name;
        if(db_name.equals("shoplist"))
        {
            FakeShopListItem item1 = new FakeShopListItem("Fruit","apple");
            FakeShopListItem item2 = new FakeShopListItem("Fruit","pear");
            FakeShopListItem item3 = new FakeShopListItem("Fruit","pineapple");
            FakeShopListItem item4 = new FakeShopListItem("Diary","Cheddar cheese");
            FakeShopListItem item5 = new FakeShopListItem("Diary","2% Milk");
            FakeShopListItem item6 = new FakeShopListItem("Soft Drinks","Coke");

            hm_shoplist = new HashMap<String, List<FakeShopListItem>>();
            List<FakeShopListItem> list1 = new ArrayList<FakeShopListItem>();
            List<FakeShopListItem> list2 = new ArrayList<FakeShopListItem>();
            List<FakeShopListItem> list3 = new ArrayList<FakeShopListItem>();
            list1.add(item1);
            list1.add(item2);
            list1.add(item3);
            list2.add(item4);
            list2.add(item5);
            list3.add(item6);
            hm_shoplist.put("Fruit",list1);
            hm_shoplist.put("Diary",list2);
            hm_shoplist.put("Soft Drinks",list3);
        }

        if(db_name.equals("pantry"))
        {
            FakePantryItem item1 = new FakePantryItem("Fruit","apple",new Date(),"Low");
            FakePantryItem item2 = new FakePantryItem("Fruit","pineapple",new Date(),"Medium");
            FakePantryItem item3 = new FakePantryItem("Diary","2% Milk",new Date(),"High");
            FakePantryItem item4 = new FakePantryItem("Soft Drinks","apple",new Date(),"Low");

            hm_pantry = new HashMap<String, List<FakePantryItem>>();
            List<FakePantryItem> list1 = new ArrayList<FakePantryItem>();
            List<FakePantryItem> list2 = new ArrayList<FakePantryItem>();
            List<FakePantryItem> list3 = new ArrayList<FakePantryItem>();
            list1.add(item1);
            list1.add(item2);
            list2.add(item3);
            list3.add(item4);
            hm_pantry.put("Fruit",list1);
            hm_pantry.put("Diary",list2);
            hm_pantry.put("Soft Drinks",list3);
        }

    }

    public List<ShopAndPantryListItem> generatePantryList()
    {
        Set<String> categoryList = hm_pantry.keySet();
        List<ShopAndPantryListItem> list_of_listItem = new ArrayList<ShopAndPantryListItem>();
        for(String category : categoryList)
        {
            List<FakePantryItem> list_of_Fakeitems = hm_pantry.get(category);
            ShopAndPantryListItemHeader header = new ShopAndPantryListItemHeader(category);
            list_of_listItem.add(header);

            for(FakePantryItem item : list_of_Fakeitems)
            {
                ShopAndPantryListSingleItem singleListItem = new ShopAndPantryListSingleItem(item.getName(),item.getDateString(),item.getStorageLevel(),false);
                list_of_listItem.add(singleListItem);
            }
        }
        return list_of_listItem;
    }

    public List<ShopAndPantryListItem> generateShopList()
    {
        Set<String> categoryList = hm_shoplist.keySet();
        List<ShopAndPantryListItem> list_of_listItem = new ArrayList<ShopAndPantryListItem>();
        for(String category : categoryList)
        {
            List<FakeShopListItem> list_of_Fakeitems = hm_shoplist.get(category);
            ShopAndPantryListItemHeader header = new ShopAndPantryListItemHeader(category);
            list_of_listItem.add(header);

            Log.e("loader",category);

            for(FakeShopListItem item : list_of_Fakeitems)
            {
                ShopAndPantryListSingleItem singleListItem = new ShopAndPantryListSingleItem(item.getName());
                list_of_listItem.add(singleListItem);

                Log.e("loader",item.getName());
            }
        }
        return list_of_listItem;
    }
}
