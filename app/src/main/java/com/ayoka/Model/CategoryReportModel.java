package com.ayoka.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryReportModel {

    @SerializedName("CategoryReportId")
    @Expose
    private Integer CategoryReportId;
    @SerializedName("DepartmentId")
    @Expose
    private Integer DepartmentId;
    @SerializedName("CategoryReportName")
    @Expose
    private String CategoryReportName;
    @SerializedName("Type")
    @Expose
    private Boolean Type;
    @SerializedName("MainCategoryId")
    @Expose
    private Integer MainCategoryId;
    @SerializedName("Explanation")
    @Expose
    private String Explanation;
    /**
     *
     * @return
     * The Id
     */
    public Integer getCategoryReportId() {
        return CategoryReportId;
    }

    /**
     *
     * @param CategoryReportId
     * The CategoryReportId
     */
    public void setCategoryReportId(Integer CategoryReportId) {
        this.CategoryReportId = CategoryReportId;
    }

    /**
     *
     * @return
     * The DepartmentId
     */
    public Integer getDepartmentId() {
        return DepartmentId;
    }

    /**
     *
     * @param DepartmentId
     * The DepartmentId
     */
    public void setDepartmentId(Integer DepartmentId) {
        this.DepartmentId = DepartmentId;
    }

    /**
     *
     * @return
     * The CategoryReportname
     */
    public String getCategoryReportName() {
        return CategoryReportName;
    }

    /**
     *
     * @param CategoryReportName
     * The CategoryReportName
     */
    public void setCategoryReportName(String CategoryReportName) {
        this.CategoryReportName = CategoryReportName;
    }

    /**
     *
     * @return
     * The Type
     */
    public Boolean getType() {
        return Type;
    }

    /**
     *
     * @param Type
     * The Type
     */
    public void setType(Boolean Type) {
        this.Type = Type;
    }

    /**
     *
     * @return
     * The MainCategoryId
     */
    public Integer getMainCategoryId() {
        return MainCategoryId;
    }

    /**
     *
     * @param MainCategoryId
     * The MainId
     */
    public void setMainCategoryId(Integer MainCategoryId) {
        this.MainCategoryId = MainCategoryId;
    }

    /**
     *
     * @return
     * The Explanation
     */
    public String getExplanation() {
        return Explanation;
    }

    /**
     *
     * @param Explanation
     * The Explanation
     */
    public void setExplanation(String Explanation) {
        this.Explanation = Explanation;
    }
}