package com.github.kuma.api;

import com.github.kuma.api.api_data.ClassifiedProduct;
import com.github.kuma.api.api_data.ComplexSearch;
import com.github.kuma.api.api_data.RecipeInformation;
import com.github.kuma.api.api_data.SearchRecipes;
import com.github.kuma.api.api_data.Searchby_Ingredients;
import com.github.kuma.api.api_data.SummarizeRecipe;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Spoonacular_getdata
{
    private Retrofit retrofit;
    private static String URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/";
    private static Spoonacular_api service;
    private static Spoonacular_SearchRecipe search_service;
    private static Spoonacular_ClassifyProduct classificationService;

    public Spoonacular_getdata()
    {
        retrofit = new Retrofit.Builder()
                   .baseUrl(URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
        service = retrofit.create(Spoonacular_api.class);
        classificationService = retrofit.create(Spoonacular_ClassifyProduct.class);
    }

    public SummarizeRecipe get_recipe_summary(int id)
    {

        final Call<SummarizeRecipe> result = service.list(id);
        try
        {
            Response<SummarizeRecipe> response = result.execute();
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




    public RecipeInformation get_recipe_information(int id)
    {

        final Call<RecipeInformation> result = service.search_info(id);
        try
        {
            Response<RecipeInformation> response = result.execute();
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

    /**
     * Search recipes by name and type of meal
     * type can be : main course, side dish, dessert, appetizer,
     * salad, bread, breakfast, soup, beverage, sauce, or drink.
     */

    public SearchRecipes SearchRecipes(String query, String type)
    {
        final Call<SearchRecipes> result = service.search(query, type);

        try
        {
            Response<SearchRecipes> response = result.execute();
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

    // search recipes by ingredient
    public Searchby_Ingredients Searchby_Ingredients(String ingra)
    {
        final Call<List<Searchby_Ingredients>> result = service.search_by_in(ingra);

        try
        {
            Response<List<Searchby_Ingredients>> response = result.execute();
            return response.body().get(0);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("in error");
            System.err.println(e.toString());
        }
        return null;
    }

    /**
     * Complex search
     *  ingradient is a string contain the require ingredient
     * type can be : main course, side dish, dessert, appetizer,
     * salad, bread, breakfast, soup, beverage, sauce, or drink.
     */
    public ComplexSearch ComplexRecipes(String ingredient, String query, String type)
    {
        final Call<ComplexSearch> result = service.complex_search(true, ingredient, false, 5, 0, query, 2, type);

        try
        {
            Response<ComplexSearch> response = result.execute();
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

    /**
     * Classify the given product using the Spoonacular API.
     * @param product The product to classify.
     * @return The result of the classification request, or null on failure.
     */
    public ClassifiedProduct classifyProduct(Spoonacular_ClassifyProduct.ClassificationRequest product)
    {
        final Call<ClassifiedProduct> result = classificationService.classify(product);

        try
        {
            Response<ClassifiedProduct> response = result.execute();
            ClassifiedProduct body = response.body();
            if(body == null)
            {
                ResponseBody errorBody = response.errorBody();
                System.err.println(errorBody.string());
            }
            return response.body();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
