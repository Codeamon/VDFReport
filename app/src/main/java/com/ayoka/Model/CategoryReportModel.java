package com.ayoka.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryReportModel {

    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("DepartmentId")
    @Expose
    private Integer DepartmentId;
    @SerializedName("CategoryReportname")
    @Expose
    private String CategoryReportname;
    @SerializedName("Type")
    @Expose
    private Integer Type;
    @SerializedName("MainId")
    @Expose
    private Integer MainId;

    /**
     *
     * @return
     * The Id
     */
    public Integer getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The Id
     */
    public void setId(Integer Id) {
        this.Id = Id;
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
    public String getCategoryReportname() {
        return CategoryReportname;
    }

    /**
     *
     * @param CategoryReportname
     * The CategoryReportname
     */
    public void setCategoryReportname(String CategoryReportname) {
        this.CategoryReportname = CategoryReportname;
    }

    /**
     *
     * @return
     * The Type
     */
    public Integer getType() {
        return Type;
    }

    /**
     *
     * @param Type
     * The Type
     */
    public void setType(Integer Type) {
        this.Type = Type;
    }

    /**
     *
     * @return
     * The MainId
     */
    public Integer getMainId() {
        return MainId;
    }

    /**
     *
     * @param MainId
     * The MainId
     */
    public void setMainId(Integer MainId) {
        this.MainId = MainId;
    }

}