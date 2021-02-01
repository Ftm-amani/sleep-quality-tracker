package com.example.sleepqualitychecker.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "daily_sleep_quality_table")
public class SleepNight implements Serializable {

    @PrimaryKey(autoGenerate = true)
    long nightId = 0;

    @ColumnInfo(name = "start_time_milli")
    long startTime = System.currentTimeMillis();

    @ColumnInfo(name = "end_time_milli")
    long stopTime = 0;

    @ColumnInfo(name = "quality_rating")
    int sleepQuality = -1;

    public long getNightId() {
        return nightId;
    }

    public void setNightId(long nightId) {
        this.nightId = nightId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }

    public int getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(int sleepQuality) {
        this.sleepQuality = sleepQuality;
    }
}
