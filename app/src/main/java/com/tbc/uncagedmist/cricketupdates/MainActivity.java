package com.tbc.uncagedmist.cricketupdates;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tbc.uncagedmist.cricketupdates.R;

import com.tbc.uncagedmist.cricketupdates.Tools.AppAdOrganizer;
import com.tbc.uncagedmist.cricketupdates.adapter.AllSeriesAdapter;
import com.tbc.uncagedmist.cricketupdates.adapter.NewsAdapter;
import com.tbc.uncagedmist.cricketupdates.adapter.WebthirdBean;
import com.tbc.uncagedmist.cricketupdates.model.seriesmodel;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static com.tbc.uncagedmist.cricketupdates.Tools.AppTimeHandler.appStructureBase;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RelativeLayout liveline;
    private RecyclerView rcSeriesList;
    private ProgressBar prbarSeries;
    private ArrayList<seriesmodel> seriesarrayList;

    Document document;
    private RecyclerView rcNewsList;
    String URL = "https://www.cricbuzz.com/cricket-news";
    private ArrayList<WebthirdBean> arrayList;
    private NestedScrollView scroller;
    private RelativeLayout progressbarmain;
    ImageView top;

    String PRIVACY_URL = "https://docs.google.com/document/d/16i0TI6ks2qgXdy70UczJoAlhrliwh8R2QxGqeHAPUT4/edit?usp=sharing";

    ReviewManager manager;
    ReviewInfo reviewInfo;

    private String seriesurl="https://m.cricbuzz.com/cricket-news/series";
    private AllSeriesAdapter seriesadapter;
    ProgressBar newsloaderjsoup;
    FrameLayout googleadViewNative;
    private NativeAdLayout nativeAdLayout;
    private UnifiedNativeAd nativeAd;
    private NativeAd fbnativeAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = ReviewManagerFactory.create(MainActivity.this);

        FirebaseApp.initializeApp(MainActivity.this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        googleadViewNative = findViewById(R.id.gooogleadViewNative);
        nativeAdLayout = findViewById(R.id.fbnative_ad_container);
        if (appStructureBase != null && appStructureBase.getFb_native_home() == 1) {

            fbnativeAd = new NativeAd(this, appStructureBase.getFb_native_id());
            NativeAdBase.NativeLoadAdConfig nativeLoadAdConfig = fbnativeAd
                    .buildLoadAdConfig()
                    .withAdListener(new NativeAdListener() {
                        @SuppressLint("LongLogTag")
                        @Override
                        public void onMediaDownloaded(Ad ad) {
                        }

                        @Override
                        public void onError(Ad ad, AdError adError) {
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {

                            try {

                                if (fbnativeAd == null || fbnativeAd != ad) {
                                    return;
                                }
                                AppAdOrganizer.getInstance().inflateAd(MainActivity.this, fbnativeAd, nativeAdLayout);
                                nativeAdLayout.setVisibility(View.VISIBLE);
                            } catch (Exception ignored) {

                            }
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                        }
                    }).build();

            fbnativeAd.loadAd(nativeLoadAdConfig);

        }
        else if (appStructureBase != null && appStructureBase.getGoogle_native_home() == 1) {

            AdLoader adLoader = new AdLoader.Builder(Objects.requireNonNull(this), appStructureBase.getNative_id())
                    .forUnifiedNativeAd(unifiedNativeAd -> {
                        googleadViewNative.setVisibility(View.VISIBLE);
                        try {
                            if (nativeAd != null) {
                                nativeAd.destroy();
                            }
                            nativeAd = unifiedNativeAd;
                            @SuppressLint("InflateParams")
                            UnifiedNativeAdView adView = (UnifiedNativeAdView) LayoutInflater.from(this).inflate(R.layout.ad_unified, null);
                            AppAdOrganizer.getInstance().loadAdMobNativeAd(unifiedNativeAd, adView);
                            googleadViewNative.removeAllViews();
                            googleadViewNative.addView(adView);
                        } catch (Exception ignored) {

                        }
                    }).build();
            adLoader.loadAd(new AdRequest.Builder().build());

        } else {
            nativeAdLayout.setVisibility(View.GONE);
            googleadViewNative.setVisibility(View.GONE);
        }



        liveline = findViewById(R.id.liveline);
        prbarSeries = findViewById(R.id.prbarSeries);
        progressbarmain = findViewById(R.id.progressbarmain);
        liveline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.tbc.uncagedmist.cricketupdates.liveline.MainActivity.class);
                try {
                    loadFBAd(MainActivity.this, intent);
                } catch (Exception ignored) {
                }
            }
        });
        newsloaderjsoup = findViewById(R.id.newsloaderjsoup);
        top = findViewById(R.id.top);
        rcSeriesList = findViewById(R.id.rcSeriesList);
        rcSeriesList.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        rcSeriesList.setVisibility(View.GONE);
        seriesarrayList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        scroller = findViewById(R.id.nestedscroller);

        rcNewsList = findViewById(R.id.rcNewsList);
        rcNewsList.setLayoutManager(layoutManager);
        rcNewsList.scrollToPosition(0);
        layoutManager.scrollToPositionWithOffset(0, 0);
        arrayList = new ArrayList<>();

        new seriesContent().execute();
        new Content().execute();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }
    private void loadFBAd(final Activity activity, final Intent intent) {
        try {

            if (appStructureBase.getFb_inter_option1() == 1) {
                final CustomProgressDialog customProgressDialog = new CustomProgressDialog(activity);
                customProgressDialog.setCancelable(false);
                customProgressDialog.show();

                AppAdOrganizer
                        .getInstance()
                        .getfbAdMobInstance(activity)
                        .buildLoadAdConfig()
                        .withAdListener(new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {
                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                long currentTimeInSecs = System.currentTimeMillis();

                                try {
                                    startActivity(intent);
                                } catch (Exception ignored) {
                                }
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {
                                long currentTimeInSecs = System.currentTimeMillis();

                                startActivity(intent);
                                customProgressDialog.dismiss();

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                try {
                                    customProgressDialog.dismiss();
                                    AppAdOrganizer.getInstance().getfbAdMobInstance(activity).show();
                                } catch (Exception ignored) {
                                }
                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                            }
                        })
                        .build();
                AppAdOrganizer.getInstance().getfbAdMobInstance(activity).loadAd();


            } else {
                loadAmbAd(activity, intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAmbAd(final Activity activity, final Intent intent) {
        if (appStructureBase.getGoogle_inter_option1() == 1) {
            if (AppAdOrganizer.getInstance().getAdMobInstance(activity).isLoaded()) {
                AppAdOrganizer.getInstance().getAdMobInstance(activity)
                        .setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                try {
                                    AppAdOrganizer.getInstance().getAdMobInstance(activity).setAdListener(new AdListener());
                                    AppAdOrganizer.getInstance().loadAdMobAd();

                                    startActivity(intent);
                                } catch (Exception ignored) {
                                }
                            }
                        });

                AppAdOrganizer.getInstance().getAdMobInstance(activity).show();
            } else {
                if (!AppAdOrganizer.getInstance().getAdMobInstance(activity).isLoading()) {
                    AppAdOrganizer.getInstance().getAdMobInstance(activity).setAdListener(new AdListener());
                    AppAdOrganizer.getInstance().loadAdMobAd();
                }
                activity.startActivity(intent);
            }
        } else {
            activity.startActivity(intent);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_rate) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName()));
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.nav_share) {

            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String body = "Let me Recommend you this application Tips and Tricks \n" + "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName();
            String sub = "Tips and Tricks ";
            myIntent.putExtra(Intent.EXTRA_SUBJECT, sub);
            myIntent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(myIntent, "Share Using"));
            // Handle the camera action

        } else if (id == R.id.nav_feedback) {
            feedback();
        } else if (id == R.id.nav_policy) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(PRIVACY_URL));
            startActivity(intent);

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class seriesContent extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(Void... voids) {
            try {
                document = Jsoup.connect(seriesurl).get();
                Elements allarray = document.select("div[class=\"list-group\"]");

                Elements allarray2 = allarray.select("a");

                for (int j = 0; j <allarray2.size(); j++) {
                    Elements titlefst = allarray2.select("div[class=\"col-xs-12 col-lg-12 dis-inline\"]").select("h3");
                    String titlescnd = titlefst.get(j).text();

                    String link = allarray2.get(j).attr("href");
                    seriesmodel seriesmodel = new seriesmodel();

//                        seriesmodel.setMoths(String.valueOf(monthsfinal));
                    seriesmodel.setTeamname(String.valueOf(titlescnd));
//                        seriesmodel.setDatetodate(String.valueOf(datetodatefinal3));
                    seriesmodel.setLink("https://m.cricbuzz.com/" +link);
//                        seriesmodel.setType("T20 League");
                    seriesarrayList.add(seriesmodel);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            seriesadapter = new AllSeriesAdapter(MainActivity.this, seriesarrayList);
            rcSeriesList.setAdapter(seriesadapter);
            rcSeriesList.setVisibility(View.VISIBLE);
            prbarSeries.setVisibility(View.GONE);
//            adapter.notifyDataSetChanged();

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
                document = Jsoup.connect(URL).get();
//                Elements alldata2 = document.select("div[class=cb-col cb-col-67 cb-nws-lft-col]");
//                Elements alldata = alldata2.select("div[id=news-list]");
                Elements allarray = document.select("div[class=cb-col cb-col-100 cb-lst-itm cb-lst-itm-lg]");


                String zzzz = document.select("div[class=\"ajax-pagination\"]").attr("url");
//                Elements yyyy = zzzz.select("div[class=ng-isolate-scope]");
                URL = "https://www.cricbuzz.com/" + zzzz;

                for (int j = 0; j < allarray.size(); j++) {
                    WebthirdBean webthirdBean = new WebthirdBean();
                    String captionhead2 = allarray.get(j).select("img").attr("src");
                    String captionhead3 = allarray.get(j).select("h2").text();
                    Elements aaaa = allarray.select("div[class=\"cb-nws-intr\"]");
                    String captionhead4 = aaaa.get(j).select("div").text();
                    Elements bbbb = allarray.select("div[class=cb-nws-time]");
                    String captionhead5 = bbbb.get(j).text();
                    String cccc = allarray.get(j).select("div").last().text();

                    Elements asdf = allarray.get(j).select("h2");
                    String nextlink = asdf.select("a").attr("href");

                    if (!(captionhead2 != null && captionhead2.trim().length() > 0)) {
                        captionhead2 = allarray.get(j).select("img").attr("source");
                    }
                    webthirdBean.setImage(String.valueOf("https://www.cricbuzz.com/" + captionhead2));
                    webthirdBean.setTypeofnews(String.valueOf(captionhead5));
                    webthirdBean.setMainheading(String.valueOf(captionhead3));
                    webthirdBean.setCaption(String.valueOf(captionhead4));
                    webthirdBean.setTime(String.valueOf(cccc));
                    webthirdBean.setLink(String.valueOf("https://www.cricbuzz.com" +nextlink));
                    arrayList.add(webthirdBean);
                }


                scroller.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                    if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                        if (allarray.size() < 200) {
//                            main_progress.setVisibility(VISIBLE);
//                            FetchLoginUserPost(tab_title_id, requestCount);
                            new Content().execute();
                            progressbarmain.setVisibility(View.VISIBLE);

                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            NewsAdapter adapter = new NewsAdapter(MainActivity.this, arrayList);
            rcNewsList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            progressbarmain.setVisibility(View.GONE);
            newsloaderjsoup.setVisibility(View.GONE);
            top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scroller.fullScroll(View.FOCUS_UP);
                }
            });
        }
    }

    private void feedback() {
        Task<ReviewInfo> request = manager.requestReviewFlow();

        request.addOnCompleteListener(task -> {
            if (task.isSuccessful())    {
                reviewInfo = task.getResult();

                Task<Void> flow = manager.launchReviewFlow(MainActivity.this,reviewInfo);

                flow.addOnSuccessListener(result -> {
                });
            }
            else {
                Toast.makeText(MainActivity.this, "ERROR...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}