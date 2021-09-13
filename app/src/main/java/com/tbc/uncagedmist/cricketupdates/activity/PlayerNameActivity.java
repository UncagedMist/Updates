package com.tbc.uncagedmist.cricketupdates.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.Tools.AppAdOrganizer;
import com.tbc.uncagedmist.cricketupdates.adapter.PlayernameAdapter;
import com.tbc.uncagedmist.cricketupdates.model.playernamemodel;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeBannerAd;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static com.tbc.uncagedmist.cricketupdates.Tools.AppTimeHandler.appStructureBase;

public class PlayerNameActivity extends AppCompatActivity {
    String link;
    ImageView back;
    ProgressBar progressbar;
    private ArrayList<playernamemodel> arrayList;
    private PlayernameAdapter adapter;
    RecyclerView recyclerView;
    Document document;
    private NativeAdLayout fbnativeAdLayout;
    private NativeBannerAd fbnativeBannerAd;
    FrameLayout adrelative;
    private AdView adView;
    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);
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
            AppAdOrganizer.getInstance().loadAdMobBannerAd(PlayerNameActivity.this, adViewAdaptiveBanner, AppAdOrganizer.getInstance().getAdSize(Objects.requireNonNull(PlayerNameActivity.this), adViewAdaptiveBanner));
            adViewAdaptiveBanner.setVisibility(View.VISIBLE);
            adrelative.setVisibility(View.VISIBLE);
        }else {
            adViewAdaptiveBanner.setVisibility(View.GONE);
            fbnativeAdLayout.setVisibility(View.GONE);
            adrelative.setVisibility(View.GONE);
        }
        back=findViewById(R.id.back);
        progressbar=findViewById(R.id.progressbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        link = getIntent().getExtras().getString("teamlink");
        recyclerView = findViewById(R.id.playernamerecycle);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<>();
        progressbar.setVisibility(View.VISIBLE);
        new Content().execute();





    }

    class Content extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(Void... voids) {
            try {
                document = Jsoup.connect(link).get();
                Elements allarray = document.select("div[class=\"col-sm-12 col-sm-12 col-md-12 col-lg-12 cb-col-news cb-col-matches\"]");
                Elements allarray2 = allarray.select("div[class=\"cb-squad-list\"]");

                for (int i = 0; i < allarray2.size(); i++) {
                    playernamemodel playernamemodel = new playernamemodel();
                    Elements teamname2 = allarray.select("a[class=\"list-group-item cb-list-group-item\"]");
                    Elements teamname = teamname2.select("div[class=\"col-xs-8 col-lg-11 cb-col-text-container\"]");


                    String playerdetaillink = teamname2.get(i).attr("href");
                    String playername = teamname.get(i).select("h3").text();
                    String playertype = teamname.get(i).select("p").text();



                    playernamemodel.setPlayername(String.valueOf(playername));
                    playernamemodel.setPlayertype(String.valueOf(playertype));
                    playernamemodel.setPlayerdetaillink(String.valueOf(playerdetaillink));
                    arrayList.add(playernamemodel);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            adapter = new PlayernameAdapter(PlayerNameActivity.this, arrayList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            progressbar.setVisibility(View.GONE);

        }
    }

}