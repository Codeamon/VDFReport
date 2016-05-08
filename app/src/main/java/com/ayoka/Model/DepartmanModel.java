      package com.ayoka.Model;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class DepartmanModel {

    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("DepartmentName")
    @Expose
    private String DepartmentName;
    @SerializedName("IconName")
    @Expose
    private Object IconName;
    @SerializedName("ProjectId")
    @Expose
    private Integer ProjectId;

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

    /**
     *
     * @return
     * The IconName
     */
    public Object getIconName() {
        return IconName;
    }

    /**
     *
     * @param IconName
     * The IconName
     */
    public void setIconName(Object IconName) {
        this.IconName = IconName;
    }

    /**
     *
     * @return
     * The ProjectId
     */
    public Integer getProjectId() {
        return ProjectId;
    }

    /**
     *
     * @param ProjectId
     * The ProjectId
     */
    public void setProjectId(Integer ProjectId) {
        this.ProjectId = ProjectId;
    }

}
