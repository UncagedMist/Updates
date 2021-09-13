package com.tbc.uncagedmist.cricketupdates.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.Tools.AppAdOrganizer;
import com.tbc.uncagedmist.cricketupdates.adapter.MatchesNewsAdapter;
import com.tbc.uncagedmist.cricketupdates.model.seriesnewsmodel;
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

public class SeriesDetailNewsFragment extends AppCompatActivity {
    private ArrayList<seriesnewsmodel> arrayList;
    private MatchesNewsAdapter adapter;
    RelativeLayout backk;
    RecyclerView recyclerView;
    //    AVLoadingIndicatorView ldg;
    private int posMain;
    TextView imgtxt;
    private RecyclerView rcNewsList;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;
    int itemCount = 0;
    Document document;
    TextView txtdemo;
    NestedScrollView scroller;
    ProgressBar progressbarmain;
    ImageView top;
    String link,title;

    LinearLayout layoutNoData;


    private NativeAdLayout fbnativeAdLayout;
    private NativeBannerAd fbnativeBannerAd;
    FrameLayout adrelative;
    private AdView adView;
    public static SeriesDetailNewsFragment newInstance() {
        return new SeriesDetailNewsFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentseriesnewsnew);
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
            AppAdOrganizer.getInstance().loadAdMobBannerAd(SeriesDetailNewsFragment.this, adViewAdaptiveBanner, AppAdOrganizer.getInstance().getAdSize(Objects.requireNonNull(SeriesDetailNewsFragment.this), adViewAdaptiveBanner));
            adViewAdaptiveBanner.setVisibility(View.VISIBLE);
            adrelative.setVisibility(View.VISIBLE);
        }else {
            adViewAdaptiveBanner.setVisibility(View.GONE);
            fbnativeAdLayout.setVisibility(View.GONE);
            adrelative.setVisibility(View.GONE);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(SeriesDetailNewsFragment.this);
        link = getIntent().getExtras().getString("link")+"/news";
        title = getIntent().getExtras().getString("title");
        TextView titleprevious=findViewById(R.id.titleprevious);
        titleprevious.setText(title);
//        recyclerView.setLayoutManager(new LinearLayoutManager(News.this, RecyclerView.VERTICAL, false));
        arrayList = new ArrayList<>();
        progressbarmain = findViewById(R.id.newsloaderjsoup);
        top = findViewById(R.id.top);

        scroller = findViewById(R.id.nestedscroller);
        layoutNoData = findViewById(R.id.layoutNoData);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rcNewsList = findViewById(R.id.rcSeriesDetailList);
        rcNewsList.setLayoutManager(layoutManager);
        rcNewsList.scrollToPosition(0);
        layoutManager.scrollToPositionWithOffset(0, 0);

//        new Content().execute();
getdata();
    }

    private void getdata() {
        try {
            document = Jsoup.connect(link).get();
//                Elements alldata2 = document.select("div[class=cb-col cb-col-67 cb-nws-lft-col]");
//                Elements alldata = alldata2.select("div[id=news-list]");
            Elements allarray = document.select("div[class=\"cb-col-history col-xs-12 col-sm-12 col-md-12 col-lg-12\"]");


            if (allarray.size() == 0) {
                layoutNoData.setVisibility(View.VISIBLE);
                progressbarmain.setVisibility(View.GONE);
            }else {
                for (int j = 0; j < allarray.size(); j++) {
                    seriesnewsmodel seriesnewsmodel = new seriesnewsmodel();
                    String captionhead2 = allarray.get(j).select("img").attr("src");
                    String captionhead3 = allarray.get(j).select("h3").text();
                    String cccc = allarray.get(j).select("h4").text();
                    Elements aaaa = allarray.select("div[class=\"cb-nws-intr\"]");
                    Elements asdf = allarray.get(j).select("h2");
                    String nextlink = allarray.get(j).select("a").attr("href");

                    if (!(captionhead2 != null && captionhead2.trim().length() > 0)) {
                        captionhead2 = allarray.get(j).select("img").attr("source");
                    }
                    String newString = captionhead2.replace("100x80", "300x240");
                    seriesnewsmodel.setImage(String.valueOf("https:"+newString));
                    seriesnewsmodel.setMainheading(String.valueOf(captionhead3));
                    seriesnewsmodel.setTime(String.valueOf(cccc));
                    seriesnewsmodel.setLink(String.valueOf("https://www.cricbuzz.com" +nextlink));
                    arrayList.add(seriesnewsmodel);
                }
                adapter = new MatchesNewsAdapter(SeriesDetailNewsFragment.this,SeriesDetailNewsFragment.this, arrayList);
                rcNewsList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressbarmain.setVisibility(View.GONE);

                top.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scroller.fullScroll(View.FOCUS_UP);

                    }
                });
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
                document = Jsoup.connect(link).get();
//                Elements alldata2 = document.select("div[class=cb-col cb-col-67 cb-nws-lft-col]");
//                Elements alldata = alldata2.select("div[id=news-list]");
                Elements allarray = document.select("div[class=\"cb-col-history col-xs-12 col-sm-12 col-md-12 col-lg-12\"]");


                for (int j = 0; j < allarray.size(); j++) {
                    seriesnewsmodel seriesnewsmodel = new seriesnewsmodel();

                    String captionhead2 = allarray.get(j).select("img").attr("src");


                    String captionhead3 = allarray.get(j).select("h3").text();
                    String cccc = allarray.get(j).select("h4").text();
                    Elements aaaa = allarray.select("div[class=\"cb-nws-intr\"]");


//                    String cccc = allarray.get(j).select("div").last().text();


                    Elements asdf = allarray.get(j).select("h2");
                    String nextlink = allarray.get(j).select("a").attr("href");

                    if (!(captionhead2 != null && captionhead2.trim().length() > 0)) {
                        captionhead2 = allarray.get(j).select("img").attr("source");
                    }

                    String newString = captionhead2.replace("100x80", "300x240");

                    seriesnewsmodel.setImage(String.valueOf("https:"+newString));
//                    seriesnewsmodel.setTypeofnews(String.valueOf(captionhead5));
                    seriesnewsmodel.setMainheading(String.valueOf(captionhead3));
//                    seriesnewsmodel.setCaption(String.valueOf(captionhead4));
                    seriesnewsmodel.setTime(String.valueOf(cccc));
                    seriesnewsmodel.setLink(String.valueOf("https://www.cricbuzz.com" +nextlink));
                    arrayList.add(seriesnewsmodel);
                }




            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            adapter = new MatchesNewsAdapter(SeriesDetailNewsFragment.this,SeriesDetailNewsFragment.this, arrayList);
            rcNewsList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            progressbarmain.setVisibility(View.GONE);

            top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scroller.fullScroll(View.FOCUS_UP);

                }
            });


        }
    }
    public static String removeLastChar(String str) {
        return removeLastChars(str, 7);
    }

    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }



}