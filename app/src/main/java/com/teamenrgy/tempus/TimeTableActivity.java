package com.teamenrgy.tempus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class TimeTableActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_time_table);

        Intent oldintent = getIntent();
        String courses = oldintent.getStringExtra("courses");
        // final ArrayList<String> Days = new ArrayList<>();
        String Days = "";
        int l = courses.length();

        Response.Listener<String> Listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    String mons = jsonResponse.getString("mon");
                    String tues = jsonResponse.getString("tue");
                    String weds = jsonResponse.getString("wed");
                    String thus = jsonResponse.getString("thu");
                    String fris = jsonResponse.getString("fri");
                    String sats = jsonResponse.getString("sat");
                    String suns = jsonResponse.getString("sun");
                    String names = jsonResponse.getString("name");
                    String venues = jsonResponse.getString("venue");
                    TextView mon = (TextView) findViewById(R.id.mon);
                    TextView tue = (TextView) findViewById(R.id.tue);
                    TextView wed = (TextView) findViewById(R.id.wed);
                    TextView thu = (TextView) findViewById(R.id.thu);
                    TextView fri = (TextView) findViewById(R.id.fri);
                    TextView sat = (TextView) findViewById(R.id.sat);
                    TextView sun = (TextView) findViewById(R.id.sun);

                    mon.setText(mon.getText().toString() + names + " " + mons + " " + venues + "\n");
                    tue.setText(tue.getText().toString() + names + " " + tues + " " + venues + "\n");
                    wed.setText(wed.getText().toString() + names + " " + weds + " " + venues + "\n");
                    thu.setText(thu.getText().toString() + names + " " + thus + " " + venues + "\n");
                    fri.setText(fri.getText().toString() + names + " " + fris + " " + venues + "\n");
                    sat.setText(sat.getText().toString() + names + " " + sats + " " + venues + "\n");
                    sun.setText(sun.getText().toString() + names + " " + suns + " " + venues + "\n");




                    /*DayAdapter dayAdapter = new DayAdapter(getParent(), Days);
                    ListView days = (ListView) findViewById(R.id.days);
                    days.setAdapter(dayAdapter);*/

                   // Toast.makeText(getBaseContext(), "day", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        for(int i = 6; i<=l; i=i+3) {
            String course = courses.substring(i-3, i);
            CourseDetailRequest courseRequest = new CourseDetailRequest(course,  Listener);
            RequestQueue queue = Volley.newRequestQueue(TimeTableActivity.this);
            queue.add(courseRequest);
        }

        // Toast.makeText(getBaseContext(), "adapt", Toast.LENGTH_LONG).show();
    }
}