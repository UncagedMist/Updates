package com.tbc.uncagedmist.cricketupdates.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.model.pointtablemodel;

import java.util.ArrayList;

public class SeriesPointsTableAdapter extends RecyclerView.Adapter<SeriesPointsTableAdapter.ViewHolder> {
    Context context;
    ArrayList<pointtablemodel> arrayList;

    public SeriesPointsTableAdapter(Context context, ArrayList<pointtablemodel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_series_points_table, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt1.setText(arrayList.get(position).getTxt1());
        holder.txt2.setText(arrayList.get(position).getTxt2());
        holder.txt3.setText(arrayList.get(position).getTxt3());
        holder.txt4.setText(arrayList.get(position).getTxt4());
        holder.txt5.setText(arrayList.get(position).getTxt5());
        holder.txt6.setText(arrayList.get(position).getTxt6());
        holder.txt7.setText(arrayList.get(position).getTxt7());
        holder.txt8.setText(arrayList.get(position).getTxt8());
        holder.txt9.setText(arrayList.get(position).getTxt9());


        holder.titletxt1.setText(arrayList.get(position).getTitletxt1());
        holder.titletxt2.setText(arrayList.get(position).getTitletxt2());
        holder.titletxt3.setText(arrayList.get(position).getTitletxt3());
        holder.titletxt4.setText(arrayList.get(position).getTitletxt4());
        holder.titletxt5.setText(arrayList.get(position).getTitletxt5());
        holder.titletxt6.setText(arrayList.get(position).getTitletxt6());
        holder.titletxt7.setText(arrayList.get(position).getTitletxt7());
        holder.titletxt8.setText(arrayList.get(position).getTitletxt8());
        holder.titletxt9.setText(arrayList.get(position).getTitletxt9());
        holder.loutTitle.setVisibility(View.GONE);

        if (position==0){
            holder.loutTitle.setVisibility(View.VISIBLE);
        }
        else {

        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,titletxt1,titletxt2,titletxt3,titletxt4,titletxt5,titletxt6,titletxt7,titletxt8,titletxt9;
        ImageView imgload;
        LinearLayout loutTitle;

        ViewHolder(View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.txt1);
            txt2 = itemView.findViewById(R.id.txt2);
            txt3 = itemView.findViewById(R.id.txt3);
            txt4 = itemView.findViewById(R.id.txt4);
            txt5 = itemView.findViewById(R.id.txt5);
            txt6 = itemView.findViewById(R.id.txt6);
            txt7 = itemView.findViewById(R.id.txt7);
            txt8 = itemView.findViewById(R.id.txt8);
            txt9 = itemView.findViewById(R.id.txt9);
            titletxt1 = itemView.findViewById(R.id.txtTitle1);
            titletxt2 = itemView.findViewById(R.id.txtTitle2);
            titletxt3 = itemView.findViewById(R.id.txtTitle3);
            titletxt4 = itemView.findViewById(R.id.txtTitle4);
            titletxt5 = itemView.findViewById(R.id.txtTitle5);
            titletxt6 = itemView.findViewById(R.id.txtTitle6);
            titletxt7 = itemView.findViewById(R.id.txtTitle7);
            titletxt8 = itemView.findViewById(R.id.txtTitle8);
            titletxt9 = itemView.findViewById(R.id.txtTitle9);
            loutTitle = itemView.findViewById(R.id.loutTitle);
//            txt9 = itemView.findViewById(R.id.txt9);
        }
    }
}