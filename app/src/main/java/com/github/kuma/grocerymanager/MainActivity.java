package com.github.kuma.grocerymanager;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.github.kuma.api.api_data.SearchRecipes;
import com.github.kuma.api.api_data.Searchby_Ingredients;
import com.github.kuma.api.api_data.SummarizeRecipe;
import java.io.IOException;

import com.github.kuma.api.Spoonacular_getdata;
public class MainActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //System.err.println("in main ativity");

        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                //System.err.println("into the new thread");
                Spoonacular_getdata sp = new Spoonacular_getdata();

                SearchRecipes a = sp.SearchRecipes("burger", "main course");
/*
                System.err.println(a.getNumber());
                System.err.println(a.getBaseUri());
                System.err.println(a.getResults().get(0).getImage());

                Searchby_Ingredients b = sp.Searchby_Ingredients("apples,flour,sugar");
                System.err.println(b.getId());*/

            }
        };
        thread.start();

    }
}
