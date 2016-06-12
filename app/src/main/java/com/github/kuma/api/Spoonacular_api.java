package com.github.kuma.api;

/**
 * Created by Grant on 2016-06-05.
 */

import com.github.kuma.api.api_data.SearchRecipes;
import com.github.kuma.api.api_data.SummarizeRecipe;
import retrofit2.http.*;
import retrofit2.Call;

import java.lang.*;

public interface Spoonacular_api
{
    // for calling summary of recipe: require id
    @Headers("X-Mashape-Key: 9cbhlo1UzpmshuBjs1ZpuDiUhWCFp1tiWnujsn5pVwsZTtNhG7")
    @GET("recipes/{id}/summary")
    Call<SummarizeRecipe> list (@Path("id") int id);

    // for search recipes
    @Headers("X-Mashape-Key: 9cbhlo1UzpmshuBjs1ZpuDiUhWCFp1tiWnujsn5pVwsZTtNhG7")
    @GET("recipes/search?query=burger?")
    Call<SearchRecipes> search(
            @Query("query") String query
    );
}
