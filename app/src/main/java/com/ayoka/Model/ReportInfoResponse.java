package com.ayoka.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ReportInfoResponse implements Serializable {

    @SerializedName("ReportMainId")
    @Expose
    private Integer reportMainId;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("ReportFilters")
    @Expose
    private List<ReportFilter> reportFilters = new ArrayList<ReportFilter>();

    /**
     *
     * @return
     * The reportMainId
     */
    public Integer getReportMainId() {
        return reportMainId;
    }

    /**
     *
     * @param reportMainId
     * The ReportMainId
     */
    public void setReportMainId(Integer reportMainId) {
        this.reportMainId = reportMainId;
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
     * The Title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The reportFilters
     */
    public List<ReportFilter> getReportFilters() {
        return reportFilters;
    }

    /**
     *
     * @param reportFilters
     * The ReportFilters
     */
    public void setReportFilters(List<ReportFilter> reportFilters) {
        this.reportFilters = reportFilters;
    }

}