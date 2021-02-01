package com.example.sleepqualitychecker.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sleepqualitychecker.R;
import com.example.sleepqualitychecker.databinding.FragmentSleepDetailDialogBinding;
import com.example.sleepqualitychecker.model.SleepNight;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SleepDetailDialogFragment extends DialogFragment {
    FragmentSleepDetailDialogBinding binding;
    SleepNight sleepNight;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (getArguments()!=null){
            sleepNight = SleepDetailDialogFragmentArgs.fromBundle(getArguments()).getSleepNight();
        }

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_detail_dialog, container, false);


        int emojiImage=sleepNight.getSleepQuality();
        switch (sleepNight.getSleepQuality()){
            case 1: emojiImage=R.drawable.e1; break;
            case 2: emojiImage=R.drawable.e2; break;
            case 3: emojiImage=R.drawable.e3; break;
            case 4: emojiImage=R.drawable.e4; break;
            case 5: emojiImage=R.drawable.e5; break;
            default: emojiImage=R.drawable.e6; break;
        }

        binding.imageView.setImageResource(emojiImage);


        binding.tvStartTime.setText(getDateWithTimeInMili(sleepNight.getStartTime()));
        binding.tvStopTime.setText(getDateWithTimeInMili(sleepNight.getStopTime()));

        binding.btnClose.setOnClickListener(v -> getDialog().dismiss());
        return binding.getRoot();


    }


    public String getDateWithTimeInMili(long mili) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mili);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");

        String date = simpleDateFormat.format(calendar.getTime());
        return date;
    }
}