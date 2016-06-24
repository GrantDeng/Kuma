package com.github.kuma.grocerymanager;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.github.kuma.api.api_data.*;

import java.io.IOException;

import com.github.kuma.api.Spoonacular_getdata;
public class MainActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.err.println("in main ativity");

        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                System.err.println("into the new thread");
                Spoonacular_getdata sp = new Spoonacular_getdata();


                SearchRecipes a = sp.SearchRecipes("beef", "main course");

                System.err.println(a.getNumber());
                System.err.println(a.getProcessingTimeMs());
                System.err.println(a.getResults().size());


                Searchby_Ingredients b = sp.Searchby_Ingredients("beef, orion");
                System.err.println(b.getId());
                System.err.println(b.getLikes());
                System.err.println(b.getTitle());

                //ComplexSearch e = sp.ComplexRecipes("onions, lettuce, tomato", "burger", "main course");
                //System.err.println(e.getTotalResults());
                //System.err.println("aaaaa");
                //System.err.println(e.getResults().get(0).getTitle());

                //RecipeInformation c = sp.get_recipe_information(b.getId());
                //System.err.println(c.getExtendedIngredients().size());

                //SummarizeRecipe d = sp.get_recipe_summary(a.getResults().get(0).getId());
                //System.err.println(d.getSummary());



            }
        };
        thread.start();

    }
}
