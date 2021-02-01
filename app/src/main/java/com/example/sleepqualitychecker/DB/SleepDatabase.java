package com.example.sleepqualitychecker.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sleepqualitychecker.model.SleepNight;

@Database(entities = {SleepNight.class}, version = 1, exportSchema = false)
public abstract class SleepDatabase extends RoomDatabase {

    public abstract SleepDao sleepDao();

    public static volatile SleepDatabase INSTANCE = null;


    public static SleepDatabase getInstance(Context context) {

        synchronized (context) {
            SleepDatabase instance = INSTANCE;
            if (INSTANCE == null) {

                instance = Room.databaseBuilder(context.getApplicationContext(), SleepDatabase.class, "noteDatabase")
                        .fallbackToDestructiveMigration().allowMainThreadQueries()
                        .build();
                INSTANCE = instance;
            }
            return instance;

        }
    }
}


