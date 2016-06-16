package com.github.kuma.grocerymanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

/**
 *
 */
public abstract class BaseActivity extends Activity
{
    private ImageButton pantry_button;
    private ImageButton shoplist_button;
    private ImageButton mealplan_button;
    private ImageButton input_button;
    private ImageButton setting_button;

    private String curPage;
    private String prevPage;

    public void setCurPage(String page)
    {
        curPage = page;
    }

    public void setPrevPage(String page)
    {
        prevPage = page;
    }

    public void setButtons()
    {
        shoplist_button = (ImageButton)findViewById(R.id.shoplist_button);
        pantry_button = (ImageButton)findViewById((R.id.pantry_button));
        mealplan_button = (ImageButton)findViewById(R.id.plan_button);
        input_button = (ImageButton)findViewById(R.id.input_button);
    }

    public void setButtonListener(final Context context)
    {
        pantry_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(!curPage.equals("pantry"))
                {
                    Intent intent = new Intent(context, PantryActivity.class);
                    intent.putExtra("curPage","pantry");
                    intent.putExtra("prevPage",curPage);
                    startActivity(intent);
                }
            }
        });

        shoplist_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(!curPage.equals("shoplist"))
                {
                    Intent intent = new Intent(context, ShopListActivity.class);
                    intent.putExtra("curPage", "shoplist");
                    intent.putExtra("prevPage", curPage);
                    startActivity(intent);
                }
            }
        });

        mealplan_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(!curPage.equals("mealplan"))
                {
                    Intent intent = new Intent(context, MealPlanActivity.class);
                    intent.putExtra("curPage", "mealplan");
                    intent.putExtra("prevPage", curPage);
                    startActivity(intent);
                }
            }
        });

        input_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View V)
            {
                if(!curPage.equals("input"))
                {
                    Intent intent = new Intent(context, InputActivity.class);
                    intent.putExtra("curPage","input");
                    intent.putExtra("prevPage",curPage);
                    startActivity(intent);
                }
            }
        });
    }
}
