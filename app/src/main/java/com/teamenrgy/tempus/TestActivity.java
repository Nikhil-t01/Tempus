package com.teamenrgy.tempus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by nikhil-t on 28/10/17.
 */

public class TestActivity extends TableActivity {
    String courses;
    String[][] timings = new String[8][7];
    List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    int ny, nm ,l;
    Bundle b;
    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        setContentView(R.layout.activity_time_table);
        Intent oldintent = getIntent();
        courses = oldintent.getStringExtra("courses");
        b = oldintent.getExtras();
        String Days = "";
        l = courses.length();

        Calendar startTime, endTime;
        WeekViewEvent event;

        final int[] i = {1};

       for(;i[0]<=l/3;i[0] = i[0]+1){
            timings[i[0]] = b.getStringArray("timings"+i[0]);
           Toast.makeText(getBaseContext(),""+i[0], Toast.LENGTH_SHORT).show();
         //  Toast.makeText(getBaseContext(), timings[i[0]][0], Toast.LENGTH_SHORT).show();
            for(int m=0;m<7;m++) {
                if (timings[i[0]][m].equals("-"))
                    continue;
                //  Toast.makeText(getBaseContext(), ""+m, Toast.LENGTH_SHORT).show();
                startTime = Calendar.getInstance();
                endTime = (Calendar) startTime.clone();
                startTime.set(Calendar.DAY_OF_WEEK, m+1);
                startTime.set(Calendar.HOUR_OF_DAY, 3);
                startTime.set(Calendar.MINUTE, 0);
                startTime.set(Calendar.MONTH, newMonth - 1);
                startTime.set(Calendar.YEAR, newYear);
                event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
                endTime.add(Calendar.HOUR, 1);
                endTime.set(Calendar.MONTH, nm - 1);
                event.setColor(getResources().getColor(R.color.event_color_01));
                events.add(event);
            }
        }

        return events;
    }


}
