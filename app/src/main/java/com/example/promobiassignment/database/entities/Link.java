package com.example.promobiassignment.database.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Link implements Serializable {

    @SerializedName("type")
    private String linkType;
    @SerializedName("url")
    private String url;
    @SerializedName("suggested_link_text")
    private String suggestedLinkText;

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSuggestedLinkText() {
        return suggestedLinkText;
    }

    public void setSuggestedLinkText(String suggestedLinkText) {
        this.suggestedLinkText = suggestedLinkText;
    }
}
