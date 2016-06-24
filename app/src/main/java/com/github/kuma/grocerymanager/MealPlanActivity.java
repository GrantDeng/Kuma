package com.github.kuma.grocerymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.github.kuma.data.db.DbUtils;

/**
 *
 */
public class MealPlanActivity extends BaseActivity
{
    private final String pageTitle = "Meal Planner";
    private TextView pageTitleTextView;

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

        try{
            DbUtils.deleteDB(getApplicationContext());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
