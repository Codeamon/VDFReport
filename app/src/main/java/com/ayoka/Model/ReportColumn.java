
package com.ayoka.Model;

import java.util.HashMap;
import java.util.Map;

public class ReportColumn {

    private String ColumnName;
    private String ColumnValue;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The ColumnName
     */
    public String getColumnName() {
        return ColumnName;
    }

    /**
     *
     * @param ColumnName
     * The ColumnName
     */
    public void setColumnName(String ColumnName) {
        this.ColumnName = ColumnName;
    }

    /**
     *
     * @return
     * The ColumnValue
     */
    public String getColumnValue() {
        return ColumnValue;
    }

    /**
     *
     * @param ColumnValue
     * The ColumnValue
     */
    public void setColumnValue(String ColumnValue) {
        this.ColumnValue = ColumnValue;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}