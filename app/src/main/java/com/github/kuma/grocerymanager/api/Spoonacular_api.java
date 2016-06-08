package com.github.kuma.grocerymanager.api;

/**
 * Created by Grant on 2016-06-05.
 */

import com.github.kuma.grocerymanager.api_data.Recipe_detail_model;
import com.github.kuma.grocerymanager.api_data.SummarizeRecipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import java.util.List;
import java.lang.*;

public interface Spoonacular_api
{

    String URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/";


    // for calling summary of recipe: require id
    @Headers(
            "X-Mashape-Key: 9cbhlo1UzpmshuBjs1ZpuDiUhWCFp1tiWnujsn5pVwsZTtNhG7"
    )
    @GET("recipes/{id}/summary")
    Call<SummarizeRecipe> list (
            @Path("id") int id
    );



    public class Spoonacular_service
    {
        private static Spoonacular_api service;

        public static Spoonacular_api getIstance()
        {
            if (service == null)
            {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service = retrofit.create(Spoonacular_api.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
