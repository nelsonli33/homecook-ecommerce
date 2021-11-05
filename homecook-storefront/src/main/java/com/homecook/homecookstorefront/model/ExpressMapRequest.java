package com.homecook.homecookstorefront.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpressMapRequest
{
    @JsonProperty("serverReplyUrl")
    private String serverReplyUrl;

    public String getServerReplyUrl()
    {
        return serverReplyUrl;
    }

    public void setServerReplyUrl(String serverReplyUrl)
    {
        this.serverReplyUrl = serverReplyUrl;
    }
}
