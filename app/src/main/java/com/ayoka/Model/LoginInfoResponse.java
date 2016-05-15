package com.ayoka.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginInfoResponse {


    @SerializedName("UserId")
    @Expose
    private Integer UserId;
    @SerializedName("Username")
    @Expose
    private String Username;
    @SerializedName("FullName")
    @Expose
    private String FullName;
    @SerializedName("CompanyId")
    @Expose
    private Integer CompanyId;
    @SerializedName("UserGroupId")
    @Expose
    private Integer UserGroupId;


    /**
     * @return The UserGroupId
     */
    public Integer getUserId() {
        return UserId;
    }

    /**
     * @param UserId The UserId
     */
    public void setUserId(Integer UserId) {
        this.UserId = UserId;
    }

    /**
     * @return The Username
     */
    public String getUsername() {
        return Username;
    }

    /**
     * @param Username The Username
     */
    public void setUsername(String Username) {
        this.Username = Username;
    }

    /**
     * @return The FullName
     */
    public String getFullName() {
        return FullName;
    }

    /**
     * @param FullName The FullName
     */
    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    /**
     * @return The CompanyId
     */
    public Integer getCompanyId() {
        return CompanyId;
    }

    /**
     * @param CompanyId The CompanyId
     */
    public void setCompanyId(Integer CompanyId) {
        this.CompanyId = CompanyId;
    }

    /**
     * @return The UserGroupId
     */
    public Integer getUserGroupId() {
        return UserGroupId;
    }

    /**
     * @param UserGroupId The UserGroupId
     */
    public void setUserGroupId(Integer UserGroupId) {
        this.UserGroupId = UserGroupId;
    }

}
