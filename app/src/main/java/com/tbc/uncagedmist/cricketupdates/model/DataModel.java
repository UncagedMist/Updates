package com.tbc.uncagedmist.cricketupdates.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

@Keep
public class DataModel implements Serializable {

    private String matchId,countryCode, image, sName, batteamscore, start_time,desc,end_time,type, bowlteamscore, status, mnum, batteamid, srs,mchState,banner;
    DataModel team1, team2, miniscore, header;
    String name;
    //All Series
    String start_date, end_date, series_category;
    @SerializedName("id")
    String idn;
    String headline, timestamp, s_name,package_name;

    //All SeriesMatches
    String match_desc, dn, state, state_title, location, timezone, lat;
    @SerializedName("long")
    String longitude;
    ArrayList<String> score;
    ArrayList<ScoreModel> innings;

    //HomeNews
    String hline, intro, date, src, storyType, isE, topic_name, author_name, relatedStoriesCount, ipath, iwth, iht,mchDesc;
    DataModel img, ximg;

    public String getMchDesc() {
        return mchDesc;
    }

    public void setMchDesc(String mchDesc) {
        this.mchDesc = mchDesc;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getHline() {
        return hline;
    }

    public void setHline(String hline) {
        this.hline = hline;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getStoryType() {
        return storyType;
    }

    public void setStoryType(String storyType) {
        this.storyType = storyType;
    }

    public String getIsE() {
        return isE;
    }

    public void setIsE(String isE) {
        this.isE = isE;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getRelatedStoriesCount() {
        return relatedStoriesCount;
    }

    public void setRelatedStoriesCount(String relatedStoriesCount) {
        this.relatedStoriesCount = relatedStoriesCount;
    }

    public String getIpath() {
        return ipath;
    }

    public void setIpath(String ipath) {
        this.ipath = ipath;
    }

    public String getIwth() {
        return iwth;
    }

    public void setIwth(String iwth) {
        this.iwth = iwth;
    }

    public String getIht() {
        return iht;
    }

    public void setIht(String iht) {
        this.iht = iht;
    }

    public DataModel getImg() {
        return img;
    }

    public void setImg(DataModel img) {
        this.img = img;
    }

    public DataModel getXimg() {
        return ximg;
    }

    public void setXimg(DataModel ximg) {
        this.ximg = ximg;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMchState() {
        return mchState;
    }

    public void setMchState(String mchState) {
        this.mchState = mchState;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public DataModel getTeam1() {
        return team1;
    }

    public void setTeam1(DataModel team1) {
        this.team1 = team1;
    }

    public DataModel getTeam2() {
        return team2;
    }

    public void setTeam2(DataModel team2) {
        this.team2 = team2;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getIdn() {
        return idn;
    }

    public void setIdn(String idn) {
        this.idn = idn;
    }

    public DataModel getMiniscore() {
        return miniscore;
    }

    public void setMiniscore(DataModel miniscore) {
        this.miniscore = miniscore;
    }

    public String getBatteamscore() {
        return batteamscore;
    }

    public void setBatteamscore(String batteamscore) {
        this.batteamscore = batteamscore;
    }

    public String getBowlteamscore() {
        return bowlteamscore;
    }

    public void setBowlteamscore(String bowlteamscore) {
        this.bowlteamscore = bowlteamscore;
    }

    public DataModel getHeader() {
        return header;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setHeader(DataModel header) {
        this.header = header;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMnum() {
        return mnum;
    }

    public void setMnum(String mnum) {
        this.mnum = mnum;
    }

    public String getBatteamid() {
        return batteamid;
    }

    public void setBatteamid(String batteamid) {
        this.batteamid = batteamid;
    }

    public String getSrs() {
        return srs;
    }

    public void setSrs(String srs) {
        this.srs = srs;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getSeries_category() {
        return series_category;
    }

    public void setSeries_category(String series_category) {
        this.series_category = series_category;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getMatch_desc() {
        return match_desc;
    }

    public void setMatch_desc(String match_desc) {
        this.match_desc = match_desc;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState_title() {
        return state_title;
    }

    public void setState_title(String state_title) {
        this.state_title = state_title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public ArrayList<String> getScore() {
        return score;
    }

    public void setScore(ArrayList<String> score) {
        this.score = score;
    }

    public ArrayList<ScoreModel> getInnings() {
        return innings;
    }

    public void setInnings(ArrayList<ScoreModel> innings) {
        this.innings = innings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
