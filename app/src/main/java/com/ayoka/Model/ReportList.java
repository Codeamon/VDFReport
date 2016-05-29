package com.ayoka.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportList {

    private Integer ReportContentId;
    private String Description;
    private Integer ChartType;
    private List<ReportValue> ReportValues = new ArrayList<ReportValue>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The ReportContentId
     */
    public Integer getReportContentId() {
        return ReportContentId;
    }

    /**
     *
     * @param ReportContentId
     * The ReportContentId
     */
    public void setReportContentId(Integer ReportContentId) {
        this.ReportContentId = ReportContentId;
    }

    /**
     *
     * @return
     * The Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     *
     * @param Description
     * The Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     *
     * @return
     * The ChartType
     */
    public Integer getChartType() {
        return ChartType;
    }

    /**
     *
     * @param ChartType
     * The ChartType
     */
    public void setChartType(Integer ChartType) {
        this.ChartType = ChartType;
    }

    /**
     *
     * @return
     * The ReportValues
     */
    public List<ReportValue> getReportValues() {
        return ReportValues;
    }

    /**
     *
     * @param ReportValues
     * The ReportValues
     */
    public void setReportValues(List<ReportValue> ReportValues) {
        this.ReportValues = ReportValues;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}