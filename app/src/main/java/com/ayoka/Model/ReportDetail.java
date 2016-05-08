
package com.ayoka.Model;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportDetail {

    @SerializedName("ReportList")
    @Expose
    private List<ReportList> ReportList = new ArrayList<ReportList>();
    @SerializedName("TabList")
    @Expose
    private List<TabList> TabList = new ArrayList<TabList>();

    /**
     * @return The ReportList
     */
    public List<ReportList> getReportList() {
        return ReportList;
    }

    /**
     * @param ReportList The ReportList
     */
    public void setReportList(List<ReportList> ReportList) {
        this.ReportList = ReportList;
    }

    /**
     * @return The TabList
     */
    public List<TabList> getTabList() {
        return TabList;
    }

    /**
     * @param TabList The TabList
     */
    public void setTabList(List<TabList> TabList) {
        this.TabList = TabList;
    }


    public class ReportList {

        @SerializedName("Description")
        @Expose
        private String Description;
        @SerializedName("FilterColumn")
        @Expose
        private String FilterColumn;
        @SerializedName("ReportDetailId")
        @Expose
        private Integer ReportDetailId;
        @SerializedName("ReportId")
        @Expose
        private Integer ReportId;
        @SerializedName("ReportValues")
        @Expose
        private List<ReportValue> ReportValues = new ArrayList<ReportValue>();

        /**
         * @return The Description
         */
        public String getDescription() {
            return Description;
        }

        /**
         * @param Description The Description
         */
        public void setDescription(String Description) {
            this.Description = Description;
        }

        /**
         * @return The FilterColumn
         */
        public String getFilterColumn() {
            return FilterColumn;
        }

        /**
         * @param FilterColumn The FilterColumn
         */
        public void setFilterColumn(String FilterColumn) {
            this.FilterColumn = FilterColumn;
        }

        /**
         * @return The ReportDetailId
         */
        public Integer getReportDetailId() {
            return ReportDetailId;
        }

        /**
         * @param ReportDetailId The ReportDetailId
         */
        public void setReportDetailId(Integer ReportDetailId) {
            this.ReportDetailId = ReportDetailId;
        }

        /**
         * @return The ReportId
         */
        public Integer getReportId() {
            return ReportId;
        }

        /**
         * @param ReportId The ReportId
         */
        public void setReportId(Integer ReportId) {
            this.ReportId = ReportId;
        }

        /**
         * @return The ReportValues
         */
        public List<ReportValue> getReportValues() {
            return ReportValues;
        }

        /**
         * @param ReportValues The ReportValues
         */
        public void setReportValues(List<ReportValue> ReportValues) {
            this.ReportValues = ReportValues;
        }

    }

    public class ReportValue {

        @SerializedName("ValueName")
        @Expose
        private String ValueName;
        @SerializedName("ValueTypeName")
        @Expose
        private String ValueTypeName;

        /**
         * @return The ValueName
         */
        public String getValueName() {
            return ValueName;
        }

        /**
         * @param ValueName The ValueName
         */
        public void setValueName(String ValueName) {
            this.ValueName = ValueName;
        }

        /**
         * @return The ValueTypeName
         */
        public String getValueTypeName() {
            return ValueTypeName;
        }

        /**
         * @param ValueTypeName The ValueTypeName
         */
        public void setValueTypeName(String ValueTypeName) {
            this.ValueTypeName = ValueTypeName;
        }

    }

    public class TabList {

        @SerializedName("ReportDetailId")
        @Expose
        private Integer ReportDetailId;
        @SerializedName("TabName")
        @Expose
        private String TabName;

        /**
         * @return The ReportDetailId
         */
        public Integer getReportDetailId() {
            return ReportDetailId;
        }

        /**
         * @param ReportDetailId The ReportDetailId
         */
        public void setReportDetailId(Integer ReportDetailId) {
            this.ReportDetailId = ReportDetailId;
        }

        /**
         * @return The TabName
         */
        public String getTabName() {
            return TabName;
        }

        /**
         * @param TabName The TabName
         */
        public void setTabName(String TabName) {
            this.TabName = TabName;
        }

    }
}