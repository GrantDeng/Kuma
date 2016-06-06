package com.github.kuma.grocerymanager;

import android.os.Bundle;
import android.gesture.GestureOverlayView;
import android.support.v7.app.ActionBarActivity;

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

        final Call<List<Github>> result = Spoonacular_api.Spoonacular_service.getIstance().list("square", "retrofit");




        result.enqueue(new Callback<List<Github>>()
        {
            @Override
            public void onResponse(Call<List<Github>> call, Response<List<Github>> response)
            {

                if (response.body() == null){
                    System.err.println("null!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }
                else {
                    System.err.println("not null!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }

                System.err.println(response.errorBody().toString());
                System.err.println(response.code());
                System.err.println(response.isSuccessful());
                System.err.println(response.message());

                System.err.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Github>> call, Throwable t)
            {
                System.err.println("error");
            }
        });


        //System.err.println("woooooooooooooooo!!!!!!!!!!!!!!!!!!!!!!!!");
        //System.err.println(result.toString());
    }
}
