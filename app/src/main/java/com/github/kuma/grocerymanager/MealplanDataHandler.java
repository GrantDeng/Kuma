package com.github.kuma.grocerymanager;

import android.content.Context;

import com.github.kuma.data.db.DbDocument;
import com.github.kuma.data.db.SimpleDbInterface;

import java.util.List;

public class MealplanDataHandler
{
    Context context;
    List<DbDocument> data;

    public MealplanDataHandler(Context context)
    {
        //data = SimpleDbInterface.getAllMealPlanDocuments(context);
    }
}
