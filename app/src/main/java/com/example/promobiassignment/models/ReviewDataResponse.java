package com.example.promobiassignment.models;

import com.example.promobiassignment.database.entities.Feed;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewDataResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("copyright")
    private String copyright;
    @SerializedName("has_more")
    private Boolean hasMore;
    @SerializedName("num_results")
    private Integer numResults;
    @SerializedName("results")
    private List<Feed> feeds = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }
}
