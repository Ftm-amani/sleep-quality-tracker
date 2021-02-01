package com.example.sleepqualitychecker.fragments;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sleepqualitychecker.R;
import com.example.sleepqualitychecker.model.SleepNight;

import java.util.List;

public class SleepAddAdapter extends RecyclerView.Adapter<SleepAddAdapter.SleepViewHolder> {
    List<SleepNight> sleepNights;
    Context context;
    NightListener nightListener;

    public SleepAddAdapter(List<SleepNight> sleepNights,Context context,NightListener nightListener) {
        this.sleepNights = sleepNights;
        this.context=context;
        this.nightListener= nightListener;
    }


    @NonNull
    @Override
    public SleepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rv_item,parent,false);
        return new SleepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SleepViewHolder holder, int position) {
        SleepNight sleepNight= sleepNights.get(position);
        holder.tvNumber.setText(sleepNight.getSleepQuality()+"");

        int emojiImage = R.drawable.e1;
        switch (sleepNight.getSleepQuality()){
//            case 1: emojiImage = R.drawable.e1; break;
            case 2: emojiImage = R.drawable.e2; break;
            case 3: emojiImage = R.drawable.e3; break;
            case 4: emojiImage = R.drawable.e4; break;
            case 5: emojiImage = R.drawable.e5; break;
            case 6: emojiImage = R.drawable.e6; break;
            default:emojiImage = R.drawable.e1;
        }

        holder.img.setImageResource(emojiImage);
    }

    @Override
    public int getItemCount() {
        return sleepNights.size();
    }

    class SleepViewHolder extends RecyclerView.ViewHolder{
        TextView tvNumber;
        ImageView img;
        public SleepViewHolder(@NonNull View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.img_emoji);
            tvNumber=itemView.findViewById(R.id.tv_number);

            itemView.setOnClickListener(v -> {
                nightListener.clickNight(sleepNights.get(getAdapterPosition()),v);
            });
        }
    }


    public interface  NightListener{
        void clickNight(SleepNight sleepNight,View view);
    }

}

