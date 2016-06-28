package com.github.kuma.grocerymanager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class MealplanAdapter extends BaseAdapter
{
    private LayoutInflater minflater;
    private Activity activity;
    private List<MealplanRecipe> data;

    public MealplanAdapter(Context context, List<MealplanRecipe> data)
    {
        this.activity = (Activity) context;
        minflater = activity.getLayoutInflater();
        this.data = data;
    }

    public interface ItemButtonCallBackInterface
    {
        void onDeleteRecipe(int day, int period, String name);
    }

    ItemButtonCallBackInterface buttonCallBack;

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public MealplanRecipe getItem(int pos)
    {
        return data.get(pos);
    }

    @Override
    public long getItemId (int pos)
    {
        return pos;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        buttonCallBack = (ItemButtonCallBackInterface) activity;
        View v = minflater.inflate(R.layout.single_recipe_list_item,null);
        ImageButton deleteButton = (ImageButton) v.findViewById(R.id.delete_recipe);
        TextView recipeTitle = (TextView) v.findViewById(R.id.textView2);

        MealplanRecipe item = getItem(position);
        final int day = item.getDayNum();
        final int dayPeriod = item.getPlanType();
        final String recipeName = item.getRecipeName();
        recipeTitle.setText(recipeName);

        deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                buttonCallBack.onDeleteRecipe(day,dayPeriod,recipeName);
            }
        });

        return v;
    }
}
