
package com.github.kuma.api.api_data;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeInformation {

    @SerializedName("vegetarian")
    @Expose
    private boolean vegetarian;
    @SerializedName("vegan")
    @Expose
    private boolean vegan;
    @SerializedName("glutenFree")
    @Expose
    private boolean glutenFree;
    @SerializedName("dairyFree")
    @Expose
    private boolean dairyFree;
    @SerializedName("veryHealthy")
    @Expose
    private boolean veryHealthy;
    @SerializedName("cheap")
    @Expose
    private boolean cheap;
    @SerializedName("veryPopular")
    @Expose
    private boolean veryPopular;
    @SerializedName("sustainable")
    @Expose
    private boolean sustainable;
    @SerializedName("weightWatcherSmartPoints")
    @Expose
    private int weightWatcherSmartPoints;
    @SerializedName("gaps")
    @Expose
    private String gaps;
    @SerializedName("lowFodmap")
    @Expose
    private boolean lowFodmap;
    @SerializedName("ketogenic")
    @Expose
    private boolean ketogenic;
    @SerializedName("whole30")
    @Expose
    private boolean whole30;
    @SerializedName("servings")
    @Expose
    private int servings;
    @SerializedName("sourceUrl")
    @Expose
    private String sourceUrl;
    @SerializedName("spoonacularSourceUrl")
    @Expose
    private String spoonacularSourceUrl;
    @SerializedName("aggregateLikes")
    @Expose
    private int aggregateLikes;
    @SerializedName("creditText")
    @Expose
    private String creditText;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("sourceName")
    @Expose
    private String sourceName;
    @SerializedName("extendedIngredients")
    @Expose
    private List<ExtendedIngredient> extendedIngredients = new ArrayList<ExtendedIngredient>();
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("readyInMinutes")
    @Expose
    private int readyInMinutes;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("instructions")
    @Expose
    private Object instructions;

    /**
     * 
     * @return
     *     The vegetarian
     */
    public boolean isVegetarian() {
        return vegetarian;
    }

    /**
     * 
     * @param vegetarian
     *     The vegetarian
     */
    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    /**
     * 
     * @return
     *     The vegan
     */
    public boolean isVegan() {
        return vegan;
    }

    /**
     * 
     * @param vegan
     *     The vegan
     */
    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    /**
     * 
     * @return
     *     The glutenFree
     */
    public boolean isGlutenFree() {
        return glutenFree;
    }

    /**
     * 
     * @param glutenFree
     *     The glutenFree
     */
    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    /**
     * 
     * @return
     *     The dairyFree
     */
    public boolean isDairyFree() {
        return dairyFree;
    }

    /**
     * 
     * @param dairyFree
     *     The dairyFree
     */
    public void setDairyFree(boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    /**
     * 
     * @return
     *     The veryHealthy
     */
    public boolean isVeryHealthy() {
        return veryHealthy;
    }

    /**
     * 
     * @param veryHealthy
     *     The veryHealthy
     */
    public void setVeryHealthy(boolean veryHealthy) {
        this.veryHealthy = veryHealthy;
    }

    /**
     * 
     * @return
     *     The cheap
     */
    public boolean isCheap() {
        return cheap;
    }

    /**
     * 
     * @param cheap
     *     The cheap
     */
    public void setCheap(boolean cheap) {
        this.cheap = cheap;
    }

    /**
     * 
     * @return
     *     The veryPopular
     */
    public boolean isVeryPopular() {
        return veryPopular;
    }

    /**
     * 
     * @param veryPopular
     *     The veryPopular
     */
    public void setVeryPopular(boolean veryPopular) {
        this.veryPopular = veryPopular;
    }

    /**
     * 
     * @return
     *     The sustainable
     */
    public boolean isSustainable() {
        return sustainable;
    }

    /**
     * 
     * @param sustainable
     *     The sustainable
     */
    public void setSustainable(boolean sustainable) {
        this.sustainable = sustainable;
    }

    /**
     * 
     * @return
     *     The weightWatcherSmartPoints
     */
    public int getWeightWatcherSmartPoints() {
        return weightWatcherSmartPoints;
    }

    /**
     * 
     * @param weightWatcherSmartPoints
     *     The weightWatcherSmartPoints
     */
    public void setWeightWatcherSmartPoints(int weightWatcherSmartPoints) {
        this.weightWatcherSmartPoints = weightWatcherSmartPoints;
    }

    /**
     * 
     * @return
     *     The gaps
     */
    public String getGaps() {
        return gaps;
    }

    /**
     * 
     * @param gaps
     *     The gaps
     */
    public void setGaps(String gaps) {
        this.gaps = gaps;
    }

    /**
     * 
     * @return
     *     The lowFodmap
     */
    public boolean isLowFodmap() {
        return lowFodmap;
    }

    /**
     * 
     * @param lowFodmap
     *     The lowFodmap
     */
    public void setLowFodmap(boolean lowFodmap) {
        this.lowFodmap = lowFodmap;
    }

    /**
     * 
     * @return
     *     The ketogenic
     */
    public boolean isKetogenic() {
        return ketogenic;
    }

    /**
     * 
     * @param ketogenic
     *     The ketogenic
     */
    public void setKetogenic(boolean ketogenic) {
        this.ketogenic = ketogenic;
    }

    /**
     * 
     * @return
     *     The whole30
     */
    public boolean isWhole30() {
        return whole30;
    }

    /**
     * 
     * @param whole30
     *     The whole30
     */
    public void setWhole30(boolean whole30) {
        this.whole30 = whole30;
    }

    /**
     * 
     * @return
     *     The servings
     */
    public int getServings() {
        return servings;
    }

    /**
     * 
     * @param servings
     *     The servings
     */
    public void setServings(int servings) {
        this.servings = servings;
    }

    /**
     * 
     * @return
     *     The sourceUrl
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     * 
     * @param sourceUrl
     *     The sourceUrl
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    /**
     * 
     * @return
     *     The spoonacularSourceUrl
     */
    public String getSpoonacularSourceUrl() {
        return spoonacularSourceUrl;
    }

    /**
     * 
     * @param spoonacularSourceUrl
     *     The spoonacularSourceUrl
     */
    public void setSpoonacularSourceUrl(String spoonacularSourceUrl) {
        this.spoonacularSourceUrl = spoonacularSourceUrl;
    }

    /**
     * 
     * @return
     *     The aggregateLikes
     */
    public int getAggregateLikes() {
        return aggregateLikes;
    }

    /**
     * 
     * @param aggregateLikes
     *     The aggregateLikes
     */
    public void setAggregateLikes(int aggregateLikes) {
        this.aggregateLikes = aggregateLikes;
    }

    /**
     * 
     * @return
     *     The creditText
     */
    public String getCreditText() {
        return creditText;
    }

    /**
     * 
     * @param creditText
     *     The creditText
     */
    public void setCreditText(String creditText) {
        this.creditText = creditText;
    }

    /**
     * 
     * @return
     *     The license
     */
    public String getLicense() {
        return license;
    }

    /**
     * 
     * @param license
     *     The license
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * 
     * @return
     *     The sourceName
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * 
     * @param sourceName
     *     The sourceName
     */
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    /**
     * 
     * @return
     *     The extendedIngredients
     */
    public List<ExtendedIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    /**
     * 
     * @param extendedIngredients
     *     The extendedIngredients
     */
    public void setExtendedIngredients(List<ExtendedIngredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    /**
     * 
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The readyInMinutes
     */
    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    /**
     * 
     * @param readyInMinutes
     *     The readyInMinutes
     */
    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The instructions
     */
    public Object getInstructions() {
        return instructions;
    }

    /**
     * 
     * @param instructions
     *     The instructions
     */
    public void setInstructions(Object instructions) {
        this.instructions = instructions;
    }

}
