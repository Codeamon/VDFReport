      package com.ayoka.Model;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class DepartmanModel {

    @SerializedName("DepartmentId")
    @Expose
    private Integer DepartmentId;
    @SerializedName("DepartmentName")
    @Expose
    private String DepartmentName;

    @SerializedName("CompanyId")
    @Expose
    private Integer CompanyId;

    /**
     *
     * @return
     * The Id
     */
    public Integer getId() {
        return DepartmentId;
    }

    /**
     *
     * @param DepartmentId
     * The Id
     */
    public void setId(Integer DepartmentId) {
        this.DepartmentId = DepartmentId;
    }

    /**
     *
     * @return
     * The DepartmentName
     */
    public String getDepartmentName() {
        return DepartmentName;
    }

    /**
     *
     * @param DepartmentName
     * The DepartmentName
     */
    public void setDepartmentName(String DepartmentName) {
        this.DepartmentName = DepartmentName;
    }


    public Integer getCompanyId() {
        return CompanyId;
    }

    /**
     *
     * @param CompanyId
     * The CompanyId
     */
    public void setCompanyId(Integer CompanyId) {
        this.CompanyId = CompanyId;
    }

}
