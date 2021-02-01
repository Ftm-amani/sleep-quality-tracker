package com.example.sleepqualitychecker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.sleepqualitychecker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        navController= Navigation.findNavController(this,R.id.nav_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerlayout);
        NavigationUI.setupWithNavController(binding.navigation, navController);
        changeToolbarColor(true);


    }


    public void changeToolbarColor(boolean isDay){

        if (isDay){
            binding.toolbar.setTitleTextColor(this.getResources().getColor(R.color.black));
        }else {
            binding.toolbar.setTitleTextColor(this.getResources().getColor(R.color.white));
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,binding.drawerlayout);
    }
}
