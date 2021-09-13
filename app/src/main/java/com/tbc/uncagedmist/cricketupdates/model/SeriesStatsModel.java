package com.tbc.uncagedmist.cricketupdates.model;

import androidx.annotation.Keep;

import java.util.ArrayList;

@Keep
public class SeriesStatsModel {
    String name;
    ArrayList<String> title;
    ArrayList<String> id;
    ArrayList<ScoreModel> stats;

    public ArrayList<String> getId() {
        return id;
    }

    public void setId(ArrayList<String> id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTitle() {
        return title;
    }

    public void setTitle(ArrayList<String> title) {
        this.title = title;
    }

    public ArrayList<ScoreModel> getStats() {
        return stats;
    }

    public void setStats(ArrayList<ScoreModel> stats) {
        this.stats = stats;
    }
}
