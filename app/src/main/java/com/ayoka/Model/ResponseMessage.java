package com.ayoka.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmetyildirim on 15.5.2016.
 */



public class ResponseMessage<T> {

    @SerializedName("ErrorCode")
    @Expose
    private Integer ErrorCode;
    @SerializedName("ErrorMessage")
    @Expose
    private String ErrorMessage;
    @SerializedName("WarningMessage")
    @Expose
    private String WarningMessage;
    @SerializedName("Message")
    @Expose
    private T Message;


    public T getMessage() {
        return Message;
    }

    /**
     *
     * @param Message
     * The Message
     */
    public void setMessage(T Message) {
        this.Message = Message;
    }
    /**
     *
     * @return
     * The Message
     */


    /**
     *
     * @return
     * The ErrorCode
     */
    public Integer getErrorCode() {
        return ErrorCode;
    }

    /**
     *
     * @param ErrorCode
     * The ErrorCode
     */
    public void setErrorCode(Integer ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    /**
     *
     * @return
     * The ErrorMessage
     */
    public String getErrorMessage() {
        return ErrorMessage;
    }

    /**
     *
     * @param ErrorMessage
     * The ErrorMessage
     */
    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    /**
     *
     * @return
     * The WarningMessage
     */
    public String getWarningMessage() {
        return WarningMessage;
    }

    /**
     *
     * @param WarningMessage
     * The WarningMessage
     */
    public void setWarningMessage(String WarningMessage) {
        this.WarningMessage = WarningMessage;
    }


}


