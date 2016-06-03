package com.github.kuma.grocerymanager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity
{
    // TEMPORARY - DO NOT DO THIS LONG TERM
    private final String[] docsNeeded = { "food_data", "pantry" };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TEMPORARY - DO NOT DO THIS LONG TERM

    }
}
