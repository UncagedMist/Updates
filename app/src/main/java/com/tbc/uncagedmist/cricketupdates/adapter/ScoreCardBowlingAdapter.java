package com.tbc.uncagedmist.cricketupdates.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.livematchscore.livecricketscore.R;
//import com.livematchscore.livecricketscore.rest.ScoreCardTableModel;

import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.model.ScoreCardTableModel;

import java.util.ArrayList;

public class ScoreCardBowlingAdapter extends RecyclerView.Adapter<ScoreCardBowlingAdapter.ViewHolder> {
    private final Activity activity;
    private ArrayList<ScoreCardTableModel> data;

    public ScoreCardBowlingAdapter(Activity activity,ArrayList<ScoreCardTableModel> data) {
        this.data = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_score_bowl, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (position == 0) {
                holder.loutHeader.setBackgroundColor(activity.getResources().getColor(R.color.gray));
            } else {
                holder.loutHeader.setBackgroundColor(activity.getResources().getColor(R.color.white));
            }

            if (data.get(position).getPlayerName() != null) {
                holder.txtName.setVisibility(View.VISIBLE);
                holder.txtName.setText(data.get(position).getPlayerName());
            } else {
                holder.txtName.setVisibility(View.GONE);
            }
            if (data.get(position).getPlayerOvers() != null) {
                holder.txtOvers.setVisibility(View.VISIBLE);
                holder.txtOvers.setText(data.get(position).getPlayerOvers());
            } else {
                holder.txtOvers.setVisibility(View.GONE);
            }

            if (data.get(position).getPlayerMaidens() != null) {
                holder.txtMaidens.setVisibility(View.VISIBLE);
                holder.txtMaidens.setText(data.get(position).getPlayerMaidens());
            } else {
                holder.txtMaidens.setVisibility(View.GONE);
            }

            if (data.get(position).getPlayerRuns() != null) {
                holder.txtRuns.setVisibility(View.VISIBLE);
                holder.txtRuns.setText(data.get(position).getPlayerRuns());
            } else {
                holder.txtRuns.setVisibility(View.GONE);
            }

            if (data.get(position).getPlayerWickets() != null) {
                holder.txtWickets.setVisibility(View.VISIBLE);
                holder.txtWickets.setText(data.get(position).getPlayerWickets());
            } else {
                holder.txtWickets.setVisibility(View.GONE);
            }

            if (data.get(position).getPlayerNoballs() != null) {
                holder.txtNoball.setVisibility(View.VISIBLE);
                holder.txtNoball.setText(data.get(position).getPlayerNoballs());
            } else {
                holder.txtNoball.setVisibility(View.GONE);
            }
            if (data.get(position).getPlayerWides() != null) {
                holder.txtWide.setVisibility(View.VISIBLE);
                holder.txtWide.setText(data.get(position).getPlayerWides());
            } else {
                holder.txtWide.setVisibility(View.GONE);
            }

            if (data.get(position).getPlayerEconomy() != null) {
                holder.txtEconomy.setVisibility(View.VISIBLE);
                holder.txtEconomy.setText(data.get(position).getPlayerEconomy());
            } else {
                holder.txtEconomy.setVisibility(View.GONE);
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
        private final TextView txtOvers;
        private final TextView txtMaidens;
        private final TextView txtRuns;
        private final TextView txtWickets;
        private final TextView txtNoball;
        private final TextView txtWide;
        private final TextView txtEconomy;
        private final LinearLayout loutHeader;

        ViewHolder(View itemView) {
            super(itemView);
            loutHeader = itemView.findViewById(R.id.loutHeader);
            txtName = itemView.findViewById(R.id.txtName);
            txtOvers = itemView.findViewById(R.id.txtOvers);
            txtMaidens = itemView.findViewById(R.id.txtMaidens);
            txtRuns = itemView.findViewById(R.id.txtRuns);
            txtWickets = itemView.findViewById(R.id.txtWickets);
            txtNoball = itemView.findViewById(R.id.txtNoball);
            txtWide = itemView.findViewById(R.id.txtWide);
            txtEconomy = itemView.findViewById(R.id.txtEconomy);
        }
    }
}