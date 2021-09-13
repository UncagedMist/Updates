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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.Tools.AppAdOrganizer;
import com.tbc.uncagedmist.cricketupdates.adapter.gallaryadapter;
import com.tbc.uncagedmist.cricketupdates.model.gallarymodel;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeBannerAd;
//import com.cricket.live.line.adapter.gallaryadapter;
//import com.cricket.live.line.model.gallarymodel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static com.tbc.uncagedmist.cricketupdates.Tools.AppTimeHandler.appStructureBase;

public class SeriesDetailGalleryFragment extends AppCompatActivity {
    private ArrayList<gallarymodel> arrayList;
    private gallaryadapter adapter;
    RecyclerView recyclerView;
    Document document;
    LinearLayout nodataavailable;
    ProgressBar progress_bar;

    private String link;
    private String title;

    public static SeriesDetailGalleryFragment newInstance() {
        return new SeriesDetailGalleryFragment();
    }
    private NativeAdLayout fbnativeAdLayout;
    private NativeBannerAd fbnativeBannerAd;
    FrameLayout adrelative;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_series_detail);
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
            AppAdOrganizer.getInstance().loadAdMobBannerAd(SeriesDetailGalleryFragment.this, adViewAdaptiveBanner, AppAdOrganizer.getInstance().getAdSize(Objects.requireNonNull(SeriesDetailGalleryFragment.this), adViewAdaptiveBanner));
            adViewAdaptiveBanner.setVisibility(View.VISIBLE);
            adrelative.setVisibility(View.VISIBLE);
        }else {
            adViewAdaptiveBanner.setVisibility(View.GONE);
            fbnativeAdLayout.setVisibility(View.GONE);
            adrelative.setVisibility(View.GONE);
        }
        recyclerView = findViewById(R.id.rcSeriesDetailList);
        nodataavailable = findViewById(R.id.layoutNoData);
        progress_bar = findViewById(R.id.progress_bar);
        link = getIntent().getExtras().getString("link");
        title = getIntent().getExtras().getString("title");
        TextView titleprevious=findViewById(R.id.titleprevious);
        titleprevious.setText(title);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(SeriesDetailGalleryFragment.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
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

//        nodataavailable.setVisibility(View.GONE);
//        new Content().execute()
        getdata();
    }

    private void getdata() {
        try {
            document = Jsoup.connect(link + "/photos").get();
            Elements allarray = document.select("div[class=\"row cb-row\"]");
            Elements thead = allarray.select("div[class=\"cb-col-history col-xs-12 col-sm-12 col-md-12 col-lg-12\"]");


            if (thead.size() == 0) {
                nodataavailable.setVisibility(View.VISIBLE);
                progress_bar.setVisibility(View.GONE);
            }else {

                for (int i = 0; i < thead.size(); i++) {
                    gallarymodel gallarymodel = new gallarymodel();
                    Elements imagefst = thead.select("a").select("div[class=\"col-xs-4 col-sm-2 col-md-1 col-lg-1\"]");
                    String title = thead.get(i).select("a").attr("title");
                    String imagescnd = imagefst.get(i).select("img").attr("src");
                    String newString = imagescnd.replace("90x75", "500x350");
                    gallarymodel.setTitle(String.valueOf(title));
                    gallarymodel.setImage(String.valueOf("https:" + newString));
                    gallarymodel.setLink(String.valueOf("https:" + newString));
                    arrayList.add(gallarymodel);
                }

                adapter = new gallaryadapter(SeriesDetailGalleryFragment.this, arrayList);
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
                progress_bar.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
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
                document = Jsoup.connect(link + "/photos").get();
                Elements allarray = document.select("div[class=\"row cb-row\"]");
                Elements thead = allarray.select("div[class=\"cb-col-history col-xs-12 col-sm-12 col-md-12 col-lg-12\"]");


                for (int i = 0; i < thead.size(); i++) {
                    gallarymodel gallarymodel = new gallarymodel();
                    Elements imagefst = thead.select("a").select("div[class=\"col-xs-4 col-sm-2 col-md-1 col-lg-1\"]");
                    String title = thead.get(i).select("a").attr("title");
                    String imagescnd = imagefst.get(i).select("img").attr("src");
                    String newString = imagescnd.replace("90x75", "500x350");
                    gallarymodel.setTitle(String.valueOf(title));
                    gallarymodel.setImage(String.valueOf("https:" + newString));
                    gallarymodel.setLink(String.valueOf("https:" + newString));
                    arrayList.add(gallarymodel);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            adapter = new gallaryadapter(SeriesDetailGalleryFragment.this, arrayList);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            progress_bar.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();

        }
    }


}