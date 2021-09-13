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
import com.tbc.uncagedmist.cricketupdates.model.statsmodel;

import java.util.ArrayList;

public class SeriesStatesAdapter extends RecyclerView.Adapter<SeriesStatesAdapter.ViewHolder> {
    Context context;
    ArrayList<statsmodel> arrayList;

    public SeriesStatesAdapter(Context context, ArrayList<statsmodel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.statslistview, parent, false);
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

        holder.titletxt1.setText(arrayList.get(position).getTitle1());
        holder.titletxt2.setText(arrayList.get(position).getTitle2());
        holder.titletxt3.setText(arrayList.get(position).getTitle3());
        holder.titletxt4.setText(arrayList.get(position).getTitle4());
        holder.titletxt5.setText(arrayList.get(position).getTitle5());
        holder.titletxt6.setText(arrayList.get(position).getTitle6());

        holder.loutTitle.setVisibility(View.GONE);

        if (position==0){
            holder.loutTitle.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView playerimage;
        TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8,titletxt1,titletxt2,titletxt3,titletxt4,titletxt5,titletxt6,titletxt7,titletxt8;
        LinearLayout loutTitle;


       public ViewHolder(View itemView) {
            super(itemView);

           txt1 = itemView.findViewById(R.id.txt1);
           txt2 = itemView.findViewById(R.id.txt2);
           txt3 = itemView.findViewById(R.id.txt3);
           txt4 = itemView.findViewById(R.id.txt4);
           txt5 = itemView.findViewById(R.id.txt5);
           txt6 = itemView.findViewById(R.id.txt6);

           titletxt1 = itemView.findViewById(R.id.txtTitle1);
           titletxt2 = itemView.findViewById(R.id.txtTitle2);
           titletxt3 = itemView.findViewById(R.id.txtTitle3);
           titletxt4 = itemView.findViewById(R.id.txtTitle4);
           titletxt5 = itemView.findViewById(R.id.txtTitle5);
           titletxt6 = itemView.findViewById(R.id.txtTitle6);


           loutTitle = itemView.findViewById(R.id.loutTitle);






        }
    }
}