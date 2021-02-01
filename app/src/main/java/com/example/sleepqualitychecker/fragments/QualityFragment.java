package com.example.sleepqualitychecker.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sleepqualitychecker.R;
import com.example.sleepqualitychecker.databinding.FragmentQualityBinding;

public class QualityFragment extends Fragment implements View.OnClickListener {
    FragmentQualityBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_quality, container, false);

        binding.imgEmoj1.setOnClickListener(this);
        binding.imgEmoj2.setOnClickListener(this);
        binding.imgEmoj3.setOnClickListener(this);
        binding.imgEmoj4.setOnClickListener(this);
        binding.imgEmoj5.setOnClickListener(this);
        binding.imgEmoj6.setOnClickListener(this);

        return binding.getRoot();

    }

    @Override
    public void onClick(View v) {
        int sleepQuality =-1;
        switch (v.getId()){
            case R.id.img_emoj_1: sleepQuality=1; break;
            case R.id.img_emoj_2: sleepQuality=2; break;
            case R.id.img_emoj_3: sleepQuality=3; break;
            case R.id.img_emoj_4: sleepQuality=4; break;
            case R.id.img_emoj_5: sleepQuality=5; break;
            case R.id.img_emoj_6: sleepQuality=6; break;

        }
        Navigation.findNavController(v)
                .navigate(QualityFragmentDirections
                        .actionQualityFragmentToSleepAddFragment(sleepQuality));
    }
}