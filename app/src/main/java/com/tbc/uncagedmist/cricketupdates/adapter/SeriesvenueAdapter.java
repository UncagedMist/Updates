package com.tbc.uncagedmist.cricketupdates.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.model.venuemodel;

import java.util.ArrayList;

public class SeriesvenueAdapter extends RecyclerView.Adapter<SeriesvenueAdapter.ViewHolder> {
    Context context;
    ArrayList<venuemodel> arrayList;

    public SeriesvenueAdapter(Context context, ArrayList<venuemodel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serieslistview, parent, false);
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
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

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

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView maincaption,maintitle;
        ImageView imgload;
        RelativeLayout layout;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgload = itemView.findViewById(R.id.imgload);
            maintitle = itemView.findViewById(R.id.maintitle);
            maincaption = itemView.findViewById(R.id.maincaption);
            layout = itemView.findViewById(R.id.layout);


        }
    }
}
