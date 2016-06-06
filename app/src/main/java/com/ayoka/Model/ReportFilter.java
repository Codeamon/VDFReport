
        package com.ayoka.Model;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import java.io.Serializable;

        public class ReportFilter  implements Serializable {

            @SerializedName("FilterColumn")
            @Expose
            private String filterColumn;
            @SerializedName("FilterRequired")
            @Expose
            private Boolean filterRequired;
            @SerializedName("FilterTypeId")
            @Expose
            private Integer filterTypeId;
            @SerializedName("FilterTypeName")
            @Expose
            private String filterTypeName;
            @SerializedName("DefaultValue")
            @Expose
            private String defaultValue;

            /**
             * @return The filterColumn
             */
            public String getFilterColumn() {
                return filterColumn;
            }

            /**
             * @param filterColumn The FilterColumn
             */
            public void setFilterColumn(String filterColumn) {
                this.filterColumn = filterColumn;
            }

            /**
             * @return The filterRequired
             */
            public Boolean getFilterRequired() {
                return filterRequired;
            }

            /**
             * @param filterRequired The FilterRequired
             */
            public void setFilterRequired(Boolean filterRequired) {
                this.filterRequired = filterRequired;
            }

            /**
             * @return The filterTypeId
             */
            public Integer getFilterTypeId() {
                return filterTypeId;
            }

            /**
             * @param filterTypeId The FilterTypeId
             */
            public void setFilterTypeId(Integer filterTypeId) {
                this.filterTypeId = filterTypeId;
            }

            /**
             * @return The filterTypeName
             */
            public String getFilterTypeName() {
                return filterTypeName;
            }

            /**
             * @param filterTypeName The FilterTypeName
             */
            public void setFilterTypeName(String filterTypeName) {
                this.filterTypeName = filterTypeName;
            }

            /**
             * @return The defaultValue
             */
            public String getDefaultValue() {
                return defaultValue;
            }

            /**
             * @param defaultValue The DefaultValue
             */
            public void setDefaultValue(String defaultValue) {
                this.defaultValue = defaultValue;
            }
        }
