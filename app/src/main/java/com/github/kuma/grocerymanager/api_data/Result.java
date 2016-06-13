
package com.github.kuma.grocerymanager.api_data;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Result
{

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
    @SerializedName("imageUrls")
    @Expose
    private List<String> imageUrls = new ArrayList<String>();

    /**
     *
     * @return
     *     The id
     */
    public int getId()
    {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     *
     * @return
     *     The title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     *
     * @return
     *     The readyInMinutes
     */
    public int getReadyInMinutes()
    {
        return readyInMinutes;
    }

    /**
     *
     * @param readyInMinutes
     *     The readyInMinutes
     */
    public void setReadyInMinutes(int readyInMinutes)
    {
        this.readyInMinutes = readyInMinutes;
    }

    /**
     *
     * @return
     *     The image
     */
    public String getImage()
    {
        return image;
    }

    /**
     *
     * @param image
     *     The image
     */
    public void setImage(String image)
    {
        this.image = image;
    }

    /**
     *
     * @return
     *     The imageUrls
     */
    public List<String> getImageUrls()
    {
        return imageUrls;
    }

    /**
     *
     * @param imageUrls
     *     The imageUrls
     */
    public void setImageUrls(List<String> imageUrls)
    {
        this.imageUrls = imageUrls;
    }

}
