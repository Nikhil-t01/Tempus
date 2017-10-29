package com.teamenrgy.tempus;

import android.content.Intent;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Class used to represent TimeTable.
 * Gets the lecture timings of all courses from database and generates a time table accordingly.
 * However, there are some bugs in this timetable, for example the user can only view upto the next week in the timetable.
 * This WeekView module for implementing calendar has been picked up from GitHub
 */
public class TimeTableActivity extends TableActivity {
    String courses;
    List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    int l;

    /**
     * Function for adding events dynamically to the database
     * @param newYear Year in which event is being added
     * @param newMonth Month in which event is being added
     * @return List of all events to be added in the calendar
     */
    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        Intent oldintent = getIntent();
        courses = oldintent.getStringExtra("courses");
        String Days = "";
        l = courses.length();

        // Calendar startTime, endTime;
        // WeekViewEvent event;
        final int[] i = {0};

        HashMap<Integer, ClassTimings> test = new HashMap<Integer, ClassTimings>();
        test = (HashMap<Integer, ClassTimings>) oldintent.getSerializableExtra("all_timings");


        for(;i[0]<l/3-1;i[0] = i[0]+1){
            ClassTimings timings = test.get(i[0]);
            //  Toast.makeText(getBaseContext(),timings[i[0]][0], Toast.LENGTH_SHORT).show();
            //  Toast.makeText(getBaseContext(), timings[i[0]][0], Toast.LENGTH_SHORT).show();
            for(int m=0;m<7;m++) {
                if (timings.days[m].equals("-"))
                    continue;
                //  Toast.makeText(getBaseContext(), ""+m, Toast.LENGTH_SHORT).show();
                Calendar startTime = Calendar.getInstance();
                switch (m){
                    case 0: startTime.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        break;
                    case 1: startTime.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                        break;
                    case 2: startTime.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                        break;
                    case 3: startTime.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                        break;
                    case 4: startTime.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                        break;
                    case 5: startTime.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                        break;
                    case 6: startTime.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                        break;
                }

                String ltime = timings.days[m];

                startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ltime.substring(0,2)));
                startTime.set(Calendar.MINUTE, 0);
                //    startTime.set(Calendar.MONTH, newMonth - 1);
                startTime.set(Calendar.YEAR, newYear);
                Calendar endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR, 1);
                //  endTime.set(Calendar.MONTH, newMonth - 1);
                WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
                event.setColor(getResources().getColor(R.color.event_color_01));
                events.add(event);
            }
        }
        return events;
    }
}