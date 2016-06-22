package com.github.kuma.api.api_data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Searchby_Ingredients {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("imageType")
    @Expose
    private String imageType;
    @SerializedName("usedIngredientCount")
    @Expose
    private int usedIngredientCount;
    @SerializedName("missedIngredientCount")
    @Expose
    private int missedIngredientCount;
    @SerializedName("likes")
    @Expose
    private int likes;

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The imageType
     */
    public String getImageType() {
        return imageType;
    }

    /**
     *
     * @param imageType
     * The imageType
     */
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    /**
     *
     * @return
     * The usedIngredientCount
     */
    public int getUsedIngredientCount() {
        return usedIngredientCount;
    }

    /**
     *
     * @param usedIngredientCount
     * The usedIngredientCount
     */
    public void setUsedIngredientCount(int usedIngredientCount) {
        this.usedIngredientCount = usedIngredientCount;
    }

    /**
     *
     * @return
     * The missedIngredientCount
     */
    public int getMissedIngredientCount() {
        return missedIngredientCount;
    }

    /**
     *
     * @param missedIngredientCount
     * The missedIngredientCount
     */
    public void setMissedIngredientCount(int missedIngredientCount) {
        this.missedIngredientCount = missedIngredientCount;
    }

    /**
     *
     * @return
     * The likes
     */
    public int getLikes() {
        return likes;
    }

    /**
     *
     * @param likes
     * The likes
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

}
