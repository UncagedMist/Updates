package com.tbc.uncagedmist.cricketupdates.model;

import androidx.annotation.Keep;

import java.util.ArrayList;

@Keep
public class PointsTableDataModel {
    String series_id,series_name,id,name,p,w,l,nr,t,points,nrr;
    ArrayList<String> title,header;
    PointsTableDataModel group;
    ArrayList<PointsTableDataModel> Teams;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getNrr() {
        return nrr;
    }

    public void setNrr(String nrr) {
        this.nrr = nrr;
    }

    public ArrayList<String> getTitle() {
        return title;
    }

    public void setTitle(ArrayList<String> title) {
        this.title = title;
    }

    public ArrayList<String> getHeader() {
        return header;
    }

    public void setHeader(ArrayList<String> header) {
        this.header = header;
    }

    public PointsTableDataModel getGroup() {
        return group;
    }

    public void setGroup(PointsTableDataModel group) {
        this.group = group;
    }

    public ArrayList<PointsTableDataModel> getTeams() {
        return Teams;
    }

    public void setTeams(ArrayList<PointsTableDataModel> teams) {
        Teams = teams;
    }
}
