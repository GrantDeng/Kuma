package com.github.kuma.api;

/**
 * Created by Grant on 2016-06-05.
 */

import com.github.kuma.api.api_data.SearchRecipes;
import com.github.kuma.api.api_data.Searchby_Ingredients;
import com.github.kuma.api.api_data.SummarizeRecipe;
import retrofit2.http.*;
import retrofit2.Call;
import java.util.List;

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
}
