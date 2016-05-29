
package com.ayoka.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportValue {

    private List<ReportColumn> ReportColumns = new ArrayList<ReportColumn>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The ReportColumns
     */
    public List<ReportColumn> getReportColumns() {
        return ReportColumns;
    }

    /**
     *
     * @param ReportColumns
     * The ReportColumns
     */
    public void setReportColumns(List<ReportColumn> ReportColumns) {
        this.ReportColumns = ReportColumns;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}