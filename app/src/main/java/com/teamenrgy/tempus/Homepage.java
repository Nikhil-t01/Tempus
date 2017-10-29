package com.teamenrgy.tempus;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Homepage extends AppCompatActivity {

    int l;
    String[] timings =  new String[7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  LoginActivity.transitionToast.cancel();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_homepage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_homepage);
        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String ldap = intent.getStringExtra("ldap");
        final String dept = intent.getStringExtra("dept");
        final String courses = intent.getStringExtra("courses");
        final String events = intent.getStringExtra("events");
        final String pending_events = intent.getStringExtra("pending_events");
        //Toast.makeText(getBaseContext(), "Events: "+events + " Pending Events:" + pending_events, Toast.LENGTH_LONG).show();
        final TextView dummy = (TextView) findViewById(R.id.dummydept);

        Response.Listener<String> deptListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    int depid = jsonResponse.getInt("dept_id");
                    dummy.setText(""+depid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        DeptIDRequest deptIDRequest = new DeptIDRequest(dept, deptListener);
        RequestQueue queue = Volley.newRequestQueue(Homepage.this);
        queue.add(deptIDRequest);


//        Toast.makeText(getBaseContext(), courses, Toast.LENGTH_SHORT).show();

        Typeface font_welcome = Typeface.createFromAsset(getAssets(), "fonts/IndieFlower.ttf");
        TextView welcomeText = (TextView) findViewById(R.id.welcome);
        welcomeText.setTypeface(font_welcome);
        welcomeText.setText("Welcome, " + name + "\n" + dept);

        TextView courses_tv = (TextView) findViewById(R.id.courses);
        courses_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.progess_bar);
                Intent intent = new Intent(Homepage.this, CoursesActivity.class);
                intent.putExtra("courses", courses);
                intent.putExtra("ldap", ldap);
                intent.putExtra("name", name);
                //Toast.makeText(getBaseContext(), courses, Toast.LENGTH_SHORT).show();
                Homepage.this.startActivity(intent);

            }
        });

        TextView timetable_tv = (TextView) findViewById(R.id.timetable);
        timetable_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.progess_bar);
                l = courses.length();
                final int[] i = {0};
                final HashMap all_timings = new HashMap<Integer, ClassTimings>();

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse = null;
                        try {
                            jsonResponse = new JSONObject(response);
                            Toast.makeText(getBaseContext(), response, Toast.LENGTH_SHORT).show();
                            all_timings.put(i[0],new ClassTimings(
                                    jsonResponse.getString("mon"),
                                    jsonResponse.getString("tue"),
                                    jsonResponse.getString("wed"),
                                    jsonResponse.getString("thu"),
                                    jsonResponse.getString("fri"),
                                    jsonResponse.getString("sat"),
                                    jsonResponse.getString("sun"),
                                    jsonResponse.getString("name"),
                                    jsonResponse.getString("venue")));
                            i[0] = i[0] +1;
                            if(i[0]==l/3-1){
                                //intent.putExtras(b);
                                Intent intent = new Intent(Homepage.this, TimeTableActivity.class);
                                intent.putExtra("all_timings", all_timings);
                                intent.putExtra("courses", courses);
                                Toast.makeText(getBaseContext(), "activity starting", Toast.LENGTH_SHORT).show();
                                Homepage.this.startActivity(intent);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                for(int idx = 6; idx<=l; idx=idx+3) {
                    String course = courses.substring(idx-3, idx);
                    CourseDetailRequest courseRequest = new CourseDetailRequest(course,  listener);
                    RequestQueue queue = Volley.newRequestQueue(Homepage.this);
                    queue.add(courseRequest);
                }
            }
        });

        TextView profile_tv = (TextView) findViewById(R.id.profile);
        profile_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.progess_bar);
                Intent intent = new Intent(Homepage.this, ProfileActivity.class);
                intent.putExtra("courses", courses);
                intent.putExtra("ldap",ldap);
                intent.putExtra("name", name);
                intent.putExtra("dept", dept);
                intent.putExtra("events", events);
                intent.putExtra("pending_events", pending_events);
                //Toast.makeText(getBaseContext(), courses, Toast.LENGTH_SHORT).show();
                Homepage.this.startActivity(intent);
            }
        });

        TextView dept_tv = (TextView) findViewById(R.id.deptforum);
        dept_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setContentView(R.layout.progess_bar);
                String dept_id = dummy.getText().toString();
                Intent intent = new Intent(Homepage.this, TopicsActivity.class);
                intent.putExtra("ldap", ldap);
                intent.putExtra("name", name);
                String dept_temp = ""+dept_id;
                if(dept_id.length() == 1) {
                    dept_temp = "0"+dept_temp;
                }
                //Toast.makeText(getBaseContext(), dept_temp, Toast.LENGTH_SHORT).show();
                intent.putExtra("cat_name", dept_temp);
                intent.putExtra("cat_title", "Department");
                Homepage.this.startActivity(intent);
            }
        });

        TextView events_tv = (TextView) findViewById(R.id.quizzes);
        events_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setContentView(R.layout.progess_bar);

                final HashMap event_details = new HashMap<Integer, Event>();

                Response.Listener<String> eventsDetailListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getBaseContext(), response, Toast.LENGTH_LONG).show();
                        Log.v("anc", response);
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int total = jsonResponse.getInt("total");
                            for(int i = 0; i < total; i++) {
                                event_details.put(jsonResponse.getInt("id"+i),
                                        new Event(jsonResponse.getInt("id"+i),
                                                jsonResponse.getString("desc"+i),
                                                jsonResponse.getString("start"+i),
                                                jsonResponse.getString("end"+i),
                                                jsonResponse.getString("user"+i),
                                                jsonResponse.getString("cat"+i)));
                            }
                            Intent intent = new Intent(Homepage.this, EventsActivity.class);
                            intent.putExtra("name", name);
                            intent.putExtra("ldap", ldap);
                            intent.putExtra("events", events);
                            intent.putExtra("pending_events", pending_events);
                            intent.putExtra("dept", dept);
                            intent.putExtra("courses", courses);
                            intent.putExtra("event_details", event_details);
                            Homepage.this.startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                GetEventsDetailRequest getEventsDetail =
                        new GetEventsDetailRequest(eventsDetailListener);
                RequestQueue queue = Volley.newRequestQueue(Homepage.this);
                queue.add(getEventsDetail);
            }
        });

        Button logout = findViewById(R.id.log_out);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }





    @Override
    public void onBackPressed() {
    //    Toast.makeText(getBaseContext(), "LogOut kro", Toast.LENGTH_SHORT).show();
        //super.onBackPressed();
    }
}