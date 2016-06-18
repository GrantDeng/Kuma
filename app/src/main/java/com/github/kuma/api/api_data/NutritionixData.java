package com.github.kuma.api.api_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NutritionixData
{
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("item_name")
    @Expose
    private String itemName;
    @SerializedName("brand_id")
    @Expose
    private String brandId;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("item_description")
    @Expose
    private String itemDescription;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("nf_ingredient_statement")
    @Expose
    private String nfIngredientStatement;
    @SerializedName("nf_calories")
    @Expose
    private int nfCalories;
    @SerializedName("nf_calories_from_fat")
    @Expose
    private int nfCaloriesFromFat;
    @SerializedName("nf_total_fat")
    @Expose
    private int nfTotalFat;
    @SerializedName("nf_saturated_fat")
    @Expose
    private Object nfSaturatedFat;
    @SerializedName("nf_cholesterol")
    @Expose
    private Object nfCholesterol;
    @SerializedName("nf_sodium")
    @Expose
    private int nfSodium;
    @SerializedName("nf_total_carbohydrate")
    @Expose
    private int nfTotalCarbohydrate;
    @SerializedName("nf_dietary_fiber")
    @Expose
    private Object nfDietaryFiber;
    @SerializedName("nf_sugars")
    @Expose
    private int nfSugars;
    @SerializedName("nf_protein")
    @Expose
    private int nfProtein;
    @SerializedName("nf_vitamin_a_dv")
    @Expose
    private int nfVitaminADv;
    @SerializedName("nf_vitamin_c_dv")
    @Expose
    private int nfVitaminCDv;
    @SerializedName("nf_calcium_dv")
    @Expose
    private int nfCalciumDv;
    @SerializedName("nf_iron_dv")
    @Expose
    private int nfIronDv;
    @SerializedName("nf_servings_per_container")
    @Expose
    private int nfServingsPerContainer;
    @SerializedName("nf_serving_size_qty")
    @Expose
    private int nfServingSizeQty;
    @SerializedName("nf_serving_size_unit")
    @Expose
    private String nfServingSizeUnit;

    /**
     *
     * @return
     * The itemId
     */
    public String getItemId()
    {
        return itemId;
    }

    /**
     *
     * @param itemId
     * The item_id
     */
    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }

    /**
     *
     * @return
     * The itemName
     */
    public String getItemName()
    {
        return itemName;
    }

    /**
     *
     * @param itemName
     * The item_name
     */
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    /**
     *
     * @return
     * The brandId
     */
    public String getBrandId()
    {
        return brandId;
    }

    /**
     *
     * @param brandId
     * The brand_id
     */
    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }

    /**
     *
     * @return
     * The brandName
     */
    public String getBrandName()
    {
        return brandName;
    }

    /**
     *
     * @param brandName
     * The brand_name
     */
    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }

    /**
     *
     * @return
     * The itemDescription
     */
    public String getItemDescription()
    {
        return itemDescription;
    }

    /**
     *
     * @param itemDescription
     * The item_description
     */
    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt()
    {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updated_at
     */
    public void setUpdatedAt(String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     * The nfIngredientStatement
     */
    public String getNfIngredientStatement()
    {
        return nfIngredientStatement;
    }

    /**
     *
     * @param nfIngredientStatement
     * The nf_ingredient_statement
     */
    public void setNfIngredientStatement(String nfIngredientStatement)
    {
        this.nfIngredientStatement = nfIngredientStatement;
    }

    /**
     *
     * @return
     * The nfCalories
     */
    public int getNfCalories()
    {
        return nfCalories;
    }

    /**
     *
     * @param nfCalories
     * The nf_calories
     */
    public void setNfCalories(int nfCalories)
    {
        this.nfCalories = nfCalories;
    }

    /**
     *
     * @return
     * The nfCaloriesFromFat
     */
    public int getNfCaloriesFromFat()
    {
        return nfCaloriesFromFat;
    }

    /**
     *
     * @param nfCaloriesFromFat
     * The nf_calories_from_fat
     */
    public void setNfCaloriesFromFat(int nfCaloriesFromFat)
    {
        this.nfCaloriesFromFat = nfCaloriesFromFat;
    }

    /**
     *
     * @return
     * The nfTotalFat
     */
    public int getNfTotalFat()
    {
        return nfTotalFat;
    }

    /**
     *
     * @param nfTotalFat
     * The nf_total_fat
     */
    public void setNfTotalFat(int nfTotalFat)
    {
        this.nfTotalFat = nfTotalFat;
    }

    /**
     *
     * @return
     * The nfSaturatedFat
     */
    public Object getNfSaturatedFat()
    {
        return nfSaturatedFat;
    }

    /**
     *
     * @param nfSaturatedFat
     * The nf_saturated_fat
     */
    public void setNfSaturatedFat(Object nfSaturatedFat)
    {
        this.nfSaturatedFat = nfSaturatedFat;
    }

    /**
     *
     * @return
     * The nfCholesterol
     */
    public Object getNfCholesterol()
    {
        return nfCholesterol;
    }

    /**
     *
     * @param nfCholesterol
     * The nf_cholesterol
     */
    public void setNfCholesterol(Object nfCholesterol)
    {
        this.nfCholesterol = nfCholesterol;
    }

    /**
     *
     * @return
     * The nfSodium
     */
    public int getNfSodium()
    {
        return nfSodium;
    }

    /**
     *
     * @param nfSodium
     * The nf_sodium
     */
    public void setNfSodium(int nfSodium)
    {
        this.nfSodium = nfSodium;
    }

    /**
     *
     * @return
     * The nfTotalCarbohydrate
     */
    public int getNfTotalCarbohydrate()
    {
        return nfTotalCarbohydrate;
    }

    /**
     *
     * @param nfTotalCarbohydrate
     * The nf_total_carbohydrate
     */
    public void setNfTotalCarbohydrate(int nfTotalCarbohydrate)
    {
        this.nfTotalCarbohydrate = nfTotalCarbohydrate;
    }

    /**
     *
     * @return
     * The nfDietaryFiber
     */
    public Object getNfDietaryFiber()
    {
        return nfDietaryFiber;
    }

    /**
     *
     * @param nfDietaryFiber
     * The nf_dietary_fiber
     */
    public void setNfDietaryFiber(Object nfDietaryFiber)
    {
        this.nfDietaryFiber = nfDietaryFiber;
    }

    /**
     *
     * @return
     * The nfSugars
     */
    public int getNfSugars()
    {
        return nfSugars;
    }

    /**
     *
     * @param nfSugars
     * The nf_sugars
     */
    public void setNfSugars(int nfSugars)
    {
        this.nfSugars = nfSugars;
    }

    /**
     *
     * @return
     * The nfProtein
     */
    public int getNfProtein()
    {
        return nfProtein;
    }

    /**
     *
     * @param nfProtein
     * The nf_protein
     */
    public void setNfProtein(int nfProtein)
    {
        this.nfProtein = nfProtein;
    }

    /**
     *
     * @return
     * The nfVitaminADv
     */
    public int getNfVitaminADv()
    {
        return nfVitaminADv;
    }

    /**
     *
     * @param nfVitaminADv
     * The nf_vitamin_a_dv
     */
    public void setNfVitaminADv(int nfVitaminADv)
    {
        this.nfVitaminADv = nfVitaminADv;
    }

    /**
     *
     * @return
     * The nfVitaminCDv
     */
    public int getNfVitaminCDv()
    {
        return nfVitaminCDv;
    }

    /**
     *
     * @param nfVitaminCDv
     * The nf_vitamin_c_dv
     */
    public void setNfVitaminCDv(int nfVitaminCDv)
    {
        this.nfVitaminCDv = nfVitaminCDv;
    }

    /**
     *
     * @return
     * The nfCalciumDv
     */
    public int getNfCalciumDv()
    {
        return nfCalciumDv;
    }

    /**
     *
     * @param nfCalciumDv
     * The nf_calcium_dv
     */
    public void setNfCalciumDv(int nfCalciumDv)
    {
        this.nfCalciumDv = nfCalciumDv;
    }

    /**
     *
     * @return
     * The nfIronDv
     */
    public int getNfIronDv()
    {
        return nfIronDv;
    }

    /**
     *
     * @param nfIronDv
     * The nf_iron_dv
     */
    public void setNfIronDv(int nfIronDv)
    {
        this.nfIronDv = nfIronDv;
    }

    /**
     *
     * @return
     * The nfServingsPerContainer
     */
    public int getNfServingsPerContainer()
    {
        return nfServingsPerContainer;
    }

    /**
     *
     * @param nfServingsPerContainer
     * The nf_servings_per_container
     */
    public void setNfServingsPerContainer(int nfServingsPerContainer)
    {
        this.nfServingsPerContainer = nfServingsPerContainer;
    }

    /**
     *
     * @return
     * The nfServingSizeQty
     */
    public int getNfServingSizeQty()
    {
        return nfServingSizeQty;
    }

    /**
     *
     * @param nfServingSizeQty
     * The nf_serving_size_qty
     */
    public void setNfServingSizeQty(int nfServingSizeQty)
    {
        this.nfServingSizeQty = nfServingSizeQty;
    }

    /**
     *
     * @return
     * The nfServingSizeUnit
     */
    public String getNfServingSizeUnit()
    {
        return nfServingSizeUnit;
    }

    /**
     *
     * @param nfServingSizeUnit
     * The nf_serving_size_unit
     */
    public void setNfServingSizeUnit(String nfServingSizeUnit)
    {
        this.nfServingSizeUnit = nfServingSizeUnit;
    }
}
