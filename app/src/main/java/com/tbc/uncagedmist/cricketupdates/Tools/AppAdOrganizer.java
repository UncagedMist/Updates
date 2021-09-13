package com.tbc.uncagedmist.cricketupdates.Tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tbc.uncagedmist.cricketupdates.R;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.KeyGenerator;

//import com.facebook.ads.AdIconView;

public class AppAdOrganizer {

    private final String Site_First_Link = "http://play.google.com/store/apps/details?id=";
    public static String DevName = "Facebook";
    private final String DevLink = "https://play.google.com/store/apps/developer?id=" + DevName;
    public static String DevMail = "lite-android-support@fb.com";

    private com.facebook.ads.InterstitialAd interstitialAd;

    private static InterstitialAd intertAdUid;
    private String mnkdr = "RiXj8UfVaJ6DXrHVlZ99QQ==";
    private String mckfe = "pckg";
    private String idkrld = "ZUrCLVDd4UReWl10MKBHXQ==";
    private String plkdr = "appvalley";
    private String oieirk = "flyindia";
    private String ierkdddr = "heop";
    private static AppAdOrganizer appAdOrganizer;

    public static AppAdOrganizer getInstance() {

        if (appAdOrganizer != null)
            return appAdOrganizer;
        else
            appAdOrganizer = new AppAdOrganizer();

        return appAdOrganizer;
    }


    public void initAdMobAd(Activity activity) {
        MobileAds.initialize(activity);
    }

    public InterstitialAd getAdMobInstance(Activity activity) {
        try {
            if (intertAdUid == null) {
                intertAdUid = new InterstitialAd(activity);
                if (AppTimeHandler.appStructureBase != null
                        && AppTimeHandler.appStructureBase.getInter_id() != null && !AppTimeHandler.appStructureBase.getInter_id().equals(""))
                    intertAdUid.setAdUnitId(AppTimeHandler.appStructureBase.getInter_id());
            }
        } catch (Exception ignored) {
        }
        return intertAdUid;
    }

    public void loadAdMobAd(){

        try {
            intertAdUid.loadAd(new AdRequest.Builder().build());
        } catch (Exception ignored) {

        }
    }
    public void loadAdMobBannerAd(Activity activity, final ViewGroup adLayout, AdSize adsize) {

        try {
            if (AppSocialTools.isConnected() && AppTimeHandler.appStructureBase != null
                    && AppTimeHandler.appStructureBase.getBanner_id() != null && !AppTimeHandler.appStructureBase.getBanner_id().equals("")) {
                final AdView adView = new AdView(activity);
                adView.setAdSize(adsize);
                adView.setAdUnitId(AppTimeHandler.appStructureBase.getBanner_id());
                adView.setAdListener(new AdListener() {

                    @Override
                    public void onAdLoaded() {
                        try {
                            if (adLayout.getChildCount() == 0)
                                adLayout.addView(adView);
                        } catch (Exception ignored) {
                        }
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        Log.e("===========error",loadAdError.getMessage()+"=");
                    }


                });
                adView.loadAd(new AdRequest.Builder().build());
            }
        } catch (Exception ignored) {
        }
    }
    public AdSize getAdSize(Activity activity, final ViewGroup adLayout) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = outMetrics.density;

        float adWidthPixels = adLayout.getWidth();

        if (adWidthPixels == 0) {
            adWidthPixels = outMetrics.widthPixels;
        }

