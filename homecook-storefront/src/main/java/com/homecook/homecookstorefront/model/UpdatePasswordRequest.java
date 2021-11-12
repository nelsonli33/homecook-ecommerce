package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePasswordRequest
{
    @JsonProperty("currentPassword")
    private String currentPassword;

    @JsonProperty("newPassword")
    private String newPassword;

    @JsonProperty("checkNewPassword")
    private String checkNewPassword;

    public String getCurrentPassword()
    {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword)
    {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }

    public String getCheckNewPassword()
    {
        return checkNewPassword;
    }

    public void setCheckNewPassword(String checkNewPassword)
    {
        this.checkNewPassword = checkNewPassword;
    }
}