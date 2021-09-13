package com.tbc.uncagedmist.cricketupdates.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.Tools.AppAdOrganizer;
import com.tbc.uncagedmist.cricketupdates.adapter.SeriesTeamAdapter;
import com.tbc.uncagedmist.cricketupdates.model.playermodel;
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

public class SeriesDetailTeamsFragment extends AppCompatActivity {
    private ArrayList<playermodel> arrayList;
    private SeriesTeamAdapter adapter;
    RecyclerView recyclerView;
    Document document;
    String link;
    ProgressBar newsloaderjsoup;

    LinearLayout nodataavailable;
    private String title;
    private NativeAdLayout fbnativeAdLayout;
    private NativeBannerAd fbnativeBannerAd;
    FrameLayout adrelative;
    private AdView adView;

    public static SeriesDetailTeamsFragment newInstance() {
        return new SeriesDetailTeamsFragment();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_series_detail_news);
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
            AppAdOrganizer.getInstance().loadAdMobBannerAd(SeriesDetailTeamsFragment.this, adViewAdaptiveBanner, AppAdOrganizer.getInstance().getAdSize(Objects.requireNonNull(SeriesDetailTeamsFragment.this), adViewAdaptiveBanner));
            adViewAdaptiveBanner.setVisibility(View.VISIBLE);
            adrelative.setVisibility(View.VISIBLE);
        }else {
            adViewAdaptiveBanner.setVisibility(View.GONE);
            fbnativeAdLayout.setVisibility(View.GONE);
            adrelative.setVisibility(View.GONE);
        }
        link = getIntent().getExtras().getString("link");
        title = getIntent().getExtras().getString("title");
        TextView titleprevious=findViewById(R.id.titleprevious);
        titleprevious.setText(title);
        recyclerView = findViewById(R.id.rcSeriesDetailList);
        newsloaderjsoup = findViewById(R.id.newsloaderjsoup);
        nodataavailable = findViewById(R.id.layoutNoData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SeriesDetailTeamsFragment.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<>();
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        new Content().execute();
getdata();







    }

    private void getdata() {
        try {
            document = Jsoup.connect(link+"/squads").get();
            Elements allarray = document.select("div[class=\"container container-fluid cb-container\"]");
            Elements allarray2 = allarray.select("div[class=\"row cb-row\"]");
            Elements allarray3 = allarray2.select("div[class=\"cb-squad-list\"]").select("a");
            if (allarray3.size() == 0) {
                nodataavailable.setVisibility(View.VISIBLE);
                newsloaderjsoup.setVisibility(View.GONE);
            }else {
                for (int i = 0; i < allarray3.size(); i++) {
                    playermodel playermodel = new playermodel();
                    Elements teamname = allarray.select("div[class=\"cb-squad-list\"]");
                    String teamnamea = teamname.get(i).select("a").attr("title");
                    String teamlink = teamname.get(i).select("a").attr("href");
                    playermodel.setTeamname(String.valueOf(teamnamea));
                    playermodel.setLinkkk(String.valueOf(teamlink));
                    arrayList.add(playermodel);

                }

                adapter = new SeriesTeamAdapter(SeriesDetailTeamsFragment.this, arrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                newsloaderjsoup.setVisibility(View.GONE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                document = Jsoup.connect(link+"/squads").get();
                Elements allarray = document.select("div[class=\"container container-fluid cb-container\"]");
                Elements allarray2 = allarray.select("div[class=\"row cb-row\"]");
                Elements allarray3 = allarray2.select("div[class=\"cb-squad-list\"]").select("a");

                  for (int i = 0; i < allarray3.size(); i++) {
                    playermodel playermodel = new playermodel();
                    Elements teamname = allarray.select("div[class=\"cb-squad-list\"]");
                    String teamnamea = teamname.get(i).select("a").attr("title");
                    String teamlink = teamname.get(i).select("a").attr("href");
                    playermodel.setTeamname(String.valueOf(teamnamea));
                    playermodel.setLinkkk(String.valueOf(teamlink));
                    arrayList.add(playermodel);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            adapter = new SeriesTeamAdapter(SeriesDetailTeamsFragment.this, arrayList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            newsloaderjsoup.setVisibility(View.GONE);

        }
    }



}