package com.tbc.uncagedmist.cricketupdates.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

//import com.facebook.ads.Ad;
//import com.facebook.ads.AdError;
//import com.facebook.ads.InterstitialAdListener;
//import com.facebook.ads.NativeAd;
//import com.facebook.ads.NativeAdLayout;
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdLoader;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.LoadAdError;
//import com.google.android.gms.ads.formats.UnifiedNativeAd;
//import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.tbc.uncagedmist.cricketupdates.CustomProgressDialog;
import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.Tools.AppAdOrganizer;
import com.tbc.uncagedmist.cricketupdates.fragments.SeriesDetailGalleryFragment;
import com.tbc.uncagedmist.cricketupdates.fragments.SeriesDetailMatchesFragment;
import com.tbc.uncagedmist.cricketupdates.fragments.SeriesDetailNewsFragment;
import com.tbc.uncagedmist.cricketupdates.fragments.SeriesDetailStatsFragment;
import com.tbc.uncagedmist.cricketupdates.fragments.SeriesDetailTeamsFragment;
import com.tbc.uncagedmist.cricketupdates.fragments.SeriesvenueFragment;
import com.tbc.uncagedmist.cricketupdates.model.DataModel;
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
//import com.cricket.live.line.rest.DataModel;
//import com.cricket.live.line.utils.AdUtils;
//import com.cricket.live.line.utils.ExtraUtility;
//import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
//import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;

import java.util.Objects;

import static com.tbc.uncagedmist.cricketupdates.Tools.AppTimeHandler.appStructureBase;

//import static com.cricket.live.line.activity.StartActivity.appAdsData;

public class SeriesOptionActivity extends AppCompatActivity {
    ImageView back;
//    private UnifiedNativeAd nativeAd;
    FrameLayout adViewNative;

    RelativeLayout matches,news,teams,points,gallary,venue;
    private ProgressDialog dialog;

    FrameLayout googleadViewNative;
    private NativeAdLayout nativeAdLayout;
    private UnifiedNativeAd nativeAd;
    private NativeAd fbnativeAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_option);



        googleadViewNative = findViewById(R.id.gooogleadViewNative);
        nativeAdLayout = findViewById(R.id.fbnative_ad_container);
        if (appStructureBase != null && appStructureBase.getFb_native_series_option() == 1) {

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
                                AppAdOrganizer.getInstance().inflateAd(SeriesOptionActivity.this, fbnativeAd, nativeAdLayout);
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
        else if (appStructureBase != null && appStructureBase.getGoogle_native_series_option() == 1) {

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


        back=findViewById(R.id.back);
        matches=findViewById(R.id.matches);
        news=findViewById(R.id.news);
        teams=findViewById(R.id.teams);
        points=findViewById(R.id.points);
        gallary=findViewById(R.id.gallary);
        venue=findViewById(R.id.venue);
        DataModel data = (DataModel) getIntent().getExtras().getSerializable("data");
        String title = getIntent().getExtras().getString("title");


        String link = getIntent().getExtras().getString("link");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        matches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeriesOptionActivity.this, SeriesDetailMatchesFragment.class);
                intent.putExtra("link",link);
                intent.putExtra("title","Matches");
                try {
                    loadFBAd(SeriesOptionActivity.this, intent);
                } catch (Exception ignored) {
                }
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeriesOptionActivity.this, SeriesDetailNewsFragment.class);
                intent.putExtra("link",link);
                intent.putExtra("title","News");
                try {
                    loadFBAd(SeriesOptionActivity.this, intent);
                } catch (Exception ignored) {
                }
            }
        });

        teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeriesOptionActivity.this, SeriesDetailTeamsFragment.class);
                intent.putExtra("link",link);
                intent.putExtra("title","Teams");
                try {
                    loadFBAd(SeriesOptionActivity.this, intent);
                } catch (Exception ignored) {
                }
            }
        });

        points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeriesOptionActivity.this, SeriesDetailStatsFragment.class);
                intent.putExtra("link",link);
                intent.putExtra("title","Points");
                try {
                    loadFBAd(SeriesOptionActivity.this, intent);
                } catch (Exception ignored) {
                }
            }
        });

        gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeriesOptionActivity.this, SeriesDetailGalleryFragment.class);
                intent.putExtra("link",link);
                intent.putExtra("title","Gallary");
                try {
                    loadFBAd(SeriesOptionActivity.this, intent);
                } catch (Exception ignored) {
                }

            }
        });

        venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeriesOptionActivity.this, SeriesvenueFragment.class);
                intent.putExtra("link",link);
                intent.putExtra("title","Venue");
                try {
                    loadFBAd(SeriesOptionActivity.this, intent);
                } catch (Exception ignored) {
                }


            }
        });








    }


    private void loadFBAd(final Activity activity, final Intent intent) {
        try {

            if (appStructureBase.getFb_inter_option2() == 1) {
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
                                    activity.startActivity(intent);
                                } catch (Exception ignored) {
                                }
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {
                                long currentTimeInSecs = System.currentTimeMillis();

                                activity.startActivity(intent);
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
        if (appStructureBase.getGoogle_inter_option2() == 1) {
            if (AppAdOrganizer.getInstance().getAdMobInstance(activity).isLoaded()) {
                AppAdOrganizer.getInstance().getAdMobInstance(activity)
                        .setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                try {
                                    AppAdOrganizer.getInstance().getAdMobInstance(activity).setAdListener(new AdListener());
                                    AppAdOrganizer.getInstance().loadAdMobAd();

                                    activity.startActivity(intent);
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
}