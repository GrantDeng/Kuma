package com.github.kuma.grocerymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.fasterxml.jackson.databind.deser.Deserializers;

/**
 *
 */
public class PantryActivity extends BaseActivity
{
    private final String pageTitle = "Pantry";
    private TextView pageTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantry);

        // set current page and previous page
        Intent intent = getIntent();
        setCurPage("pantry");
        setPrevPage(intent.getStringExtra("prevPage"));

        // set buttons
        setButtons();
        // set button listeners
        setButtonListener(this);

        // set page title
        pageTitleTextView = (TextView)findViewById(R.id.page_title);
        pageTitleTextView.setText(pageTitle);

    }
}
