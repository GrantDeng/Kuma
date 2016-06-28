package com.github.kuma.grocerymanager;

import android.content.Context;
import android.widget.ListView;

import java.util.List;

public class MealplanViewHandler
{
    Context context;
    ListView listview;
    MealplanAdapter ra;

    public MealplanViewHandler(Context c)
    {
        context = c;
    }

    public void setListview (ListView lv)
    {
        listview = lv;
    }

    public void setAdapter (List<MealplanRecipe> data)
    {
        ra = new MealplanAdapter(context,data);
        listview.setAdapter(ra);
    }
}
