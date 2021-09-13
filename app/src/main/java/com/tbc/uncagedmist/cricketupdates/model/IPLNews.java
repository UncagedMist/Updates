package com.tbc.uncagedmist.cricketupdates.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IPLNews implements Serializable {

    @SerializedName("headlines")
    private String headlines;
    @SerializedName("newsUrl")
    private String newsUrl;
    @SerializedName("newsSource")
    private String newsSource;
    @SerializedName("newsImage")
    private String newsImage;

    @SerializedName("summary")
    private String summary;
    @SerializedName("story_type")
    private String story_type;
    @SerializedName("header")
    private String header;
    @SerializedName("location")
    private String location;
    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("source")
    private String source;
    @SerializedName("highlight")
    private String highlight;
    @SerializedName("image")
    private String image;


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStory_type() {
        return story_type;
    }

    public void setStory_type(String story_type) {
        this.story_type = story_type;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeadlines() {
        return headlines;
    }

    public void setHeadlines(String headlines) {
        this.headlines = headlines;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }
}