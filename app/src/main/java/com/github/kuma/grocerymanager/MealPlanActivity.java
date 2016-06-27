package com.github.kuma.grocerymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.kuma.data.db.SimpleDbInterface;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MealPlanActivity extends BaseActivity
{
    private final String pageTitle = "Meal Planner";
    private TextView pageTitleTextView;
    private LinearLayout contentVerticalLayout;
    private List<LinearLayout> list_of_dailylayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_planer);

        // set current page and previous page
        Intent intent = getIntent();
        setCurPage("mealplan");
        setPrevPage(intent.getStringExtra("prevPage"));

        // set buttons
        setButtons();
        // set button listeners
        setButtonListener(this);

        // set page title
        pageTitleTextView = (TextView)findViewById(R.id.page_title);
        pageTitleTextView.setText(pageTitle);

        // set daily headers, day periods, initialize list_of_dailylayout
        contentVerticalLayout = (LinearLayout)findViewById(R.id.mealPlanLinearLayout);
        list_of_dailylayout = new ArrayList<LinearLayout>();
        setDailyHeadersAndPeriods();

        // setup data handler
        try{
            MealplanDataHandler mealplan_dh = new MealplanDataHandler(getApplicationContext());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private void setDailyHeadersAndPeriods()
    {
        for(int i = 0; i < contentVerticalLayout.getChildCount(); i++)
        {
            View dailyView = contentVerticalLayout.getChildAt(i);
            LinearLayout dailyLayout = (LinearLayout)contentVerticalLayout.getChildAt(i);
            list_of_dailylayout.add(dailyLayout);
            TextView header = (TextView) dailyView.findViewById(R.id.ListHeader);

            if(i == 0)
            {
                header.setText("Today");
            }
            else if(i == 1)
            {
                header.setText("Tomorrow");
            }
            else
            {
                header.setText("Day " + (i+1));
            }

            // set periods text
            for(int j = 0; j < dailyLayout.getChildCount(); j++)
            {
                View v = dailyLayout.getChildAt(j);
                TextView dayPeriod = (TextView) v.findViewById(R.id.day_period);
                switch (j)
                {
                    case 0:
                        break;
                    case 1:
                        dayPeriod.setText("Breakfast");
                        break;
                    case 2:
                        dayPeriod.setText("Lunch");
                        break;
                    case 3:
                        dayPeriod.setText("Dinner");
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
