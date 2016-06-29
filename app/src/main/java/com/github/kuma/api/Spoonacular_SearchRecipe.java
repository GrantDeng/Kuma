package com.github.kuma.api;

import com.github.kuma.api.api_data.SearchRecipes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Spoonacular_SearchRecipe
{
    @Headers(
            "X-Mashape-Key: 9cbhlo1UzpmshuBjs1ZpuDiUhWCFp1tiWnujsn5pVwsZTtNhG7"
    )
    @GET("recipes/search?query=burger?")
    Call<SearchRecipes> search(
            @Query("query") String query
    );
}

