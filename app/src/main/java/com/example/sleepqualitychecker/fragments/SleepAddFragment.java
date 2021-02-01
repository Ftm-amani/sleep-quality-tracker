package com.example.sleepqualitychecker.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sleepqualitychecker.DB.SleepDao;
import com.example.sleepqualitychecker.DB.SleepDatabase;
import com.example.sleepqualitychecker.MainActivity;
import com.example.sleepqualitychecker.R;
import com.example.sleepqualitychecker.databinding.FragmentSleepAddBinding;
import com.example.sleepqualitychecker.model.SleepNight;

import java.util.List;


public class SleepAddFragment extends Fragment {


    FragmentSleepAddBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SleepDao dao = SleepDatabase.getInstance(getContext()).sleepDao();

        if (getArguments() != null) {
            SleepAddFragmentArgs sleepAddFragmentArgs = SleepAddFragmentArgs.fromBundle(getArguments());
            int quality = sleepAddFragmentArgs.getSleepQuality();

            if (quality != -1) {
                SleepNight toNight = dao.getToNight();
                toNight.setSleepQuality(quality);


                Runnable runnable= () -> dao.update(toNight);

                Thread thread= new Thread(runnable);
                thread.start();
            }
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_add, container, false);

        binding.btnStart.setOnClickListener(v ->
        {
            // save start
            SleepNight sleepNight = new SleepNight();



            dao.insert(sleepNight);
            binding.btnStart.setVisibility(View.INVISIBLE);
            binding.btnStop.setVisibility(View.VISIBLE);
            binding.imgHeader.setImageResource(R.drawable.sleep);
            ((MainActivity)getActivity()).changeToolbarColor(false);

        });

        binding.btnStop.setOnClickListener(v -> {
            // save stop

            SleepNight updateSleepNight = dao.getToNight();

            updateSleepNight.setStopTime(System.currentTimeMillis());
            dao.update(updateSleepNight);
            binding.btnStart.setVisibility(View.VISIBLE);
            binding.btnStop.setVisibility(View.INVISIBLE);
            binding.imgHeader.setImageResource(R.drawable.wake_up);
            ((MainActivity)getActivity()).changeToolbarColor(true);

            Navigation.findNavController(v).navigate(SleepAddFragmentDirections.actionSleepAddFragmentToQualityFragment());

        });
        List<SleepNight> data = dao.getAllNights();

        SleepAddAdapter adapter = new SleepAddAdapter(data, getContext(), (sleepNight, view) -> Navigation
                .findNavController(view)
                .navigate(SleepAddFragmentDirections
                        .actionSleepAddFragmentToSleepDetailDialogFragment(sleepNight)));

        binding.btnClear.setOnClickListener(v -> {
            dao.clear();

            data.clear();
            adapter.notifyDataSetChanged();

        });

        binding.rvNight.setLayoutManager(new GridLayoutManager(getContext(), 5, RecyclerView.VERTICAL, false));
        binding.rvNight.setAdapter(adapter);
        return binding.getRoot();
    }


}