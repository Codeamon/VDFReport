package com.ayoka.common;

import java.util.Date;

/**
 * Created by OsmanKorcan on 24.4.2016.
 */
public class Reports {

    private String  reportName;
    private int CompanyID;
    private Date reportDate;
    private String explanation;

    public Reports(String reportName, int companyID, String explanation) {
        super();
        this.reportName = reportName;
        CompanyID = companyID;
        this.explanation=explanation;
    }

    @Override
    public String toString() {
        return reportName;
    }
    public String getReportName() {
        return reportName;
    }
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Date getReportDate(Date reportDate) {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getExplanation() {
        return explanation;
    }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(int companyID) {
        CompanyID = companyID;
    }
}


