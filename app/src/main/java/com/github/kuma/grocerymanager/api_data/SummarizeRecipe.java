
package com.github.kuma.grocerymanager.api_data;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("org.jsonschema2pojo")
public class SummarizeRecipe
{

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("summary")
    @Expose
    private String summary;

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
     *     The summary
     */
    public String getSummary()
    {
        return summary;
    }

    /**
     *
     * @param summary
     *     The summary
     */
    public void setSummary(String summary)
    {
        this.summary = summary;
    }

}
