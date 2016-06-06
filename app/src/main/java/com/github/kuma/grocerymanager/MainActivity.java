package com.github.kuma.grocerymanager;

import android.app.DownloadManager;
import android.gesture.GestureOverlayView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import com.github.kuma.grocerymanager.api.Spoonacular_api;
import com.github.kuma.grocerymanager.api_data.Recipe_detail_model;
import com.github.kuma.grocerymanager.api_data.SummarizeRecipe;
import com.github.kuma.grocerymanager.api_github.Github;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import java.util.List;
import java.lang.Object;
import java.io.*;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryRow;
import com.couchbase.lite.View;
import com.github.kuma.data.db.DbDocument;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/")
            .build();*/


        //Spoonacular_api service = Spoonacular_api.retrofit.create(Spoonacular_api.class);

        final Call<SummarizeRecipe> result = Spoonacular_api.Spoonacular_service.getIstance().list(4632);




        result.enqueue(new Callback<SummarizeRecipe>()
        {
            @Override
            public void onResponse(Call<SummarizeRecipe> call, Response<SummarizeRecipe> response)
            {

                if (response.body() == null){
                    System.err.println("null!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }
                else {
                    System.err.println("not null!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }

                //System.err.println(response.errorBody().toString());
                System.err.println(response.code());
                System.err.println(response.isSuccessful());
                System.err.println(response.message());
                //Query query = response.body().getQuery();
                System.err.println(response.body().getId());
                System.err.println(response.body().getSummary());

            }

            @Override
            public void onFailure(Call<SummarizeRecipe> call, Throwable t)
            {
                System.err.println("error");
            }
        });


        //System.err.println("woooooooooooooooo!!!!!!!!!!!!!!!!!!!!!!!!");
        //System.err.println(result.toString());
    }
}
