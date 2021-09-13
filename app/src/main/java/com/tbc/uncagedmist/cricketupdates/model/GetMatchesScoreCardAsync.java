package com.tbc.uncagedmist.cricketupdates.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;

//import com.livematchscore.livecricketscore.activity.MatchDetailActivity;

import com.tbc.uncagedmist.cricketupdates.activity.MatchDetailActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GetMatchesScoreCardAsync extends AsyncTask<String, Void, String> {

    private final String url;
    @SuppressLint("StaticFieldLeak")
    private final Activity activity;
    private Document document;

    public GetMatchesScoreCardAsync(Activity activity, String url){
        this.activity = activity;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... strings) {
        try{

            document = Jsoup.connect(url).timeout(60*1000).get();

            try {
                String matchTitle = document.select("div").get(0).text();
                MatchDetailActivity.responseModel.setMatchTitle(matchTitle);
            }catch (Exception e){
                e.printStackTrace();
            }
            //Innings1
            getInnings1();
            //innnings2
            getInnings2();

            ArrayList<ScoreCardTableModel> matchInfos = new ArrayList<>();
            try {
                Elements matchInfo = document.select("div[class=cb-col cb-col-100]");
                Elements infoDetail = matchInfo.select("div[class=cb-col cb-col-100 cb-font-13]");
                Elements infoList = infoDetail.select("div[class=cb-col cb-col-100 cb-mtch-info-itm]");

                for (int i = 0; i < infoList.size(); i++) {
                    try {
                        Elements subele = infoList.get(i).getAllElements();
                        String date = infoList.select("span").attr("timestamp");
                        String matchInfoTitle = subele.get(1).text();
                        String matchInfoValue = subele.get(2).text();






                        String pattern = "dd/MM/yyyy";
                        Date dateq = new Date(Long.parseLong(date));
                        DateFormat dateFormat=new SimpleDateFormat(pattern);
                        dateFormat.format(dateq);
                        String todayAsString = dateFormat.format(dateq);



                        String timepattern = "hh:mm:ss aa";
                        Date time = new Date(Long.parseLong(date));
                        DateFormat timeformat=new SimpleDateFormat(timepattern);
                        timeformat.format(time);
                        String timeasstring = timeformat.format(time);






                        ScoreCardTableModel scoreCardTableModel = new ScoreCardTableModel();
                        scoreCardTableModel.setMatchInfoTitle(matchInfoTitle);
                        scoreCardTableModel.setMatchInfoValue(matchInfoValue);

                        scoreCardTableModel.setDate(todayAsString);
                        scoreCardTableModel.setTime(timeasstring);

//                        scoreCardTableModel.setMatchInfoValue(matchInfoValue);
//                        scoreCardTableModel.setMatchInfoValue(matchInfoValue);
                        matchInfos.add(scoreCardTableModel);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                String matchInfoValue = "";
                String matchInfoTitle = "";
                Elements squodList = infoDetail.select("div[class=cb-col cb-col-100 cb-minfo-tm-nm]");

                for (int i = 0; i < squodList.size(); i++) {
                    Elements subele = squodList.get(i).getAllElements();
                    try {
                        matchInfoTitle = subele.get(1).text();
                    }catch (Exception e){
                        e.printStackTrace();
                        matchInfoTitle = subele.get(0).text();
                    }
                    try {
                        matchInfoValue = subele.get(0).text();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    ScoreCardTableModel scoreCardTableModel = new ScoreCardTableModel();
                    try{
                        if ((squodList.size() == 5 && i == 3) || (i == 2 && squodList.size() == 3 )){
                            ScoreCardTableModel scoreCardTableModel1 = new ScoreCardTableModel();
                            scoreCardTableModel1.setMatchInfoTitle(infoDetail.select("div[class=cb-col cb-col-100 cb-minfo-tm-nm cb-minfo-tm2-nm]").text());
                            scoreCardTableModel1.setMatchInfoValue("");
                            matchInfos.add(scoreCardTableModel1);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try {
                        if (!matchInfoTitle.contains("Bench") && !matchInfoTitle.contains("Squad")){
                            matchInfoTitle = "Playing XI";
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    scoreCardTableModel.setMatchInfoTitle(matchInfoTitle);
                    scoreCardTableModel.setMatchInfoValue(matchInfoValue.replace(matchInfoTitle,""));
                    matchInfos.add(scoreCardTableModel);
                }
                MatchDetailActivity.responseModel.setMatchInfos(matchInfos);
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    private void getInnings1(){
        try {
            //Innings1
            Elements innings_1 = document.select("div[id=innings_1]");
            Elements spanarray = innings_1.select("span");
            String team1Name = spanarray.get(0).text();
            String team1Score = spanarray.get(1).text();
            MatchDetailActivity.responseModel.setTeam1Name(team1Name);
            MatchDetailActivity.responseModel.setTeam1Score(team1Score);
            Elements scoreboardInningsMain = innings_1.select("div[class=cb-col cb-col-100 cb-ltst-wgt-hdr]");
            ArrayList<String> scoreboardTitles = new ArrayList<>();
            Elements scoreboardHeader = scoreboardInningsMain.select("div[class=cb-col cb-col-100 cb-scrd-sub-hdr cb-bg-gray]");
            Elements titles = scoreboardHeader.get(0).getAllElements();
            for (int i = 0; i < titles.size(); i++) {
                scoreboardTitles.add(titles.get(i).text());
            }
            ScoreCardTableModel scoreCardTableModel = new ScoreCardTableModel();
            if (scoreboardTitles.size() == 8){
                scoreCardTableModel.setPlayerName(scoreboardTitles.get(1));
                scoreCardTableModel.setPlayerStatus(scoreboardTitles.get(2));
                scoreCardTableModel.setPlayerRuns(scoreboardTitles.get(3));
                scoreCardTableModel.setPlayerBalls(scoreboardTitles.get(4));
                scoreCardTableModel.setPlayer4s(scoreboardTitles.get(5));
                scoreCardTableModel.setPlayer6s(scoreboardTitles.get(6));
                scoreCardTableModel.setPlayersr(scoreboardTitles.get(7));
            }
            Elements scoreboardItems = scoreboardInningsMain.get(0).select("div[class=cb-col cb-col-100 cb-scrd-itms]");
            ArrayList<ScoreCardTableModel> scoreCardItems = new ArrayList<>();
            scoreCardItems.add(scoreCardTableModel);

            for (int i = 0; i < scoreboardItems.size(); i++) {
                try {
                    String aaa = "";
                    try {
                        aaa = scoreboardItems.get(i).select("div[class=cb-col-73 cb-col]").text();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if (aaa.trim().length() == 0) {
                        String playername = scoreboardItems.get(i).select("a").text();
                        String playerStatus = scoreboardItems.get(i).select("span[class=text-gray]").text();
                        String playerRun = scoreboardItems.get(i).select("div[class=cb-col cb-col-8 text-right text-bold]").text();
                        Elements detail = scoreboardItems.get(i).select("div[class=cb-col cb-col-8 text-right]");
                        String playerBall = detail.get(0).text();
                        String playerFours = detail.get(1).text();
                        String playerStrikeRate = detail.get(2).text();
                        String playerSixes = "0";
                        try {
                            playerSixes = scoreboardItems.get(i).text().split(" ")[scoreboardItems.get(i).text().split(" ").length - 2];
                        } catch (Exception e) {
                            e.printStackTrace();
                            playerSixes = "0";
                        }
                        ScoreCardTableModel scoreCardTableModel1 = new ScoreCardTableModel();
                        scoreCardTableModel1.setPlayerName(playername);
                        scoreCardTableModel1.setPlayerStatus(playerStatus);
                        scoreCardTableModel1.setPlayerRuns(playerRun);
                        scoreCardTableModel1.setPlayerBalls(playerBall);
                        scoreCardTableModel1.setPlayer4s(playerFours);
                        scoreCardTableModel1.setPlayer6s(playerSixes);
                        scoreCardTableModel1.setPlayersr(playerStrikeRate);
                        scoreCardItems.add(scoreCardTableModel1);
                    }else {
                        String playername = "Yet To Bat";
                        ScoreCardTableModel scoreCardTableModel1 = new ScoreCardTableModel();
                        scoreCardTableModel1.setPlayerName(playername);
                        scoreCardTableModel1.setPlayerRuns(aaa);
                        scoreCardItems.add(scoreCardTableModel1);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    ScoreCardTableModel scoreCardTableModel1 = new ScoreCardTableModel();
                    scoreCardTableModel1.setPlayerName(scoreboardItems.get(i).getAllElements().get(1).text());
                    scoreCardTableModel1.setPlayerRuns(scoreboardItems.get(i).getAllElements().get(0).text().replace(scoreboardItems.get(i).getAllElements().get(1).text(),""));
                    scoreCardItems.add(scoreCardTableModel1);
                }
            }
            String scoreFallWicketsTitle = innings_1.select("div[class=cb-col cb-col-100 cb-scrd-sub-hdr cb-bg-gray text-bold]").text();
            String scoreFallWicketsarray = innings_1.select("div[class=cb-col cb-col-100 cb-col-rt cb-font-13]").text();
            MatchDetailActivity.responseModel.setInnings1(scoreCardItems);
            MatchDetailActivity.responseModel.setFallofWicketsLabel(scoreFallWicketsTitle);
            MatchDetailActivity.responseModel.setFallofWicketsValue(scoreFallWicketsarray);

            Element scoreboardBowlerItems = scoreboardInningsMain.get(1);
            ArrayList<String> scoreboardbowlTitles = new ArrayList<>();
            Elements scoreboardBowlHeader = scoreboardBowlerItems.select("div[class=cb-col cb-col-100 cb-scrd-sub-hdr cb-bg-gray]");
            Elements bowltitles = scoreboardBowlHeader.get(0).getAllElements();
            for (int i = 0; i < bowltitles.size(); i++) {
                scoreboardbowlTitles.add(bowltitles.get(i).text());
            }
            ScoreCardTableModel scoreCardTableModel1 = new ScoreCardTableModel();
            try {
                scoreCardTableModel1.setPlayerName(scoreboardbowlTitles.get(1));
                scoreCardTableModel1.setPlayerOvers(scoreboardbowlTitles.get(2));
                scoreCardTableModel1.setPlayerMaidens(scoreboardbowlTitles.get(3));
                scoreCardTableModel1.setPlayerRuns(scoreboardbowlTitles.get(4));
                scoreCardTableModel1.setPlayerWickets(scoreboardbowlTitles.get(5));
                scoreCardTableModel1.setPlayerNoballs(scoreboardbowlTitles.get(6));
                scoreCardTableModel1.setPlayerWides(scoreboardbowlTitles.get(7));
                scoreCardTableModel1.setPlayerEconomy(scoreboardbowlTitles.get(8));
            }catch (Exception e){
                e.printStackTrace();
            }

            ArrayList<ScoreCardTableModel> scoreCardbowlItems = new ArrayList<>();
            scoreCardbowlItems.add(scoreCardTableModel1);
            Elements scoreboardbowlerItems = scoreboardInningsMain.get(1).select("div[class=cb-col cb-col-100 cb-scrd-itms]");
            for (int i = 0; i < scoreboardbowlerItems.size(); i++) {
                try {
                    String playername = scoreboardbowlerItems.get(i).select("a").text();
                    String playerOvers = scoreboardbowlerItems.get(i).getAllElements().get(3).text();
                    String playerMaidens = scoreboardbowlerItems.get(i).getAllElements().get(4).text();
                    String playerRuns = scoreboardbowlerItems.get(i).getAllElements().get(5).text();
                    String playerWickets = scoreboardbowlerItems.get(i).getAllElements().get(6).text();
                    String playerNoBalls = scoreboardbowlerItems.get(i).getAllElements().get(7).text();
                    String playerWide = scoreboardbowlerItems.get(i).getAllElements().get(8).text();
                    String playerEco = scoreboardbowlerItems.get(i).getAllElements().get(9).text();
                    ScoreCardTableModel scoreCardTableModel2 = new ScoreCardTableModel();
                    scoreCardTableModel2.setPlayerName(playername);
                    scoreCardTableModel2.setPlayerOvers(playerOvers);
                    scoreCardTableModel2.setPlayerMaidens(playerMaidens);
                    scoreCardTableModel2.setPlayerRuns(playerRuns);
                    scoreCardTableModel2.setPlayerWickets(playerWickets);
                    scoreCardTableModel2.setPlayerWides(playerWide);
                    scoreCardTableModel2.setPlayerNoballs(playerNoBalls);
                    scoreCardTableModel2.setPlayerEconomy(playerEco);
                    scoreCardbowlItems.add(scoreCardTableModel2);
                }catch (Exception e){
                    e.printStackTrace();
                }
                MatchDetailActivity.responseModel.setBowlInnings1(scoreCardbowlItems);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getInnings2(){
        try {
            Elements innings_2 = document.select("div[id=innings_2]");
            Elements spanarray1 = innings_2.select("span");
            String team2Name = spanarray1.get(0).text();
            String team2Score = spanarray1.get(1).text();
            MatchDetailActivity.responseModel.setTeam2Name(team2Name);
            MatchDetailActivity.responseModel.setTeam2Score(team2Score);
            Elements scoreboardInnings2Main = innings_2.select("div[class=cb-col cb-col-100 cb-ltst-wgt-hdr]");
            ArrayList<String> scoreboardTitles2 = new ArrayList<>();
            Elements scoreboardHeader2 = scoreboardInnings2Main.select("div[class=cb-col cb-col-100 cb-scrd-sub-hdr cb-bg-gray]");
            Elements titles2 = scoreboardHeader2.get(0).getAllElements();
            for (int i = 0; i < titles2.size(); i++) {
                scoreboardTitles2.add(titles2.get(i).text());
            }
            ScoreCardTableModel scoreCardTableModel2 = new ScoreCardTableModel();
            if (scoreboardTitles2.size() == 8){
                scoreCardTableModel2.setPlayerName(scoreboardTitles2.get(1));
                scoreCardTableModel2.setPlayerStatus(scoreboardTitles2.get(2));
                scoreCardTableModel2.setPlayerRuns(scoreboardTitles2.get(3));
                scoreCardTableModel2.setPlayerBalls(scoreboardTitles2.get(4));
                scoreCardTableModel2.setPlayer4s(scoreboardTitles2.get(5));
                scoreCardTableModel2.setPlayer6s(scoreboardTitles2.get(6));
                scoreCardTableModel2.setPlayersr(scoreboardTitles2.get(7));
            }

            Elements scoreboardItems2 = scoreboardInnings2Main.get(0).select("div[class=cb-col cb-col-100 cb-scrd-itms]");
            ArrayList<ScoreCardTableModel> scoreCardItems2 = new ArrayList<>();
            scoreCardItems2.add(scoreCardTableModel2);

            for (int i = 0; i < scoreboardItems2.size(); i++) {
                try {
                    String aaa = "";
                    try {
                        aaa = scoreboardItems2.get(i).select("div[class=cb-col-73 cb-col]").text();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if (aaa.trim().length() == 0) {
                        String playername = scoreboardItems2.get(i).select("a").text();
                        String playerStatus = scoreboardItems2.get(i).select("span").text();

                        String playerRun = scoreboardItems2.get(i).select("div[class=cb-col cb-col-8 text-right text-bold]").text();
                        Elements detail = scoreboardItems2.get(i).select("div[class=cb-col cb-col-8 text-right]");
                        String playerBall = detail.get(0).text();
                        String playerFours = detail.get(1).text();
                        String playerStrikeRate = detail.get(2).text();
                        String playerSixes = "0";
                        try {
                            playerSixes = scoreboardItems2.get(i).text().split(" ")[scoreboardItems2.get(i).text().split(" ").length - 2];
                        } catch (Exception e) {
                            e.printStackTrace();
                            playerSixes = "0";
                        }
                        ScoreCardTableModel scoreCardTableModel3 = new ScoreCardTableModel();
                        scoreCardTableModel3.setPlayerName(playername);
                        scoreCardTableModel3.setPlayerStatus(playerStatus);
                        scoreCardTableModel3.setPlayerRuns(playerRun);
                        scoreCardTableModel3.setPlayerBalls(playerBall);
                        scoreCardTableModel3.setPlayer4s(playerFours);
                        scoreCardTableModel3.setPlayer6s(playerSixes);
                        scoreCardTableModel3.setPlayersr(playerStrikeRate);
                        scoreCardItems2.add(scoreCardTableModel3);
                    }else{
                        String playername = "Yet To Bat";
                        ScoreCardTableModel scoreCardTableModel3 = new ScoreCardTableModel();
                        scoreCardTableModel3.setPlayerName(playername);
                        scoreCardTableModel3.setPlayerRuns(aaa);
                        scoreCardItems2.add(scoreCardTableModel3);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    ScoreCardTableModel scoreCardTableModel3 = new ScoreCardTableModel();
                    scoreCardTableModel3.setPlayerName(scoreboardItems2.get(i).getAllElements().get(1).text());
                    scoreCardTableModel3.setPlayerRuns(scoreboardItems2.get(i).getAllElements().get(0).text().replace(scoreboardItems2.get(i).getAllElements().get(1).text(),""));
                    scoreCardItems2.add(scoreCardTableModel3);
                }
            }
            String scoreFallWicketsTitle2 = innings_2.select("div[class=cb-col cb-col-100 cb-scrd-sub-hdr cb-bg-gray text-bold]").text();
            String scoreFallWicketsarray2 = innings_2.select("div[class=cb-col cb-col-100 cb-col-rt cb-font-13]").text();
            MatchDetailActivity.responseModel.setInnings2(scoreCardItems2);
            MatchDetailActivity.responseModel.setFallofInnings2WicketsLabel(scoreFallWicketsTitle2);
            MatchDetailActivity.responseModel.setFallofInnings2WicketsValue(scoreFallWicketsarray2);

            Element scoreboardBowlerItems2 = scoreboardInnings2Main.get(1);
            ArrayList<String> scoreboardbowlTitles2 = new ArrayList<>();
            Elements scoreboardBowlHeader2 = scoreboardBowlerItems2.select("div[class=cb-col cb-col-100 cb-scrd-sub-hdr cb-bg-gray]");
            Elements bowltitles2 = scoreboardBowlHeader2.get(0).getAllElements();
            for (int i = 0; i < bowltitles2.size(); i++) {
                scoreboardbowlTitles2.add(bowltitles2.get(i).text());
            }
            ScoreCardTableModel scoreCardTableModel21 = new ScoreCardTableModel();
            try {
                scoreCardTableModel21.setPlayerName(scoreboardbowlTitles2.get(1));
                scoreCardTableModel21.setPlayerOvers(scoreboardbowlTitles2.get(2));
                scoreCardTableModel21.setPlayerMaidens(scoreboardbowlTitles2.get(3));
                scoreCardTableModel21.setPlayerRuns(scoreboardbowlTitles2.get(4));
                scoreCardTableModel21.setPlayerWickets(scoreboardbowlTitles2.get(5));
                scoreCardTableModel21.setPlayerNoballs(scoreboardbowlTitles2.get(6));
                scoreCardTableModel21.setPlayerWides(scoreboardbowlTitles2.get(7));
                scoreCardTableModel21.setPlayerEconomy(scoreboardbowlTitles2.get(8));
            }catch (Exception e){
                e.printStackTrace();
            }

            ArrayList<ScoreCardTableModel> scoreCardbowlItems2 = new ArrayList<>();
            scoreCardbowlItems2.add(scoreCardTableModel21);
            Elements scoreboardbowlerItems2 = scoreboardInnings2Main.get(1).select("div[class=cb-col cb-col-100 cb-scrd-itms]");
            for (int i = 0; i < scoreboardbowlerItems2.size(); i++) {
                try {
                    String playername = scoreboardbowlerItems2.get(i).select("a").text();
                    String playerOvers = scoreboardbowlerItems2.get(i).getAllElements().get(3).text();
                    String playerMaidens = scoreboardbowlerItems2.get(i).getAllElements().get(4).text();
                    String playerRuns = scoreboardbowlerItems2.get(i).getAllElements().get(5).text();
                    String playerWickets = scoreboardbowlerItems2.get(i).getAllElements().get(6).text();
                    String playerNoBalls = scoreboardbowlerItems2.get(i).getAllElements().get(7).text();
                    String playerWide = scoreboardbowlerItems2.get(i).getAllElements().get(8).text();
                    String playerEco = scoreboardbowlerItems2.get(i).getAllElements().get(9).text();
                    ScoreCardTableModel scoreCardTableModel22 = new ScoreCardTableModel();
                    scoreCardTableModel22.setPlayerName(playername);
                    scoreCardTableModel22.setPlayerOvers(playerOvers);
                    scoreCardTableModel22.setPlayerMaidens(playerMaidens);
                    scoreCardTableModel22.setPlayerRuns(playerRuns);
                    scoreCardTableModel22.setPlayerWickets(playerWickets);
                    scoreCardTableModel22.setPlayerWides(playerWide);
                    scoreCardTableModel22.setPlayerNoballs(playerNoBalls);
                    scoreCardTableModel22.setPlayerEconomy(playerEco);
                    scoreCardbowlItems2.add(scoreCardTableModel22);

                }catch (Exception e){
                    e.printStackTrace();
                }
                MatchDetailActivity.responseModel.setBowlInnings2(scoreCardbowlItems2);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        ((MatchDetailActivity)activity).setScoreBoardData(MatchDetailActivity.responseModel);
    }
}

