package com.github.kuma.api;

/**
 * Created by Grant on 2016-06-05.
 */

import com.github.kuma.api.api_data.ComplexSearch;
import com.github.kuma.api.api_data.RecipeInformation;
import com.github.kuma.api.api_data.SearchRecipes;
import com.github.kuma.api.api_data.Searchby_Ingredients;
import com.github.kuma.api.api_data.SummarizeRecipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Spoonacular_api
{
    // for calling summary of recipe: require id
    @Headers("X-Mashape-Key: 9cbhlo1UzpmshuBjs1ZpuDiUhWCFp1tiWnujsn5pVwsZTtNhG7")
    @GET("recipes/{id}/summary")
    Call<SummarizeRecipe> list (@Path("id") int id);

    // for search recipes
    @Headers("X-Mashape-Key: 9cbhlo1UzpmshuBjs1ZpuDiUhWCFp1tiWnujsn5pVwsZTtNhG7")
    @GET("recipes/search")
    Call<SearchRecipes> search(
            @Query("query") String query,
            @Query("type") String type
    );

    // search by Ingredients
    @Headers({"X-Mashape-Key: 9cbhlo1UzpmshuBjs1ZpuDiUhWCFp1tiWnujsn5pVwsZTtNhG7",
              "Accept: application/json"})
    @GET("recipes/findByIngredients")
    Call<List<Searchby_Ingredients>> search_by_in(
            @Query("ingredients") String ingredients
    );

    // for calling information of recipe: require id
    @Headers("X-Mashape-Key: 9cbhlo1UzpmshuBjs1ZpuDiUhWCFp1tiWnujsn5pVwsZTtNhG7")
    @GET("recipes/{id}/information")
    Call<RecipeInformation> search_info (@Path("id") int id);

    // complex search
    @Headers("X-Mashape-Key: 9cbhlo1UzpmshuBjs1ZpuDiUhWCFp1tiWnujsn5pVwsZTtNhG7")
    @GET("recipes/searchComplex")
    Call<ComplexSearch> complex_search(
            @Query("fillIngredients") boolean fillIngredients,
            @Query("includeIngredients") String includeIngredients,
            @Query("limitLicense") boolean limitLicense,
            @Query("number") int number,
            @Query("offset") int offset,
            @Query("query") String query,
            @Query("ranking") int ranking,
            @Query("type") String type
    );
}