        int adWidth = (int) (adWidthPixels / density);

        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }

    public void inflateAd(Activity activity, NativeAd fbnativeAd, NativeAdLayout nativeAdLayout) {
        fbnativeAd.unregisterView();
        LayoutInflater inflater = LayoutInflater.from(activity);
        RelativeLayout adView = (RelativeLayout) inflater.inflate(R.layout.native_ad_layoutmodify, nativeAdLayout, false);
        nativeAdLayout.addView(adView);
        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(activity, fbnativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);
        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);
        nativeAdTitle.setText(fbnativeAd.getAdvertiserName());
        nativeAdBody.setText(fbnativeAd.getAdBodyText());
        nativeAdSocialContext.setText(fbnativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(fbnativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(fbnativeAd.getAdCallToAction());
        sponsoredLabel.setText(fbnativeAd.getSponsoredTranslation());
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdCallToAction);
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdIcon);
        clickableViews.add(nativeAdSocialContext);
        clickableViews.add(nativeAdBody);
        fbnativeAd.registerViewForInteraction(
                adView,
                nativeAdMedia,
                nativeAdIcon,
                clickableViews);
    }



    @SuppressLint("LongLogTag")
    public void loadAdMobNativeAd(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {

        try {
            adView.setMediaView((com.google.android.gms.ads.formats.MediaView) adView.findViewById(R.id.ad_media));
            adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
            adView.setBodyView(adView.findViewById(R.id.ad_body));
            adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
            adView.setIconView(adView.findViewById(R.id.ad_app_icon));
            adView.setPriceView(adView.findViewById(R.id.ad_price));
            adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
            adView.setStoreView(adView.findViewById(R.id.ad_store));
            adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

            ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
            adView.getMediaView().setMediaContent(nativeAd.getMediaContent());
            if (nativeAd.getBody() == null) {
                adView.getBodyView().setVisibility(View.INVISIBLE);
            } else {
                adView.getBodyView().setVisibility(View.VISIBLE);
                ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
            }

            if (nativeAd.getCallToAction() == null) {
                adView.getCallToActionView().setVisibility(View.INVISIBLE);
            } else {
                adView.getCallToActionView().setVisibility(View.VISIBLE);
                ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());

            }

            if (nativeAd.getIcon() == null) {
                adView.getIconView().setVisibility(View.INVISIBLE);
            } else {
                ((ImageView) adView.getIconView()).setImageDrawable(
                        nativeAd.getIcon().getDrawable());
                adView.getIconView().setVisibility(View.VISIBLE);


            }

            if (nativeAd.getPrice() == null) {
                adView.getPriceView().setVisibility(View.GONE);
            } else {
                adView.getPriceView().setVisibility(View.GONE);
                ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());


            }

            if (nativeAd.getStore() == null) {
                adView.getStoreView().setVisibility(View.GONE);
            } else {
                adView.getStoreView().setVisibility(View.GONE);
                ((TextView) adView.getStoreView()).setText(nativeAd.getStore());


            }

            if (nativeAd.getStarRating() == null) {
                adView.getStarRatingView().setVisibility(View.GONE);
            } else {
                ((RatingBar) adView.getStarRatingView())
                        .setRating(nativeAd.getStarRating().floatValue());
                adView.getStarRatingView().setVisibility(View.GONE);

            }

            if (nativeAd.getAdvertiser() == null) {
                adView.getAdvertiserView().setVisibility(View.GONE);
            } else {
                ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
                adView.getAdvertiserView().setVisibility(View.VISIBLE);

            }

            adView.setNativeAd(nativeAd);


        } catch (Exception ignored) {
        }
    }



    public com.facebook.ads.InterstitialAd getfbAdMobInstance(Activity activity) {
        try {
            if (interstitialAd == null) {
                if (AppTimeHandler.appStructureBase != null
                        && AppTimeHandler.appStructureBase.getFb_inter_id() != null && !AppTimeHandler.appStructureBase.getFb_inter_id().equals(""))
                    interstitialAd = new com.facebook.ads.InterstitialAd(activity, AppTimeHandler.appStructureBase.getFb_inter_id());
            }
        } catch (Exception ignored) {

        }
        return interstitialAd;
    }

    public void loadAdFbAd(com.facebook.ads.InterstitialAd.InterstitialLoadAdConfig interstitialLoadAdConfig) {
        try {
            interstitialAd.loadAd(interstitialLoadAdConfig);
        } catch (Exception ignored) {
        }
    }



    public String getMckfe() {
        return mckfe;
    }

    public String getIerkdddr() {
        return ierkdddr;
    }

    String getMnkdr() {
        return mnkdr;
    }


    String getEncyption(String keyEnc, String textEnc) {

        try {
            byte[] keyStart = "encryption key".getBytes();
            KeyGenerator kgen;

            kgen = KeyGenerator.getInstance("AES");

            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(keyStart);
            kgen.init(128, sr);
            byte[] decryptedData = AppTimeHandler.decrypt(keyEnc, textEnc);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                return new String(decryptedData, StandardCharsets.UTF_8);
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                return new String(decryptedData, StandardCharsets.UTF_8);
            }

        } catch (NoSuchAlgorithmException ignored) {
        } catch (Exception ignored) {
        }
        return keyEnc;
    }

    String getIdkrld() {
        return idkrld;
    }


    String getOieirk() {
        return oieirk;
    }

    public String getFoied() {
        return AppTimeHandler.getInstance().getMcnjf() + plkdr + AppTimeHandler.getInstance().getPksdf();
    }
}
