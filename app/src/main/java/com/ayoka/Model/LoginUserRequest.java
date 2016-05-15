
package com.ayoka.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginUserRequest {

    @SerializedName("Username")
    @Expose
    private String Username;
    @SerializedName("Password")
    @Expose
    private String Password;

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
     * The Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     *
     * @param Password
     * The Password
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

}