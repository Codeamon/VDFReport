
        package com.ayoka.Model;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class ReportResponse {

    private Integer ReportMainId;
    private String Title;
    private List<com.ayoka.Model.ReportList> ReportList = new ArrayList<com.ayoka.Model.ReportList>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     * The Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param Title
     * The Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     *
     * @return
     * The ReportList
     */
    public List<com.ayoka.Model.ReportList> getReportList() {
        return ReportList;
    }

    /**
     *
     * @param ReportList
     * The ReportList
     */
    public void setReportList(List<com.ayoka.Model.ReportList> ReportList) {
        this.ReportList = ReportList;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
