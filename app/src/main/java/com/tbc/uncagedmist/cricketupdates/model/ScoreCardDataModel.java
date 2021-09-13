package com.tbc.uncagedmist.cricketupdates.model;

import java.util.ArrayList;

public class ScoreCardDataModel {
    String teamName,teamScore,playername,playerStatus,playerRun,playerBall,playerFours,playerSixes,playerStrikeRate;
    ArrayList<String> scoreboardTitle;
    ArrayList<ScoreCardDataModel> scorecardItems;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(String teamScore) {
        this.teamScore = teamScore;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public String getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(String playerStatus) {
        this.playerStatus = playerStatus;
    }

    public String getPlayerRun() {
        return playerRun;
    }

    public void setPlayerRun(String playerRun) {
        this.playerRun = playerRun;
    }

    public String getPlayerBall() {
        return playerBall;
    }

    public void setPlayerBall(String playerBall) {
        this.playerBall = playerBall;
    }

    public String getPlayerFours() {
        return playerFours;
    }

    public void setPlayerFours(String playerFours) {
        this.playerFours = playerFours;
    }

    public String getPlayerSixes() {
        return playerSixes;
    }

    public void setPlayerSixes(String playerSixes) {
        this.playerSixes = playerSixes;
    }

    public String getPlayerStrikeRate() {
        return playerStrikeRate;
    }

    public void setPlayerStrikeRate(String playerStrikeRate) {
        this.playerStrikeRate = playerStrikeRate;
    }

    public ArrayList<String> getScoreboardTitle() {
        return scoreboardTitle;
    }

    public void setScoreboardTitle(ArrayList<String> scoreboardTitle) {
        this.scoreboardTitle = scoreboardTitle;
    }

    public ArrayList<ScoreCardDataModel> getScorecardItems() {
        return scorecardItems;
    }

    public void setScorecardItems(ArrayList<ScoreCardDataModel> scorecardItems) {
        this.scorecardItems = scorecardItems;
    }
}
