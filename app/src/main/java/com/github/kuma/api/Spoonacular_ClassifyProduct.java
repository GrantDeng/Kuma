package com.github.kuma.api;

import com.github.kuma.api.api_data.ClassifiedProduct;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Support for classifying a grocery with Spoonacular.
 */
public interface Spoonacular_ClassifyProduct
{
    @Headers(
        "X-Mashape-Key: 9cbhlo1UzpmshuBjs1ZpuDiUhWCFp1tiWnujsn5pVwsZTtNhG7"
    )
    @POST("food/products/classify")
    Call<ClassifiedProduct> classify(
        @Body ClassificationRequest classificationRequest
    );

    class ClassificationRequest
    {
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("upc")
        @Expose
        private String upc;
        @SerializedName("plu_code")
        @Expose
        private String pluCode;

        /**
         *
         * @return
         * The title
         */
        public String getTitle()
        {
            return title;
        }

        /**
         *
         * @param title
         * The title
         */
        public void setTitle(String title)
        {
            this.title = title;
        }

        /**
         *
         * @return
         * The upc
         */
        public String getUpc()
        {
            return upc;
        }

        /**
         *
         * @param upc
         * The upc
         */
        public void setUpc(String upc)
        {
            this.upc = upc;
        }

        /**
         *
         * @return
         * The pluCode
         */
        public String getPluCode()
        {
            return pluCode;
        }

        /**
         *
         * @param pluCode
         * The plu_code
         */
        public void setPluCode(String pluCode)
        {
            this.pluCode = pluCode;
        }
    }
}
