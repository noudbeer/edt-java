package com.example.edt_java;

import static android.content.ContentValues.TAG;
import static com.example.edt_java.CalendarUtils.daysInWeekArray;
import static com.example.edt_java.CalendarUtils.monthYearFromDate;
import static com.example.edt_java.Event.eventsList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.data.UnfoldingReader;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.property.DtStart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.DateTimeStamp;
import biweekly.property.RawProperty;
import biweekly.util.ICalDate;

public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        initWidgets();
        if (CalendarUtils.selectedDate == null)
            CalendarUtils.selectedDate = LocalDate.now();
        setWeekView();
        setEventList();
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);
    }

    private void setWeekView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdapter();
    }

    private void setEventList() {
        Scanner s = new Scanner(getResources().openRawResource(R.raw.ade));
        try {
            String fullContent = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.ade)));

            String line = reader.readLine();
            while (line != null)
            {
                fullContent += "\n" + line;
                line = reader.readLine();
            }
            ICalendar ical = Biweekly.parse(fullContent).first();

            for(int i=0; i< ical.getEvents().size(); i++)
            {
                VEvent event = ical.getEvents().get(i);
                String summary = event.getSummary().getValue();

                int dt_day_start = event.getDateStart().getValue().getDate();
                int dt_month_start = event.getDateStart().getValue().getMonth();
                int dt_year_start = (int) event.getDateStart().getValue().getYear(); // Ne donne pas la bonne année

                int dt_day_end = event.getDateEnd().getValue().getDay();
                int dt_month_end = event.getDateEnd().getValue().getMonth();
                int dt_year_end = (int) event.getDateEnd().getValue().getYear(); // Ne donne pas la bonne année

                String location = event.getLocation().getValue();

                int hourStart = event.getDateStart().getValue().getHours();
                int minuteStart = event.getDateStart().getValue().getMinutes();
                int secondStart = event.getDateStart().getValue().getSeconds();
                LocalTime timeStart = LocalTime.of(hourStart, minuteStart, secondStart);
                //Log.d(TAG, String.valueOf(timeStart));

                int hourEnd = event.getDateEnd().getValue().getHours();
                int minuteEnd = event.getDateEnd().getValue().getMinutes();
                int secondEnd = event.getDateEnd().getValue().getSeconds();
                LocalTime timeEnd = LocalTime.of(hourEnd, minuteEnd, secondEnd);

                Event e = new Event(summary, LocalDate.of(LocalDate.now().getYear(), dt_month_start, dt_day_start), LocalDate.of(LocalDate.now().getYear(), dt_month_end, dt_day_end), location, timeStart, timeEnd);
                Event.eventsList.add(e);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void previousWeekAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setEventAdapter();
    }

    private void setEventAdapter() {
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

    public void newEventAction(View view)
    {
        startActivity(new Intent(this, EventEditActivity.class));
    }

    public void monthViewAction(View view)
    {
        startActivity(new Intent(this, MonthViewActivity.class));
        finish();
    }
}