package com.tbc.uncagedmist.cricketupdates.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.Tools.AppAdOrganizer;
import com.tbc.uncagedmist.cricketupdates.adapter.PlayerdetailAdapter;
import com.tbc.uncagedmist.cricketupdates.model.playerdetailmodel;
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

public class PlayerdetailActivity extends AppCompatActivity {
    String link;
    RecyclerView recyclerView;
    Document document;
    ProgressBar pbar;
    private PlayerdetailAdapter adapter;
    private ArrayList<playerdetailmodel> arrayList;

    private NativeAdLayout fbnativeAdLayout;
    private NativeBannerAd fbnativeBannerAd;
    FrameLayout adrelative;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerdetail);
        link = getIntent().getExtras().getString("playerdetaillink");
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
            AppAdOrganizer.getInstance().loadAdMobBannerAd(PlayerdetailActivity.this, adViewAdaptiveBanner, AppAdOrganizer.getInstance().getAdSize(Objects.requireNonNull(PlayerdetailActivity.this), adViewAdaptiveBanner));
            adViewAdaptiveBanner.setVisibility(View.VISIBLE);
            adrelative.setVisibility(View.VISIBLE);
        }else {
            adViewAdaptiveBanner.setVisibility(View.GONE);
            fbnativeAdLayout.setVisibility(View.GONE);
            adrelative.setVisibility(View.GONE);
        }
        recyclerView = findViewById(R.id.playerdetailrecycle);
        pbar = findViewById(R.id.pbar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<>();
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
                Elements allarray = document.select("div[class=\"cb-container container-fluid\"]");
                Elements allarray2 = allarray.select("div[class=\"cb-list-item \"]");
                Elements namebold = allarray2.select("div[class=\"col-xs-12 col-lg-12 dis-inline\"]").select("div[class=\"list-content\"]").select("div[class=\"table-responsive\"]").select("table[class=\"table table-condensed \"]").select("tbody").select("tr").select("td").select("b");
                Elements namevalue = allarray2.select("div[class=\"col-xs-12 col-lg-12 dis-inline\"]").select("div[class=\"list-content\"]").select("div[class=\"table-responsive\"]").select("table[class=\"table table-condensed \"]").select("tbody").select("tr");

                for (int i = 0; i <8 ; i++) {
                    String trialfst = namevalue.get(i).select("td").get(0).text();
                    String trialscnd = namevalue.get(i).select("td").get(1).text();
                    playerdetailmodel playerdetailmodel = new playerdetailmodel();
                    String image = allarray2.select("img").attr("src");
                    String newString = image.replace("192x192", "292x292");
                    playerdetailmodel.setImage(String.valueOf("https:"+newString));
                    playerdetailmodel.setNamebold(trialfst);
                    playerdetailmodel.setName(trialscnd);
                    arrayList.add(playerdetailmodel);
                }



            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            adapter = new PlayerdetailAdapter(PlayerdetailActivity.this, arrayList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            pbar.setVisibility(View.GONE);

        }
    }

}