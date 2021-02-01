package com.example.sleepqualitychecker.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sleepqualitychecker.model.SleepNight;

import java.util.List;

@Dao
public interface SleepDao {

    @Insert
    void insert(SleepNight sleepNight);

    @Update
    void update(SleepNight sleepNight);

    @Query("DELETE FROM daily_sleep_quality_table")
    void clear();

    @Query("SELECT * FROM daily_sleep_quality_table WHERE nightId = :id ORDER BY nightId DESC LIMIT 1")
    SleepNight getNightWithId(long id);

    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
    SleepNight getToNight();

    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")
    List<SleepNight> getAllNights();


}
