package com.github.kuma.api;

import com.github.kuma.api.api_data.NutritionixData;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Support for looking up a barcode with Nutritionix.
 */
interface NutritionixUpcLookupInterface
{
    @GET("item?appKey=ef0f1bb66057e525db2ec2d5378c2577&appId=cee34f04")
    Call<NutritionixData> lookup(@Query("upc") String upc);
}

// FIXME: a lot of this should be static
public class Nutritionix_UpcLookup
{
    private Retrofit retrofit;
    private NutritionixUpcLookupInterface service;

    public Nutritionix_UpcLookup()
    {
        this.retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nutritionix.com/v1_1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        this.service = retrofit.create(NutritionixUpcLookupInterface.class);
    }

    /**
     * Classify the given product using the Spoonacular API.
     * @param upc The product to classify.
     * @return The result of the lookup request, or null on failure.
     */
    public NutritionixData searchByUpc(String upc)
    {
        final Call<NutritionixData> result = service.lookup(upc);

        try
        {
            Response<NutritionixData> response = result.execute();
            NutritionixData data = response.body();
            if(data == null)
            {
                System.err.println(response.errorBody().string());
                return null;
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
