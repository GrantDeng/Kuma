
package com.github.kuma.api.api_data;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchRecipes
{
    @SerializedName("results")
    @Expose
    private List<Search_Result> results = new ArrayList<Search_Result>();
    @SerializedName("baseUri")
    @Expose
    private String baseUri;
    @SerializedName("offset")
    @Expose
    private int offset;
    @SerializedName("number")
    @Expose
    private int number;
    @SerializedName("totalResults")
    @Expose
    private int totalResults;
    @SerializedName("processingTimeMs")
    @Expose
    private int processingTimeMs;
    @SerializedName("expires")
    @Expose
    private String expires;
    @SerializedName("isStale")
    @Expose
    private boolean isStale;

    /**
     *
     * @return
     *     The results
     */
    public List<Search_Result> getResults()
    {
        return results;
    }

    /**
     *
     * @param results
     *     The results
     */
    public void setResults(List<Search_Result> results)
    {
        this.results = results;
    }

    /**
     *
     * @return
     *     The baseUri
     */
    public String getBaseUri()
    {
        return baseUri;
    }

    /**
     *
     * @param baseUri
     *     The baseUri
     */
    public void setBaseUri(String baseUri)
    {
        this.baseUri = baseUri;
    }

    /**
     *
     * @return
     *     The offset
     */
    public int getOffset()
    {
        return offset;
    }

    /**
     *
     * @param offset
     *     The offset
     */
    public void setOffset(int offset)
    {
        this.offset = offset;
    }

    /**
     *
     * @return
     *     The number
     */
    public int getNumber()
    {
        return number;
    }

    /**
     *
     * @param number
     *     The number
     */
    public void setNumber(int number)
    {
        this.number = number;
    }

    /**
     *
     * @return
     *     The totalResults
     */
    public int getTotalResults()
    {
        return totalResults;
    }

    /**
     *
     * @param totalResults
     *     The totalResults
     */
    public void setTotalResults(int totalResults)
    {
        this.totalResults = totalResults;
    }

    /**
     *
     * @return
     *     The processingTimeMs
     */
    public int getProcessingTimeMs()
    {
        return processingTimeMs;
    }

    /**
     *
     * @param processingTimeMs
     *     The processingTimeMs
     */
    public void setProcessingTimeMs(int processingTimeMs)
    {
        this.processingTimeMs = processingTimeMs;
    }

    /**
     *
     * @return
     *     The expires
     */
    public String getExpires()
    {
        return expires;
    }

    /**
     *
     * @param expires
     *     The expires
     */
    public void setExpires(String expires)
    {
        this.expires = expires;
    }

    /**
     *
     * @return
     *     The isStale
     */
    public boolean isIsStale()
    {
        return isStale;
    }

    /**
     *
     * @param isStale
     *     The isStale
     */
    public void setIsStale(boolean isStale)
    {
        this.isStale = isStale;
    }
}
