package com.github.kuma.data.spoonacular;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.Call;

public interface SpoonacularApi
{
    class SpoonacularClassifiedProduct
    {
        public List<String> breadcrumbs;
        public String category;
        public String cleanTitle;
        public int usdaCode;
    }

    class SpoonacularClassifyRequest
    {
        public String title;
        public String upc;
        public String plu_code;
    }

    @Headers("X-MASHAPE-KEY: 9cbhlo1UzpmshuBjs1ZpuDiUhWCFp1tiWnujsn5pVwsZTtNhG7") // FIXME: USE AN OKHTTP INTERCEPTOR
    @POST("food/products/classify")
    Call<SpoonacularClassifiedProduct> classify(@Body SpoonacularClassifyRequest foodToClassify);
}
