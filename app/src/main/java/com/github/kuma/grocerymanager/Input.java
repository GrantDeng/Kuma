package com.github.kuma.grocerymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 *
 */
public class Input extends BaseActivity
{
    private final String pageTitle = "Input";
    private TextView pageTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input);

        // set current page and previous page
        Intent intent = getIntent();
        setCurPage("input");
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
