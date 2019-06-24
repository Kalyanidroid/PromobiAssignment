package com.example.promobiassignment.database.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Multimedia implements Serializable {

    @SerializedName("type")
    private String mediaType;
    @SerializedName("src")
    private String src;
    @SerializedName("width")
    private Integer width;
    @SerializedName("height")
    private Integer height;

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
