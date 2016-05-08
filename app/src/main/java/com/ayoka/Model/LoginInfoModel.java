package com.ayoka.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmetyildirim on 6.5.2016.
 */
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

}
