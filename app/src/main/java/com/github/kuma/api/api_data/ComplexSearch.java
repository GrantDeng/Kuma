
package com.github.kuma.api.api_data;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComplexSearch {

    @SerializedName("results")
    @Expose
    private List<Complex_search_result> results = new ArrayList<Complex_search_result>();
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

    /**
     * 
     * @return
     *     The results
     */
    public List<Complex_search_result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<Complex_search_result> results) {
        this.results = results;
    }

    /**
     * 
     * @return
     *     The baseUri
     */
    public String getBaseUri() {
        return baseUri;
    }

    /**
     * 
     * @param baseUri
     *     The baseUri
     */
    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    /**
     * 
     * @return
     *     The offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * 
     * @param offset
     *     The offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * 
     * @return
     *     The number
     */
    public int getNumber() {
        return number;
    }

    /**
     * 
     * @param number
     *     The number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 
     * @return
     *     The totalResults
     */
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * 
     * @param totalResults
     *     The totalResults
     */
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * 
     * @return
     *     The processingTimeMs
     */
    public int getProcessingTimeMs() {
        return processingTimeMs;
    }

    /**
     * 
     * @param processingTimeMs
     *     The processingTimeMs
     */
    public void setProcessingTimeMs(int processingTimeMs) {
        this.processingTimeMs = processingTimeMs;
    }

}
