package com.teamenrgy.tempus;

import android.content.Intent;

import com.alamkanak.weekview.WeekViewEvent;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimeTableActivity extends TableActivity {
    String courses;
    String[][] timings;
    List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    int ny, nm ,l;
    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        setContentView(R.layout.activity_time_table);
        Intent oldintent = getIntent();
        courses = oldintent.getStringExtra("courses");
        // final ArrayList<String> Days = new ArrayList<>();
        String Days = "";
        l = courses.length();
        final int[] i = {1};

        ny = newYear;
        nm = newMonth;

        Response.Listener<String> Listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    timings = new String[8][7];
                    timings[i[0]][0] = jsonResponse.getString("mon");
                    timings[i[0]][1] = jsonResponse.getString("tue");
                    timings[i[0]][2] = jsonResponse.getString("wed");
                    timings[i[0]][3] = jsonResponse.getString("thu");
                    timings[i[0]][4] = jsonResponse.getString("fri");
                    timings[i[0]][5] = jsonResponse.getString("sat");
                    timings[i[0]][6] = jsonResponse.getString("sun");
                    String names = jsonResponse.getString("name");
                    String venues = jsonResponse.getString("venue");

                    /*DayAdapter dayAdapter = new DayAdapter(getParent(), Days);
                    ListView days = (ListView) findViewById(R.id.days);
                    days.setAdapter(dayAdapter);*/

                   // Toast.makeText(getBaseContext(), ""+id, Toast.LENGTH_SHORT).show();
                        for(int m=0;m<7;m++) {
                            if (timings[i[0]][m].equals("-"))
                                continue;
                          //  Toast.makeText(getBaseContext(), ""+m, Toast.LENGTH_SHORT).show();
                            Calendar startTime = Calendar.getInstance();
                            Calendar endTime = (Calendar) startTime.clone();
                            startTime.set(Calendar.DAY_OF_WEEK, m+1);
                            startTime.set(Calendar.HOUR_OF_DAY, 3);
                            startTime.set(Calendar.MINUTE, 0);
                            startTime.set(Calendar.MONTH, nm - 1);
                            startTime.set(Calendar.YEAR, ny);
                            WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
                            endTime.add(Calendar.HOUR, 1);
                            endTime.set(Calendar.MONTH, nm - 1);
                            event.setColor(getResources().getColor(R.color.event_color_01));
                            events.add(event);
                        }
                    i[0] = i[0] +1;
                    if(i[0]==l/3-1)return;

                    // Toast.makeText(getBaseContext(), "day", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        for(int idx = 6; idx<=l; idx=idx+3) {
            String course = courses.substring(idx-3, idx);

            CourseDetailRequest courseRequest = new CourseDetailRequest(course,  Listener);
            RequestQueue queue = Volley.newRequestQueue(TimeTableActivity.this);
            queue.add(courseRequest);
        }
    return events;
    }


}