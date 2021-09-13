package com.tbc.uncagedmist.cricketupdates.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.Tools.AppAdOrganizer;
import com.tbc.uncagedmist.cricketupdates.adapter.MatchesInfoAdapter;
import com.tbc.uncagedmist.cricketupdates.adapter.ScoreCardBowlingAdapter;
import com.tbc.uncagedmist.cricketupdates.adapter.ScoreCardTableAdapter;
import com.tbc.uncagedmist.cricketupdates.model.GetMatchesScoreCardAsync;
import com.tbc.uncagedmist.cricketupdates.model.ResponseModel;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeBannerAd;

import java.util.Objects;

import static com.tbc.uncagedmist.cricketupdates.Tools.AppTimeHandler.appStructureBase;
//import com.cricket.live.line.rest.GetMatchesScoreCardAsync;
//import com.cricket.live.line.utils.AdUtils;
//import com.livematchscore.livecricketscore.utils.ExtraUtility;

public class MatchDetailActivity extends AppCompatActivity {
    private NativeAdLayout fbnativeAdLayout;
    private NativeBannerAd fbnativeBannerAd;
    FrameLayout adrelative;
    private AdView adView;
    private ProgressBar prbar;
    private RecyclerView rcScoreCard;
    private TextView txtFallofWicketsValue;
    private TextView txtFallofWicketsLabel;
    private TextView txtMatchTitle;
    private TextView txtInnings1Team;
    private TextView txtInnings1Score;
    private RecyclerView rcinnings1Bowl;
    private RecyclerView rcInnings2ScoreCard;
    private TextView txtInnings2FallofWicketsLabel;
    private TextView txtInnings2FallofWicketsValue;
    private RecyclerView rcinnings2Bowl;
    private TextView txtInnings2Team;
    private TextView txtInnings2Score;
    private RecyclerView rcMatchInfo;
    private RelativeLayout loutInnings1Header;
    private CardView loutInnings1ScoreRC;
    private RelativeLayout loutInnings1FallofHeader;
    private RelativeLayout loutInnings2header;
    private CardView loutInnings2ScoreRC;
    private RelativeLayout loutMatchInfoTitle;
    private CardView loutMatchInfo;
    private CardView loutFallOfWicketsValue;
    private CardView loutFallOfWicketsValue2;
    private CountDownTimer cTimer;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    private String matchId = "";
    public static ResponseModel responseModel;
    private ProgressDialog dialog;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        try {
            if (cTimer != null) {
                cTimer.cancel();
                cTimer = null;
            }
                finish();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private void setCountDownTimer() {
        if (cTimer != null) {
            cTimer.cancel();
            cTimer = null;
        }
        cTimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                mySwipeRefreshLayout.setRefreshing(true);
                getScoreBoardData(matchId);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);
        fbnativeAdLayout = findViewById(R.id.fbnative_banner_ad_container);
        adrelative = findViewById(R.id.adrelative);
        FrameLayout adViewAdaptiveBanner = findViewById(R.id.adViewAdaptiveBanner);

        if (appStructureBase.getFb_banner_series_detail() == 1) {
            fbnativeAdLayout.setVisibility(View.VISIBLE);
            adrelative.setVisibility(View.VISIBLE);
            adView = new AdView(this, appStructureBase.getFb_banner_id(), AdSize.BANNER_HEIGHT_50);
            fbnativeAdLayout.addView(adView);
            adView.loadAd();

        } else if(appStructureBase.getGoogle_banner_series_detail() == 1) {
            AppAdOrganizer.getInstance().loadAdMobBannerAd(MatchDetailActivity.this, adViewAdaptiveBanner, AppAdOrganizer.getInstance().getAdSize(Objects.requireNonNull(MatchDetailActivity.this), adViewAdaptiveBanner));
            adViewAdaptiveBanner.setVisibility(View.VISIBLE);
            adrelative.setVisibility(View.VISIBLE);
        }else {
            adViewAdaptiveBanner.setVisibility(View.GONE);
            fbnativeAdLayout.setVisibility(View.GONE);
            adrelative.setVisibility(View.GONE);
        }
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        }catch (Exception e){
            e.printStackTrace();
        }
        responseModel = new ResponseModel();
        setView();
    }

    private void setView() {
        mySwipeRefreshLayout = findViewById(R.id.swiperefresh);
        prbar = findViewById(R.id.prbar);
        loutInnings1Header = findViewById(R.id.loutInnings1Header);
        loutInnings1ScoreRC = findViewById(R.id.loutInnings1ScoreRC);
        loutInnings1FallofHeader = findViewById(R.id.loutInnings1FallofHeader);
        loutInnings2header = findViewById(R.id.loutInnings2header);
        loutInnings2ScoreRC = findViewById(R.id.loutInnings2ScoreRC);
        loutMatchInfoTitle = findViewById(R.id.loutMatchInfoTitle);
        loutMatchInfo = findViewById(R.id.loutMatchInfo);
        loutFallOfWicketsValue = findViewById(R.id.loutFallOfWicketsValue);
        loutFallOfWicketsValue2 = findViewById(R.id.loutFallOfWicketsValue2);
        txtMatchTitle = findViewById(R.id.txtMatchTitle);
        txtInnings1Team = findViewById(R.id.txtInnings1Team);
        txtInnings1Score = findViewById(R.id.txtInnings1Score);
        txtFallofWicketsValue = findViewById(R.id.txtFallofWicketsValue);
        txtFallofWicketsLabel = findViewById(R.id.txtFallofWicketsLabel);
        rcScoreCard = findViewById(R.id.rcScoreCard);
        rcinnings1Bowl = findViewById(R.id.rcinnings1Bowl);
        rcScoreCard.setLayoutManager(new LinearLayoutManager(MatchDetailActivity.this, RecyclerView.VERTICAL, false));
        rcinnings1Bowl.setLayoutManager(new LinearLayoutManager(MatchDetailActivity.this, RecyclerView.VERTICAL, false));
        txtInnings2Team = findViewById(R.id.txtInnings2Team);
        txtInnings2Score = findViewById(R.id.txtInnings2Score);
        rcInnings2ScoreCard = findViewById(R.id.rcInnings2ScoreCard);
        rcinnings2Bowl = findViewById(R.id.rcinnings2Bowl);
        rcInnings2ScoreCard.setLayoutManager(new LinearLayoutManager(MatchDetailActivity.this, RecyclerView.VERTICAL, false));
        rcinnings2Bowl.setLayoutManager(new LinearLayoutManager(MatchDetailActivity.this, RecyclerView.VERTICAL, false));
        txtInnings2FallofWicketsLabel = findViewById(R.id.txtInnings2FallofWicketsLabel);
        txtInnings2FallofWicketsValue = findViewById(R.id.txtInnings2FallofWicketsValue);
        rcMatchInfo = findViewById(R.id.rcMatchInfo);
        rcMatchInfo.setLayoutManager(new LinearLayoutManager(MatchDetailActivity.this, RecyclerView.VERTICAL, false));

        try {
            if (getIntent() != null && getIntent().getExtras() != null){
                matchId = getIntent().getExtras().getString("matchId");

                String matchName = getIntent().getExtras().getString("matchName");
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setDisplayShowTitleEnabled(true);
                    getSupportActionBar().setTitle(matchName);
                }
                getScoreBoardData(matchId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getScoreBoardData(String matchId){
        new GetMatchesScoreCardAsync(this, "https://www.cricbuzz.com/api/html/cricket-scorecard" + "/" + matchId).execute();
    }

    public void setScoreBoardData(ResponseModel responseModel) {
        prbar.setVisibility(View.GONE);

        if (responseModel != null) {
            try {
                if (responseModel.getMatchTitle() != null && responseModel.getMatchTitle().trim().length() > 0) {
                    txtMatchTitle.setVisibility(View.VISIBLE);
                    txtMatchTitle.setText(responseModel.getMatchTitle());
                } else {
                    txtMatchTitle.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //innings1
            try {
                if (responseModel.getTeam1Name() != null && responseModel.getTeam1Name().trim().length() > 0) {
                    loutInnings1Header.setVisibility(View.VISIBLE);
                    txtInnings1Team.setVisibility(View.VISIBLE);
                    txtInnings1Team.setText(responseModel.getTeam1Name());
                } else {
                    txtInnings1Team.setVisibility(View.GONE);
                    loutInnings1Header.setVisibility(View.GONE);
                }
                if (responseModel.getTeam1Score() != null && responseModel.getTeam1Score().trim().length() > 0) {
                    txtInnings1Score.setVisibility(View.VISIBLE);
                    txtInnings1Score.setText(responseModel.getTeam1Score());
                } else {
                    txtInnings1Score.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (responseModel.getInnings1() != null && responseModel.getInnings1().size() > 0) {
                    loutInnings1ScoreRC.setVisibility(View.VISIBLE);
                    ScoreCardTableAdapter scoreCardTableAdapter = new ScoreCardTableAdapter(MatchDetailActivity.this, responseModel.getInnings1());
                    rcScoreCard.setAdapter(scoreCardTableAdapter);
                } else {
                    loutInnings1ScoreRC.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (responseModel.getFallofWicketsLabel() != null && responseModel.getFallofWicketsLabel().trim().length() > 0) {
                    loutInnings1FallofHeader.setVisibility(View.VISIBLE);
                    txtFallofWicketsLabel.setVisibility(View.VISIBLE);
                    txtFallofWicketsLabel.setText(responseModel.getFallofWicketsLabel());
                } else {
                    loutInnings1FallofHeader.setVisibility(View.GONE);
                    txtFallofWicketsLabel.setVisibility(View.GONE);
                }
                if (responseModel.getFallofWicketsValue() != null && responseModel.getFallofWicketsValue().trim().length() > 0) {
                    txtFallofWicketsValue.setVisibility(View.VISIBLE);
                    loutFallOfWicketsValue.setVisibility(View.VISIBLE);
                    txtFallofWicketsValue.setText(responseModel.getFallofWicketsValue().replace("), ", ")\n"));
                } else {
                    txtFallofWicketsValue.setVisibility(View.GONE);
                    loutFallOfWicketsValue.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (responseModel.getBowlInnings1() != null) {
                    rcinnings1Bowl.setVisibility(View.VISIBLE);
                    ScoreCardBowlingAdapter scoreCardBowlingAdapter = new ScoreCardBowlingAdapter(MatchDetailActivity.this, responseModel.getBowlInnings1());
                    rcinnings1Bowl.setAdapter(scoreCardBowlingAdapter);
                } else {
                    rcinnings1Bowl.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            //innings2
            try {
                if (responseModel.getTeam2Name() != null && responseModel.getTeam2Name().trim().length() > 0) {
                    loutInnings2header.setVisibility(View.VISIBLE);
                    txtInnings2Team.setVisibility(View.VISIBLE);
                    txtInnings2Team.setText(responseModel.getTeam2Name());
                } else {
                    txtInnings2Team.setVisibility(View.GONE);
                    loutInnings2header.setVisibility(View.GONE);
                }
                if (responseModel.getTeam2Score() != null && responseModel.getTeam2Score().trim().length() > 0) {
                    txtInnings2Score.setVisibility(View.VISIBLE);
                    txtInnings2Score.setText(responseModel.getTeam2Score());
                } else {
                    txtInnings2Score.setVisibility(View.GONE);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                if (responseModel.getInnings2() != null && responseModel.getInnings2().size() > 0) {
                    loutInnings2ScoreRC.setVisibility(View.VISIBLE);
                    ScoreCardTableAdapter scoreCardTableAdapter = new ScoreCardTableAdapter(MatchDetailActivity.this, responseModel.getInnings2());
                    rcInnings2ScoreCard.setAdapter(scoreCardTableAdapter);
                } else {
                    loutInnings2ScoreRC.setVisibility(View.GONE);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                if (responseModel.getFallofInnings2WicketsLabel() != null && responseModel.getFallofInnings2WicketsLabel().trim().length() > 0) {
                    txtInnings2FallofWicketsLabel.setVisibility(View.VISIBLE);
                    txtInnings2FallofWicketsLabel.setText(responseModel.getFallofInnings2WicketsLabel());
                } else {
                    txtInnings2FallofWicketsLabel.setVisibility(View.GONE);
                }
                if (responseModel.getFallofInnings2WicketsValue() != null && responseModel.getFallofInnings2WicketsValue().trim().length() > 0) {
                    txtInnings2FallofWicketsValue.setVisibility(View.VISIBLE);
                    loutFallOfWicketsValue2.setVisibility(View.VISIBLE);
                    txtInnings2FallofWicketsValue.setText(responseModel.getFallofInnings2WicketsValue().replace("), ", ")\n"));
                } else {
                    txtInnings2FallofWicketsValue.setVisibility(View.GONE);
                    loutFallOfWicketsValue2.setVisibility(View.GONE);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                if (responseModel.getBowlInnings2() != null && responseModel.getBowlInnings2().size() > 0) {
                    rcinnings2Bowl.setVisibility(View.VISIBLE);
                    ScoreCardBowlingAdapter scoreCardBowlingAdapter = new ScoreCardBowlingAdapter(MatchDetailActivity.this, responseModel.getBowlInnings2());
                    rcinnings2Bowl.setAdapter(scoreCardBowlingAdapter);
                } else {
                    rcinnings2Bowl.setVisibility(View.GONE);
                }
            }catch (Exception e){
                e.printStackTrace();
            }


            //MatchInfo
            try {
                if (responseModel.getMatchInfos() != null && responseModel.getMatchInfos().size() > 0) {
                    loutMatchInfoTitle.setVisibility(View.VISIBLE);
                    loutMatchInfo.setVisibility(View.VISIBLE);
                    rcMatchInfo.setVisibility(View.VISIBLE);
                    MatchesInfoAdapter matchesInfoAdapter = new MatchesInfoAdapter(responseModel.getMatchInfos());
                    rcMatchInfo.setAdapter(matchesInfoAdapter);
                }
                else {
                    loutMatchInfoTitle.setVisibility(View.GONE);
                    loutMatchInfo.setVisibility(View.GONE);
                    rcMatchInfo.setVisibility(View.GONE);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            Toast.makeText(MatchDetailActivity.this, "Something went wrong. please try again", Toast.LENGTH_SHORT).show();
        }
        if (mySwipeRefreshLayout != null){
            mySwipeRefreshLayout.setRefreshing(false);
        }
        setCountDownTimer();
    }
}
