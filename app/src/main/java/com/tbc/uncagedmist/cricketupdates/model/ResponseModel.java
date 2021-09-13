package com.tbc.uncagedmist.cricketupdates.model;

import androidx.annotation.Keep;

import java.util.ArrayList;

@Keep
public class ResponseModel {
    ArrayList<DataModel> series;
    ArrayList<String> position;
    ArrayList<SeriesDetailModel> matches;
    ArrayList<DataModel> news;
    ArrayList<DataModel> gallery;
    ArrayList<SeriesStatsModel> stats;
    ArrayList<DataModel> teams;
    PointsTableDataModel points;
    //ArrayList<DataModel> stories;
    ArrayList<IPLNews> news_list;

    //ScoreCard
    String fallofWicketsLabel,fallofWicketsValue,powerplayLabel,oversLabel,runslabel,powerplayValue,oversValue,runsValue,matchTitle,team1Name,team1Score;
    ArrayList<ScoreCardTableModel> innings1;
    ArrayList<ScoreCardTableModel> bowlInnings1;
    String fallofInnings2WicketsLabel,fallofInnings2WicketsValue,team2Name,team2Score;
    ArrayList<ScoreCardTableModel> innings2;
    ArrayList<ScoreCardTableModel> bowlInnings2;
    //matchInfo
    ArrayList<ScoreCardTableModel> matchInfos;


    public ArrayList<IPLNews> getNews_list() {
        return news_list;
    }

    public void setNews_list(ArrayList<IPLNews> news_list) {
        this.news_list = news_list;
    }

    public ArrayList<ScoreCardTableModel> getMatchInfos() {
        return matchInfos;
    }

    public void setMatchInfos(ArrayList<ScoreCardTableModel> matchInfos) {
        this.matchInfos = matchInfos;
    }

    public String getFallofInnings2WicketsLabel() {
        return fallofInnings2WicketsLabel;
    }

    public void setFallofInnings2WicketsLabel(String fallofInnings2WicketsLabel) {
        this.fallofInnings2WicketsLabel = fallofInnings2WicketsLabel;
    }

    public String getFallofInnings2WicketsValue() {
        return fallofInnings2WicketsValue;
    }

    public void setFallofInnings2WicketsValue(String fallofInnings2WicketsValue) {
        this.fallofInnings2WicketsValue = fallofInnings2WicketsValue;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public String getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(String team2Score) {
        this.team2Score = team2Score;
    }

    public ArrayList<ScoreCardTableModel> getInnings2() {
        return innings2;
    }

    public void setInnings2(ArrayList<ScoreCardTableModel> innings2) {
        this.innings2 = innings2;
    }

    public ArrayList<ScoreCardTableModel> getBowlInnings2() {
        return bowlInnings2;
    }

    public void setBowlInnings2(ArrayList<ScoreCardTableModel> bowlInnings2) {
        this.bowlInnings2 = bowlInnings2;
    }

    public ArrayList<ScoreCardTableModel> getBowlInnings1() {
        return bowlInnings1;
    }

    public void setBowlInnings1(ArrayList<ScoreCardTableModel> bowlInnings1) {
        this.bowlInnings1 = bowlInnings1;
    }

    public String getMatchTitle() {
        return matchTitle;
    }

    public void setMatchTitle(String matchTitle) {
        this.matchTitle = matchTitle;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(String team1Score) {
        this.team1Score = team1Score;
    }

    public String getPowerplayLabel() {
        return powerplayLabel;
    }

    public void setPowerplayLabel(String powerplayLabel) {
        this.powerplayLabel = powerplayLabel;
    }

    public String getOversLabel() {
        return oversLabel;
    }

    public void setOversLabel(String oversLabel) {
        this.oversLabel = oversLabel;
    }

    public String getRunslabel() {
        return runslabel;
    }

    public void setRunslabel(String runslabel) {
        this.runslabel = runslabel;
    }

    public String getPowerplayValue() {
        return powerplayValue;
    }

    public void setPowerplayValue(String powerplayValue) {
        this.powerplayValue = powerplayValue;
    }

    public String getOversValue() {
        return oversValue;
    }

    public void setOversValue(String oversValue) {
        this.oversValue = oversValue;
    }

    public String getRunsValue() {
        return runsValue;
    }

    public void setRunsValue(String runsValue) {
        this.runsValue = runsValue;
    }

    public String getFallofWicketsLabel() {
        return fallofWicketsLabel;
    }

    public void setFallofWicketsLabel(String fallofWicketsLabel) {
        this.fallofWicketsLabel = fallofWicketsLabel;
    }

    public String getFallofWicketsValue() {
        return fallofWicketsValue;
    }

    public void setFallofWicketsValue(String fallofWicketsValue) {
        this.fallofWicketsValue = fallofWicketsValue;
    }

    public ArrayList<ScoreCardTableModel> getInnings1() {
        return innings1;
    }

    public void setInnings1(ArrayList<ScoreCardTableModel> innings1) {
        this.innings1 = innings1;
    }

//    public ArrayList<DataModel> getStories() {
//        return stories;
//    }
//
//    public void setStories(ArrayList<DataModel> stories) {
//        this.stories = stories;
//    }

    public PointsTableDataModel getPoints() {
        return points;
    }

    public void setPoints(PointsTableDataModel points) {
        this.points = points;
    }

    public ArrayList<String> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<String> position) {
        this.position = position;
    }

    public ArrayList<SeriesDetailModel> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<SeriesDetailModel> matches) {
        this.matches = matches;
    }

    public ArrayList<DataModel> getNews() {
        return news;
    }

    public void setNews(ArrayList<DataModel> news) {
        this.news = news;
    }

    public ArrayList<DataModel> getGallery() {
        return gallery;
    }

    public void setGallery(ArrayList<DataModel> gallery) {
        this.gallery = gallery;
    }

    public ArrayList<SeriesStatsModel> getStats() {
        return stats;
    }

    public void setStats(ArrayList<SeriesStatsModel> stats) {
        this.stats = stats;
    }

    public ArrayList<DataModel> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<DataModel> teams) {
        this.teams = teams;
    }

    public ArrayList<DataModel> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<DataModel> series) {
        this.series = series;
    }
}
