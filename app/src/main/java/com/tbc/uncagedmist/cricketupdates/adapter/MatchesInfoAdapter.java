package com.tbc.uncagedmist.cricketupdates.adapter;

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

public class MatchesInfoAdapter extends RecyclerView.Adapter<MatchesInfoAdapter.ViewHolder> {
    private ArrayList<ScoreCardTableModel> data;

    public MatchesInfoAdapter(ArrayList<ScoreCardTableModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_matches_info, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {

            if (data.get(position).getMatchInfoTitle() != null && data.get(position).getMatchInfoTitle().trim().length() > 0) {
                holder.txtName.setVisibility(View.VISIBLE);
                holder.txtName.setText(data.get(position).getMatchInfoTitle());
//                holder.datename.setText(data.get(position).getMatchInfoTitle());
//                holder.timename.setText(data.get(position).getMatchInfoTitle());
                if (position==1){
                    holder.dateValue.setText(data.get(position).getDate());
                    holder.datelinear.setVisibility(View.VISIBLE);
                    holder.txtName.setVisibility(View.GONE);
                    holder.txtValue.setVisibility(View.GONE);
                }else if (position==2){
                    holder.txtName.setVisibility(View.GONE);
                    holder.txtValue.setVisibility(View.GONE);
                    holder.timeValue.setText(data.get(position).getTime());
                    holder.timelinear.setVisibility(View.VISIBLE);

                }

            } else {
                holder.txtName.setVisibility(View.GONE);
            }
            if (data.get(position).getMatchInfoValue() != null && data.get(position).getMatchInfoValue().trim().length() > 0) {
                holder.txtValue.setVisibility(View.VISIBLE);
                holder.txtValue.setText(data.get(position).getMatchInfoValue());
                holder.datelinear.setVisibility(View.GONE);
                holder.timelinear.setVisibility(View.GONE);
                if (position==1){
                    holder.txtValue.setVisibility(View.GONE);
                    holder.txtName.setVisibility(View.GONE);
                    holder.dateValue.setText(data.get(position).getDate());
                    holder.datelinear.setVisibility(View.VISIBLE);
                }else if (position==2){
                    holder.txtValue.setVisibility(View.GONE);
                    holder.txtName.setVisibility(View.GONE);
                    holder.timeValue.setText(data.get(position).getTime());
                    holder.timelinear.setVisibility(View.VISIBLE);
                }

            } else {
                holder.txtValue.setVisibility(View.GONE);
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
        private final TextView txtValue;
        private final TextView datename;
        private final TextView dateValue;
        private final TextView timename;
        private final TextView timeValue;


        LinearLayout datelinear,timelinear;

        ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtValue = itemView.findViewById(R.id.txtValue);
            datename = itemView.findViewById(R.id.dateName);
            dateValue = itemView.findViewById(R.id.dateValue);
            timename = itemView.findViewById(R.id.timeName);
            timeValue = itemView.findViewById(R.id.timeValue);
            datelinear = itemView.findViewById(R.id.datelinear);
            timelinear = itemView.findViewById(R.id.timelinear);
        }
    }
}