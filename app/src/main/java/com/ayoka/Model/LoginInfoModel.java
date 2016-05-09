package com.ayoka.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginInfoModel {

    @SerializedName("Username")
    @Expose
    private String Username;
    @SerializedName("Token")
    @Expose
    private String Token;
    @SerializedName("IsDealer")
    @Expose
    private Boolean IsDealer;
    @SerializedName("DealerId")
    @Expose
    private Integer DealerId;
    @SerializedName("IsSuperUser")
    @Expose
    private Boolean IsSuperUser;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("Surname")
    @Expose
    private String Surname;
    @SerializedName("Email")
    @Expose
    private String Email;

    /**
     *
     * @return
     * The Username
     */
    public String getUsername() {
        return Username;
    }

    /**
     *
     * @param Username
     * The Username
     */
    public void setUsername(String Username) {
        this.Username = Username;
    }

    /**
     *
     * @return
     * The Token
     */
    public String getToken() {
        return Token;
    }

    /**
     *
     * @param Token
     * The Token
     */
    public void setToken(String Token) {
        this.Token = Token;
    }

    /**
     *
     * @return
     * The IsDealer
     */
    public Boolean getIsDealer() {
        return IsDealer;
    }

    /**
     *
     * @param IsDealer
     * The IsDealer
     */
    public void setIsDealer(Boolean IsDealer) {
        this.IsDealer = IsDealer;
    }

    /**
     *
     * @return
     * The DealerId
     */
    public Integer getDealerId() {
        return DealerId;
    }

    /**
     *
     * @param DealerId
     * The DealerId
     */
    public void setDealerId(Integer DealerId) {
        this.DealerId = DealerId;
    }

    /**
     *
     * @return
     * The IsSuperUser
     */
    public Boolean getIsSuperUser() {
        return IsSuperUser;
    }

    /**
     *
     * @param IsSuperUser
     * The IsSuperUser
     */
    public void setIsSuperUser(Boolean IsSuperUser) {
        this.IsSuperUser = IsSuperUser;
    }

    /**
     *
     * @return
     * The Name
     */
    public String getName() {
        return Name;
    }

    /**
     *
     * @param Name
     * The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     *
     * @return
     * The Surname
     */
    public String getSurname() {
        return Surname;
    }

    /**
     *
     * @param Surname
     * The Surname
     */
    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    /**
     *
     * @return
     * The Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     *
     * @param Email
     * The Email
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

}