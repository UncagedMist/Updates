package com.tbc.uncagedmist.cricketupdates.datamodel;

import com.google.gson.annotations.SerializedName;

public class AppStructureBase {


    @SerializedName("inter_id")
    private String inter_id;
    @SerializedName("banner_id")
    private String banner_id;
    @SerializedName("native_id")
    private String native_id;


    @SerializedName("fb_banner_id")
    private String fb_banner_id;

    @SerializedName("fb_inter_id")
    private String fb_inter_id;

    @SerializedName("fb_native_id")
    private String fb_native_id;

    @SerializedName("fb_native_home")
    private int fb_native_home;
    @SerializedName("google_native_home")
    private int google_native_home;


    @SerializedName("fb_native_liveline")
    private int fb_native_liveline;
    @SerializedName("google_native_liveline")
    private int google_native_liveline;

    @SerializedName("fb_native_series_option")
    private int fb_native_series_option;
    @SerializedName("google_native_series_option")
    private int google_native_series_option;

    @SerializedName("fb_banner_series_detail")
    private int fb_banner_series_detail;
    @SerializedName("google_banner_series_detail")
    private int google_banner_series_detail;

    @SerializedName("fb_inter_option1")
    private int fb_inter_option1;

    @SerializedName("google_inter_option1")
    private int google_inter_option1;

    @SerializedName("fb_inter_option2")
    private int fb_inter_option2;

    @SerializedName("google_inter_option2")
    private int google_inter_option2;

    @SerializedName("fb_inter_option3")
    private int fb_inter_option3;

    @SerializedName("google_inter_option3")
    private int google_inter_option3;

    public String getInter_id() {
        return inter_id;
    }

    public void setInter_id(String inter_id) {
        this.inter_id = inter_id;
    }

    public String getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
    }

    public int getFb_inter_option1() {
        return fb_inter_option1;
    }

    public void setFb_inter_option1(int fb_inter_option1) {
        this.fb_inter_option1 = fb_inter_option1;
    }

    public int getGoogle_inter_option1() {
        return google_inter_option1;
    }

    public void setGoogle_inter_option1(int google_inter_option1) {
        this.google_inter_option1 = google_inter_option1;
    }

    public int getFb_inter_option2() {
        return fb_inter_option2;
    }

    public void setFb_inter_option2(int fb_inter_option2) {
        this.fb_inter_option2 = fb_inter_option2;
    }

    public int getGoogle_inter_option2() {
        return google_inter_option2;
    }

    public void setGoogle_inter_option2(int google_inter_option2) {
        this.google_inter_option2 = google_inter_option2;
    }

    public int getFb_inter_option3() {
        return fb_inter_option3;
    }

    public void setFb_inter_option3(int fb_inter_option3) {
        this.fb_inter_option3 = fb_inter_option3;
    }

    public int getGoogle_inter_option3() {
        return google_inter_option3;
    }

    public void setGoogle_inter_option3(int google_inter_option3) {
        this.google_inter_option3 = google_inter_option3;
    }

    public String getNative_id() {
        return native_id;
    }

    public void setNative_id(String native_id) {
        this.native_id = native_id;
    }

    public String getFb_banner_id() {
        return fb_banner_id;
    }

    public void setFb_banner_id(String fb_banner_id) {
        this.fb_banner_id = fb_banner_id;
    }

    public String getFb_inter_id() {
        return fb_inter_id;
    }

    public void setFb_inter_id(String fb_inter_id) {
        this.fb_inter_id = fb_inter_id;
    }

    public String getFb_native_id() {
        return fb_native_id;
    }

    public void setFb_native_id(String fb_native_id) {
        this.fb_native_id = fb_native_id;
    }

    public int getFb_native_home() {
        return fb_native_home;
    }

    public void setFb_native_home(int fb_native_home) {
        this.fb_native_home = fb_native_home;
    }

    public int getGoogle_native_home() {
        return google_native_home;
    }

    public void setGoogle_native_home(int google_native_home) {
        this.google_native_home = google_native_home;
    }

    public int getFb_native_liveline() {
        return fb_native_liveline;
    }

    public void setFb_native_liveline(int fb_native_liveline) {
        this.fb_native_liveline = fb_native_liveline;
    }

    public int getGoogle_native_liveline() {
        return google_native_liveline;
    }

    public void setGoogle_native_liveline(int google_native_liveline) {
        this.google_native_liveline = google_native_liveline;
    }

    public int getFb_native_series_option() {
        return fb_native_series_option;
    }

    public void setFb_native_series_option(int fb_native_series_option) {
        this.fb_native_series_option = fb_native_series_option;
    }

    public int getGoogle_native_series_option() {
        return google_native_series_option;
    }

    public void setGoogle_native_series_option(int google_native_series_option) {
        this.google_native_series_option = google_native_series_option;
    }

    public int getFb_banner_series_detail() {
        return fb_banner_series_detail;
    }

    public void setFb_banner_series_detail(int fb_banner_series_detail) {
        this.fb_banner_series_detail = fb_banner_series_detail;
    }

    public int getGoogle_banner_series_detail() {
        return google_banner_series_detail;
    }

    public void setGoogle_banner_series_detail(int google_banner_series_detail) {
        this.google_banner_series_detail = google_banner_series_detail;
    }
}