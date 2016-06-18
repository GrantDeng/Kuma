package com.github.kuma.api;

import com.github.kuma.api.api_data.SummarizeRecipe;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.github.kuma.api.api_data.SearchRecipes;
import java.io.IOException;

public class Spoonacular_getdata
{
    private Retrofit retrofit;
    String URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/";
    private static Spoonacular_api service;
    private static Spoonacular_SearchRecipe search_service;

    public Spoonacular_getdata()
    {
        retrofit = new Retrofit.Builder()
                   .baseUrl(URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
        service = retrofit.create(Spoonacular_api.class);
    }

    public SummarizeRecipe get_recipe_summary(int id)
    {

        final Call<SummarizeRecipe> result = service.list(id);
        try
        {
            Response<SummarizeRecipe> response = result.execute();
            System.err.println(response.body().getSummary());
            return response.body();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("in error");
            System.err.println(e.toString());
        }
        return null;
    }

    public SearchRecipes SearchRecipes(String a, String b)
    {
        final Call<SearchRecipes> result = service.search(a);

        try
        {
            Response<SearchRecipes> response = result.execute();
            //return response.body();
            System.err.println(response.body().getBaseUri());
            return response.body();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("in error");
            System.err.println(e.toString());
        }
        return null;
    }
}
