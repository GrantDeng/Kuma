package com.github.kuma.grocerymanager;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class FakeDataHandler
{
    private HashMap<String,List<FakeShopListItem>> hm;

    public FakeDataHandler()
    {
        FakeShopListItem item1 = new FakeShopListItem("Fruit","apple");
        FakeShopListItem item2 = new FakeShopListItem("Fruit","pear");
        FakeShopListItem item3 = new FakeShopListItem("Fruit","pineapple");
        FakeShopListItem item4 = new FakeShopListItem("Diary","Cheddar cheese");
        FakeShopListItem item5 = new FakeShopListItem("Diary","2% Milk");
        FakeShopListItem item6 = new FakeShopListItem("Soft Drinks","Coke");

        hm = new HashMap<String, List<FakeShopListItem>>();
        List<FakeShopListItem> list1 = new ArrayList<FakeShopListItem>();
        List<FakeShopListItem> list2 = new ArrayList<FakeShopListItem>();
        List<FakeShopListItem> list3 = new ArrayList<FakeShopListItem>();
        list1.add(item1);
        list1.add(item2);
        list1.add(item3);
        list2.add(item4);
        list2.add(item5);
        list3.add(item6);
        hm.put("Fruit",list1);
        hm.put("Diary",list2);
        hm.put("Soft Drinks",list3);
    }

    public List<ShopListItem> generateList()
    {
        Set<String> categoryList = hm.keySet();
        List<ShopListItem> list_of_listItem = new ArrayList<ShopListItem>();
        for(String category : categoryList)
        {
            List<FakeShopListItem> list_of_Fakeitems = hm.get(category);
            ShopListItemHeader header = new ShopListItemHeader(category);
            list_of_listItem.add(header);

            Log.e("loader",category);

            for(FakeShopListItem item : list_of_Fakeitems)
            {
                ShopListSingleItem singleListItem = new ShopListSingleItem(item.getName());
                list_of_listItem.add(singleListItem);

                Log.e("loader",item.getName());
            }
        }

        return list_of_listItem;
    }
}
