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
import com.tbc.uncagedmist.cricketupdates.adapter.SeriesvenueAdapter;
import com.tbc.uncagedmist.cricketupdates.model.venuemodel;
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

public class SeriesvenueFragment extends AppCompatActivity {
    private ArrayList<venuemodel> arrayList;
    private SeriesvenueAdapter adapter;
    RecyclerView recyclerView;
    ProgressBar newsloaderjsoup;
    LinearLayout layoutNoData;
//    String URL = "https://www.cricbuzz.com/cricket-series/2887/australia-tour-of-england-2020/venues";
    Document document;
    String link;
    private String title;
    private NativeAdLayout fbnativeAdLayout;
    private NativeBannerAd fbnativeBannerAd;
    FrameLayout adrelative;
    private AdView adView;
    public static SeriesvenueFragment newInstance() {
        return new SeriesvenueFragment();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_series_detail);
         link = getIntent().getExtras().getString("link");
        title = getIntent().getExtras().getString("title");
TextView titleprevious=findViewById(R.id.titleprevious);
        titleprevious.setText(title);
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
            AppAdOrganizer.getInstance().loadAdMobBannerAd(SeriesvenueFragment.this, adViewAdaptiveBanner, AppAdOrganizer.getInstance().getAdSize(Objects.requireNonNull(SeriesvenueFragment.this), adViewAdaptiveBanner));
            adViewAdaptiveBanner.setVisibility(View.VISIBLE);
            adrelative.setVisibility(View.VISIBLE);
        }else {
            adViewAdaptiveBanner.setVisibility(View.GONE);
            fbnativeAdLayout.setVisibility(View.GONE);
            adrelative.setVisibility(View.GONE);
        }
        recyclerView = findViewById(R.id.rcSeriesDetailList);
        newsloaderjsoup = findViewById(R.id.progress_bar);
        layoutNoData = findViewById(R.id.layoutNoData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SeriesvenueFragment.this);

        recyclerView.setLayoutManager(layoutManager);
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

        getdata();
//        new Content().execute();
    }

    private void getdata() {
        try {
            document = Jsoup.connect(link+"/venues").get();
            Elements allarray = document.select("div[class=\"row cb-row\"]");
            Elements allarray2 = allarray.select("div[class=\"col-sm-12 col-sm-12 col-md-12 col-lg-12 cb-col-news cb-col-matches\"]");
            Elements allarray3 = allarray.select("a[class=\"list-group-item cb-list-group-item\"]");




            if (allarray3.size() == 0) {
                layoutNoData.setVisibility(View.VISIBLE);
                newsloaderjsoup.setVisibility(View.GONE);
            }else {

                for (int i = 0; i < allarray3.size(); i++) {
                    venuemodel venuemodel = new venuemodel();
                    String imglink = allarray3.get(i).select("img").attr("src");
                    String titlefirst = allarray3.get(i).select("h3").text();
                    String titlesecond = allarray3.get(i).select("p").text();
                    venuemodel.setImage("https:" + imglink);
                    venuemodel.setMainheading(String.valueOf(titlefirst));
                    venuemodel.setCaption(String.valueOf(titlesecond));
                    arrayList.add(venuemodel);
                }
                adapter = new SeriesvenueAdapter(SeriesvenueFragment.this, arrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE);

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
                document = Jsoup.connect(link+"/venues").get();
                Elements allarray = document.select("div[class=\"row cb-row\"]");
                Elements allarray2 = allarray.select("div[class=\"col-sm-12 col-sm-12 col-md-12 col-lg-12 cb-col-news cb-col-matches\"]");
                Elements allarray3 = allarray.select("a[class=\"list-group-item cb-list-group-item\"]");


                for (int i = 0; i <allarray3.size() ; i++) {
                    venuemodel venuemodel = new venuemodel();
                    String imglink = allarray3.get(i).select("img").attr("src");
                    String titlefirst = allarray3.get(i).select("h3").text();
                    String titlesecond = allarray3.get(i).select("p").text();


                    venuemodel.setImage("https:"+imglink);
                    venuemodel.setMainheading(String.valueOf(titlefirst));
                    venuemodel.setCaption(String.valueOf(titlesecond));
                    arrayList.add(venuemodel);

                }







            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            adapter = new SeriesvenueAdapter(SeriesvenueFragment.this, arrayList);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
            newsloaderjsoup.setVisibility(View.GONE);
//            new Content2().execute();
//            ldg.setVisibility(View.GONE);

        }


    }


}