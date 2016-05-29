package com.github.kuma.data.spoonacular;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpoonacularApiTest
{
    private SpoonacularApi spoonacular;

    @Before
    public void setUp()
    {
        this.spoonacular = new Retrofit.Builder()
            .baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpoonacularApi.class);
    }

    @Test
    public void testClassify() throws java.io.IOException
    {
        SpoonacularApi.SpoonacularClassifyRequest request = new SpoonacularApi.SpoonacularClassifyRequest();
        request.title = "Skippy Peanut Butter";
        Call<SpoonacularApi.SpoonacularClassifiedProduct> call = this.spoonacular.classify(request);
        Response<SpoonacularApi.SpoonacularClassifiedProduct> response = call.execute();
        assertEquals(response.code(), 200);
        SpoonacularApi.SpoonacularClassifiedProduct product = response.body();
        assertEquals(product.cleanTitle, "Skippy Peanut Butter");
        assertEquals(product.category, "peanut_butter");
    }
}
