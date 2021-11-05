package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultUrl
{
    @JsonProperty("frontend_redirect_url")
    private String frontendUrl;

    @JsonProperty("backend_notify_url")
    private String backendNotifyUrl;

    @JsonProperty("go_back_url")
    private String goBackUrl;

    public String getFrontendUrl()
    {
        return frontendUrl;
    }

    public void setFrontendUrl(String frontendUrl)
    {
        this.frontendUrl = frontendUrl;
    }

    public String getBackendNotifyUrl()
    {
        return backendNotifyUrl;
    }

    public void setBackendNotifyUrl(String backendNotifyUrl)
    {
        this.backendNotifyUrl = backendNotifyUrl;
    }

    public String getGoBackUrl()
    {
        return goBackUrl;
    }

    public void setGoBackUrl(String goBackUrl)
    {
        this.goBackUrl = goBackUrl;
    }

    @Override
    public String toString()
    {
        return "ResultUrl{" +
                "frontendUrl='" + frontendUrl + '\'' +
                ", backendNotifyUrl='" + backendNotifyUrl + '\'' +
                ", goBackUrl='" + goBackUrl + '\'' +
                '}';
    }


    public static final class Builder
    {
        private String frontendUrl;
        private String backendNotifyUrl;
        private String goBackUrl;

        public Builder frontendUrl(String frontendUrl)
        {
            this.frontendUrl = frontendUrl;
            return this;
        }

        public Builder backendNotifyUrl(String backendNotifyUrl)
        {
            this.backendNotifyUrl = backendNotifyUrl;
            return this;
        }

        public Builder goBackUrl(String goBackUrl)
        {
            this.goBackUrl = goBackUrl;
            return this;
        }

        public ResultUrl build()
        {
            ResultUrl resultUrl = new ResultUrl();
            resultUrl.setFrontendUrl(frontendUrl);
            resultUrl.setBackendNotifyUrl(backendNotifyUrl);
            resultUrl.setGoBackUrl(goBackUrl);
            return resultUrl;
        }
    }
}
