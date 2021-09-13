package com.tbc.uncagedmist.cricketupdates.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.model.ScoreCardTableModel;

import java.util.ArrayList;

public class ScoreCardTableAdapter extends RecyclerView.Adapter<ScoreCardTableAdapter.ViewHolder> {
    private final Activity activity;
    private ArrayList<ScoreCardTableModel> data;

    public ScoreCardTableAdapter(Activity activity,ArrayList<ScoreCardTableModel> data) {
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_score_table, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (position == 0){
                holder.loutHeader.setBackgroundColor(activity.getResources().getColor(R.color.gray));
            }else {
                holder.loutHeader.setBackgroundColor(activity.getResources().getColor(R.color.white));
            }
            if (data.get(position).getPlayerName() != null) {
                holder.txtName.setVisibility(View.VISIBLE);
                holder.txtName.setText(data.get(position).getPlayerName());
            } else {
                holder.txtName.setVisibility(View.GONE);
            }
            if (data.get(position).getPlayerStatus() != null && data.get(position).getPlayerStatus().trim().length() > 0) {
                holder.txtStatus.setVisibility(View.VISIBLE);
                holder.txtStatus.setText(data.get(position).getPlayerStatus());
            } else {
                holder.txtStatus.setVisibility(View.GONE);
            }

            if (data.get(position).getPlayerRuns() != null) {
                holder.txtRun.setVisibility(View.VISIBLE);
                holder.txtRun.setText(data.get(position).getPlayerRuns());
            } else {
                holder.txtRun.setVisibility(View.GONE);
            }

            if (data.get(position).getPlayerBalls() != null) {
                holder.txtBall.setVisibility(View.VISIBLE);
                holder.txtBall.setText(data.get(position).getPlayerBalls());
            } else {
                holder.txtBall.setVisibility(View.GONE);
            }

            if (data.get(position).getPlayer4s() != null) {
                holder.txt4s.setVisibility(View.VISIBLE);
                holder.txt4s.setText(data.get(position).getPlayer4s());
            } else {
                holder.txt4s.setVisibility(View.GONE);
            }

            if (data.get(position).getPlayer6s() != null) {
                holder.txt6s.setVisibility(View.VISIBLE);
                holder.txt6s.setText(data.get(position).getPlayer6s());
            } else {
                holder.txt6s.setVisibility(View.GONE);
            }
            if (data.get(position).getPlayersr() != null) {
                holder.txtSR.setVisibility(View.VISIBLE);
                holder.txtSR.setText(data.get(position).getPlayersr());
            } else {
                holder.txtSR.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtName;
        private final TextView txtStatus;
        private final TextView txtRun;
        private final TextView txtBall;
        private final TextView txt4s;
        private final TextView txt6s;
        private final TextView txtSR;
        private final LinearLayout loutHeader;

        ViewHolder(View itemView) {
            super(itemView);
            loutHeader = itemView.findViewById(R.id.loutHeader);
            txtName = itemView.findViewById(R.id.txtName);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtRun = itemView.findViewById(R.id.txtRun);
            txtBall = itemView.findViewById(R.id.txtBall);
            txt4s = itemView.findViewById(R.id.txt4s);
            txt6s = itemView.findViewById(R.id.txt6s);
            txtSR = itemView.findViewById(R.id.txtSR);
        }
    }
}