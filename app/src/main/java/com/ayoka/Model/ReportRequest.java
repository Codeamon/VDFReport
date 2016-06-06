package com.ayoka.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportRequest implements Serializable {

    @SerializedName("ReportMainId")
    @Expose
    private Integer ReportMainId;
    @SerializedName("FilterList")
    @Expose
    private List<com.ayoka.Model.FilterList> FilterList = new ArrayList<com.ayoka.Model.FilterList>();

    /**
     *
     * @return
     * The ReportMainId
     */
    public Integer getReportMainId() {
        return ReportMainId;
    }

    /**
     *
     * @param ReportMainId
     * The ReportMainId
     */
    public void setReportMainId(Integer ReportMainId) {
        this.ReportMainId = ReportMainId;
    }

    /**
     *
     * @return
     * The FilterList
     */
    public List<com.ayoka.Model.FilterList> getFilterList() {
        return FilterList;
    }

    /**
     *
     * @param FilterList
     * The FilterList
     */
    public void setFilterList(List<com.ayoka.Model.FilterList> FilterList) {
        this.FilterList = FilterList;
    }

}