package com.tbc.uncagedmist.cricketupdates.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tbc.uncagedmist.cricketupdates.CustomProgressDialog;
import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.Tools.AppAdOrganizer;
import com.tbc.uncagedmist.cricketupdates.activity.MatchDetailActivity;
//import com.cricket.live.line.activity.StartActivity;
import com.tbc.uncagedmist.cricketupdates.model.matchesmodel;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;
//import com.cricket.live.line.utils.AdUtils;

import java.util.ArrayList;

import com.tbc.uncagedmist.cricketupdates.Tools.AppTimeHandler;

public class AllSeriesMatchesAdapter extends RecyclerView.Adapter<AllSeriesMatchesAdapter.ViewHolder> {
    private final Activity activity;
    private ArrayList<matchesmodel> data;
//    private UnifiedNativeAd nativeAd;

    public AllSeriesMatchesAdapter(Activity activity, ArrayList<matchesmodel> data) {
        this.data = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_seriesallmatches, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.txtTitle.setText(data.get(position).getMatchname());
            holder.typeofmatch.setText(data.get(position).getMatchtype());
            holder.txtMatchDate.setText(data.get(position).getMatchdate());
            holder.matchstatus.setText(data.get(position).getMatchstatus());
            holder.time.setText(data.get(position).getTime());



            holder.fullrelative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, MatchDetailActivity.class);
                    intent.putExtra("matchId", data.get(position).getMatchid());
                    intent.putExtra("matchName", data.get(position).getMatchname());
                    try {
                        loadFBAd(activity, intent);
                    } catch (Exception ignored) {
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadFBAd(final Activity activity, final Intent intent) {
        try {

            if (AppTimeHandler.appStructureBase.getFb_inter_option3() == 1) {
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
        if (AppTimeHandler.appStructureBase.getGoogle_inter_option3() == 1) {
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
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtTitle;
        private final TextView typeofmatch;
        private final TextView txtMatchDate;
        private final TextView matchstatus;
        private final TextView time;
        private final RelativeLayout fullrelative;
        private final FrameLayout adViewNative;

        ViewHolder(View itemView) {
            super(itemView);
            txtMatchDate = itemView.findViewById(R.id.txtMatchDate);
            matchstatus = itemView.findViewById(R.id.matchstatus);
            time = itemView.findViewById(R.id.time);
            typeofmatch = itemView.findViewById(R.id.typeofmatch);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            fullrelative = itemView.findViewById(R.id.fullrelative);
            adViewNative = itemView.findViewById(R.id.adViewNative);

        }
    }
}