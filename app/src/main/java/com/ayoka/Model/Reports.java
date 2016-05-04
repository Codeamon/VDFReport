package com.ayoka.Model;

import java.util.Date;

/**
 * Created by OsmanKorcan on 24.4.2016.
 */
public class Reports {

    private String  reportName;
    private int  reportId;
    private int ProjectId;
    private int MainCategoryId;
    private float Value;
    private int TabId;
    private String TypeValue;

    public Reports(String reportName,int reportId, int projectId, int mainCategoryId, float value, int tabId, String typeValue) {
        this.reportName = reportName;
        this.reportId= reportId;
        ProjectId = projectId;
        MainCategoryId = mainCategoryId;
        Value = value;
        TabId = tabId;
        TypeValue = typeValue;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public int getMainCategoryId() {
        return MainCategoryId;
    }

    public void setMainCategoryId(int mainCategoryId) {
        MainCategoryId = mainCategoryId;
    }

    public float getValue() {
        return Value;
    }

    public void setValue(float value) {
        Value = value;
    }

    public int getTabId() {
        return TabId;
    }

    public void setTabId(int tabId) {
        TabId = tabId;
    }

    public String getTypeValue() {
        return TypeValue;
    }

    public void setTypeValue(String typeValue) {
        TypeValue = typeValue;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
}


