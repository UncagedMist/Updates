package com.tbc.uncagedmist.cricketupdates.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tbc.uncagedmist.cricketupdates.CustomProgressDialog;
import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.Tools.AppAdOrganizer;
import com.tbc.uncagedmist.cricketupdates.activity.PlayerNameActivity;
import com.tbc.uncagedmist.cricketupdates.model.playermodel;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;

import java.util.ArrayList;

import com.tbc.uncagedmist.cricketupdates.Tools.AppTimeHandler;

public class SeriesTeamAdapter extends RecyclerView.Adapter<SeriesTeamAdapter.ViewHolder> {
    private final Activity activity;
    ArrayList<playermodel> arrayList;

    public SeriesTeamAdapter(Activity activity, ArrayList<playermodel> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_series_teams, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.teamname.setText(arrayList.get(position).getTeamname());

        holder.fullrelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PlayerNameActivity.class);
                intent.putExtra("teamlink","https://m.cricbuzz.com/"+arrayList.get(position).getLinkkk());
                try {
                    loadFBAd(activity, intent);
                } catch (Exception ignored) {
                }
            }
        });
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
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
   TextView teamname;
   RelativeLayout fullrelative;

        ViewHolder(View itemView) {
            super(itemView);

            teamname = itemView.findViewById(R.id.playername);
            fullrelative = itemView.findViewById(R.id.fullrelative);
        }
    }
}