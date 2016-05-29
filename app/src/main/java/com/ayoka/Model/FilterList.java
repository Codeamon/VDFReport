package com.ayoka.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FilterList {

    @SerializedName("FilterName")
    @Expose
    private String FilterName;
    @SerializedName("FilterValue")
    @Expose
    private String FilterValue;

    /**
     *
     * @return
     * The FilterName
     */
    public String getFilterName() {
        return FilterName;
    }

    /**
     *
     * @param FilterName
     * The FilterName
     */
    public void setFilterName(String FilterName) {
        this.FilterName = FilterName;
    }

    /**
     *
     * @return
     * The FilterValue
     */
    public String getFilterValue() {
        return FilterValue;
    }

    /**
     *
     * @param FilterValue
     * The FilterValue
     */
    public void setFilterValue(String FilterValue) {
        this.FilterValue = FilterValue;
    }

}