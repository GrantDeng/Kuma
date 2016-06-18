package com.github.kuma.api.api_data;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * ClassifiedProduct is the result of a Spoonacular grocery classification request.
 */
public class ClassifiedProduct
{
    @SerializedName("breadcrumbs")
    @Expose
    private List<String> breadcrumbs = new ArrayList<String>();
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("cleanTitle")
    @Expose
    private String cleanTitle;
    @SerializedName("usdaCode")
    @Expose
    private Integer usdaCode;

    /**
     *
     * @return
     * The breadcrumbs
     */
    public List<String> getBreadcrumbs()
    {
        return breadcrumbs;
    }

    /**
     *
     * @param breadcrumbs
     * The breadcrumbs
     */
    public void setBreadcrumbs(List<String> breadcrumbs)
    {
        this.breadcrumbs = breadcrumbs;
    }

    /**
     *
     * @return
     * The category
     */
    public String getCategory()
    {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     *
     * @return
     * The cleanTitle
     */
    public String getCleanTitle()
    {
        return cleanTitle;
    }

    /**
     *
     * @param cleanTitle
     * The cleanTitle
     */
    public void setCleanTitle(String cleanTitle)
    {
        this.cleanTitle = cleanTitle;
    }

    /**
     *
     * @return
     * The usdaCode
     */
    public Integer getUsdaCode()
    {
        return usdaCode;
    }

    /**
     *
     * @param usdaCode
     * The usdaCode
     */
    public void setUsdaCode(Integer usdaCode)
    {
        this.usdaCode = usdaCode;
    }
}
