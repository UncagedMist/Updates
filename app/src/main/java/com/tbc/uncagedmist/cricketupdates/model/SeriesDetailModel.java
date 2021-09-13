package com.tbc.uncagedmist.cricketupdates.model;

import androidx.annotation.Keep;

@Keep
public class SeriesDetailModel {
    String name,match_id,series_id, series_name,data_path,team1_name,team2_name;
    DataModel header;
    DataModel venue;
    DataModel team1;
    DataModel team2,bat_team,bow_team;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataModel getBat_team() {
        return bat_team;
    }

    public void setBat_team(DataModel bat_team) {
        this.bat_team = bat_team;
    }

    public DataModel getBow_team() {
        return bow_team;
    }

    public void setBow_team(DataModel bow_team) {
        this.bow_team = bow_team;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getSeries_id() {
        return series_id;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }

    public String getData_path() {
        return data_path;
    }

    public void setData_path(String data_path) {
        this.data_path = data_path;
    }

    public String getTeam1_name() {
        return team1_name;
    }

    public void setTeam1_name(String team1_name) {
        this.team1_name = team1_name;
    }

    public String getTeam2_name() {
        return team2_name;
    }

    public void setTeam2_name(String team2_name) {
        this.team2_name = team2_name;
    }

    public DataModel getHeader() {
        return header;
    }

    public void setHeader(DataModel header) {
        this.header = header;
    }

    public DataModel getVenue() {
        return venue;
    }

    public void setVenue(DataModel venue) {
        this.venue = venue;
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
}
