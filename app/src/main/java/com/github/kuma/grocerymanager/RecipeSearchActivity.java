package com.github.kuma.grocerymanager;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecipeSearchActivity extends BaseActivity
{
    private final String pageTitle = "Recipe Search";
    private TextView pageTitleTextView;
    private int day;
    private int period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_search);

        // set current page and previous page
        Intent intent = getIntent();
        setCurPage("recipe_search");
        setPrevPage(intent.getStringExtra("prevPage"));

        /**
         * getIntExtra returns Integers
         * whichDay - 0:Today , 1:Tomorrow , 2:Day3 , 3:Day4 etc.
         * whichPeriod - 1:breakfast , 2:lunch , 3:dinner
         * Exception: -1 means you are receiving null Extra from meal planner
         */
        day = intent.getIntExtra("whichDay",-1);
        period = intent.getIntExtra("whichPeriod",-1);
        System.err.println("day = " + day + " period = " + period);

        // set buttons
        setButtons();
        // set button listeners
        setButtonListener(this);

        // set page title
        pageTitleTextView = (TextView) findViewById(R.id.page_title);
        pageTitleTextView.setText(pageTitle);


    }
}
