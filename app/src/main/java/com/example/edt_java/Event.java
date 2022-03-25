package com.example.edt_java;

import android.content.res.AssetFileDescriptor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {

    public static ArrayList<Event> eventsList = new ArrayList<>();

    public static ArrayList<Event> eventsForDate(LocalDate date)
    {
        ArrayList<Event> events = new ArrayList<>();

        for(Event event : eventsList)
        {
            if(event.getDt_start().equals(date))
                events.add(event);
        }

        return events;
    }

    /**
     * One event
     */
    private String summary;
    private LocalDate dt_start;
    private LocalDate dt_end;
    private String location;
    private LocalTime time_start;
    private LocalTime time_end;


    public Event(String summary, LocalDate dt_start, LocalDate dt_end, String location, LocalTime time_start, LocalTime time_end)
    {
        this.summary = summary;
        this.location = location;
        this.dt_start = dt_start;
        this.dt_end = dt_end;
        this.time_start = time_start;
        this.time_end = time_end;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getLocation()
    {
        return this.location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public LocalDate getDt_start()
    {
        return dt_start;
    }

    public void setDt_start(LocalDate dt_start)
    {
        this.dt_start = dt_start;
    }

    public LocalDate getDt_end()
    {
        return dt_end;
    }

    public void setDt_end(LocalDate dt_end)
    {
        this.dt_start = dt_end;
    }

    public LocalTime getTime_start()
    {
        return this.time_start;
    }

    public void setTime_start(LocalTime time_start)
    {
        this.time_start = time_start;
    }

    public LocalTime getTime_end()
    {
        return this.time_end;
    }

    public void setTime_end(LocalTime time_end)
    {
        this.time_end = time_end;
    }

}