package com.tbc.uncagedmist.cricketupdates.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
//import com.facebook.ads.Ad;
//import com.facebook.ads.AdError;
//import com.facebook.ads.InterstitialAdListener;
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
import com.tbc.uncagedmist.cricketupdates.CustomProgressDialog;
import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.Tools.AppAdOrganizer;
import com.tbc.uncagedmist.cricketupdates.activity.newsfullactivity;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;
//import com.cricket.live.line.utils.AdUtils;
//import com.cricket.live.line.utils.ExtraUtility;
//import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
//import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;

import java.util.ArrayList;

import com.tbc.uncagedmist.cricketupdates.Tools.AppTimeHandler;

//import static com.cricket.live.line.activity.StartActivity.appAdsData;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Activity context;
    ArrayList<WebthirdBean> arrayList;
    private ProgressDialog dialog;


    public NewsAdapter(Activity context, ArrayList<WebthirdBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weblistview, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


//        if (arrayList.get(position).getHeading() !=null && arrayList.get(position).getHeading().length() >0){
//            holder.layout1.setVisibility(View.GONE);
//            holder.layout.setVisibility(View.VISIBLE);
//            holder.heading.setText(arrayList.get(position).getHeading());
        Glide.with(context).load(arrayList.get(position).getImage()).into(holder.imgload);
            holder.maincaption.setText(arrayList.get(position).getCaption());
            holder.maintitle.setText(arrayList.get(position).getMainheading());
            holder.typeofnews.setText(arrayList.get(position).getTypeofnews());
            holder.captionsecond.setText(arrayList.get(position).getTime());



        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context, newsfullactivity.class);
                    intent.putExtra("link",arrayList.get(position).getLink());
                try {
                    loadFBAd(context, intent);
                } catch (Exception ignored) {
                }


            }
        });

//            holder.captionsecond.setText(arrayList.get(position).getCaption());
//        }
//
//        else {
//            holder.layout1.setVisibility(View.VISIBLE);
//            holder.layout.setVisibility(View.GONE);
//            holder.key.setText(arrayList.get(position).getKey());
//            holder.value.setText(arrayList.get(position).getValue());
//        }



    }
    private void loadFBAd(final Activity activity, final Intent intent) {
        try {

            if (AppTimeHandler.appStructureBase.getFb_inter_option1() == 1) {
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
        if (AppTimeHandler.appStructureBase.getGoogle_inter_option1() == 1) {
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
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView maincaption,captionsecond,maintitle,typeofnews;
        ImageView imgload;
        RelativeLayout layout;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgload = itemView.findViewById(R.id.imgload);
            maintitle = itemView.findViewById(R.id.maintitle);
            maincaption = itemView.findViewById(R.id.maincaption);
            captionsecond = itemView.findViewById(R.id.captionsecond);
            typeofnews = itemView.findViewById(R.id.typeofnews);
            layout = itemView.findViewById(R.id.layout);


        }
    }
}
