package com.tbc.uncagedmist.cricketupdates.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.model.playerdetailmodel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayerdetailAdapter extends RecyclerView.Adapter<PlayerdetailAdapter.ViewHolder> {
    private final Activity activity;
    ArrayList<playerdetailmodel> arrayList;

    public PlayerdetailAdapter(Activity activity, ArrayList<playerdetailmodel> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.playerdetaillistview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.teamname.setText(arrayList.get(position).getImage());
        holder.playertype.setText(arrayList.get(position).getName());

        holder.playertitle.setText(arrayList.get(position).getNamebold());
        holder.image.setVisibility(View.GONE);

if (position==0){
    holder.image.setVisibility(View.VISIBLE);
    Glide.with(activity).load(arrayList.get(position).getImage()).into(holder.image);
}

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

   TextView teamname,playertype,playertitle;
   RelativeLayout fullrelative;
          CircleImageView image;

        ViewHolder(View itemView) {
            super(itemView);

            teamname = itemView.findViewById(R.id.playername);
            playertype = itemView.findViewById(R.id.playertype);
            playertitle = itemView.findViewById(R.id.playertitle);
            fullrelative = itemView.findViewById(R.id.fullrelative);
            image = itemView.findViewById(R.id.image);
        }
    }
}