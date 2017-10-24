package com.teamenrgy.tempus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_profile);

        Intent oldintent = getIntent();
        final String ldap = oldintent.getStringExtra("ldap");
        final String initcourses = oldintent.getStringExtra("courses");
        final String name = oldintent.getStringExtra("name");
        final String curr_dept = oldintent.getStringExtra("dept");
        final String events = oldintent.getStringExtra("events");
        final String pending_events = oldintent.getStringExtra("pending_events");
        final TextView spinner_dept_dummy = findViewById(R.id.dummy_dept2);
        Spinner Departments = findViewById(R.id.Depts);
        final String[] Depts = { "Select Department", "Computer Science and Engineering",
                "Electrical Engineering",
                "Mechanical Engineering",
                "Chemical Engineering",
                "Aerospace Engineering",
                "Engineering Physics",
                "Civil Engineering",
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Depts);
        Departments.setAdapter(adapter);

        Departments.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0) {
                    spinner_dept_dummy.setText(curr_dept);
                }
                else spinner_dept_dummy.setText(Depts[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinner_dept_dummy.setText(curr_dept);
            }
        });

        final Intent intent = new Intent(ProfileActivity.this, Homepage.class);
        final TextView dummy = (TextView) findViewById(R.id.dummy);
        final TextView dummy_pEvent = (TextView) findViewById(R.id.dummy_pEvent);
        final TextView dummy_event = (TextView) findViewById(R.id.dummy_event);
        spinner_dept_dummy.setText(getIntent().getStringExtra("dept"));
        //final EditText dept = (EditText) findViewById(R.id.branch);
        //dept.setText(getIntent().getStringExtra("dept"));
        intent.putExtra("ldap", ldap);
        intent.putExtra("name", name);
        intent.putExtra("courses", initcourses);
        dummy.setText(initcourses);
        dummy_pEvent.setText(pending_events);
        dummy_event.setText(events);

        Button Done = (Button) findViewById(R.id.done);

        final Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // Toast.makeText(getBaseContext(), "Course Added", Toast.LENGTH_SHORT).show();
            }
        };

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.progess_bar);
                String current = dummy.getText().toString();
                String events = dummy_event.getText().toString();
                String pending_events = dummy_pEvent.getText().toString();
                intent.putExtra("courses", current);
                intent.putExtra("dept", spinner_dept_dummy.getText().toString());
                intent.putExtra("events", events);
                intent.putExtra("pending_events", pending_events);
                AddcourseRequest courseRequest = new AddcourseRequest(current, ldap,events, pending_events,  responseListener);
                RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);
                queue.add(courseRequest);
                ProfileActivity.this.startActivity(intent);
            }
        });

        Button Add = (Button) findViewById(R.id.add_button);
        Button Del = (Button) findViewById(R.id.del_button);
        final EditText AddC = (EditText) findViewById(R.id.etAdd);
        final EditText DelC = (EditText) findViewById(R.id.etDel);


        final Response.Listener<String> Listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    int courseid = jsonResponse.getInt("courseid");
                    String current = dummy.getText().toString();
                    if(courseid!=0) {
                        String cid = "";
                        if (1 <= courseid && courseid < 10) {
                            cid = "00" + courseid;
                        } else if (9 < courseid && courseid < 100) {
                            cid = "0" + courseid;
                        } else {
                            cid = "" + courseid;
                        }

                        for(int i= 3; i<=current.length();i+=3){
                            String c = current.substring(i-3,i);
                            if(c.equals(cid)) {
                                Toast.makeText(getBaseContext(), "You're already registered for this course", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        String course_event = jsonResponse.getString("pEvents");
                        String finalcourse = current + cid;
                        dummy.setText(finalcourse);
                        dummy_pEvent.setText(dummy_pEvent.getText().toString() + course_event.substring(4));
                        Toast.makeText(getBaseContext(), "Course Added", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getBaseContext(), "Pending Events: "+dummy_pEvent.getText().toString(), Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(getBaseContext(), "This course doesn't exist", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        final Response.Listener<String> dListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    int courseid = jsonResponse.getInt("courseid");
                    String current = dummy.getText().toString();
                    if(courseid!=0) {
                        String cid = "";
                        if (1 <= courseid && courseid < 10) {
                            cid = "00" + courseid;
                        } else if (9 < courseid && courseid < 100) {
                            cid = "0" + courseid;
                        } else {
                            cid = "" + courseid;
                        }

                        String updated = "";
                        boolean removed = false;
                        for(int i= 3; i<=current.length();i+=3){
                            String c = current.substring(i-3,i);
                            if(!c.equals(cid)) {
                                updated = updated + c;
                                removed = true;
                            }
                        }

                        if(removed) {
                            Toast.makeText(getBaseContext(), "Course Deleted", Toast.LENGTH_SHORT).show();
                            String course_event = jsonResponse.getString("pEvents");
                            String pending = dummy_pEvent.getText().toString();
                            String cur = dummy_event.getText().toString();
                            String final_cur = "";
                            String final_pending = "";
                            for(int j=4;j<=pending.length();j+=4){
                                String pEvent_id = pending.substring(j-4,j);
                                boolean b = true;
                                for(int i=8;i<=course_event.length();i+=4){
                                    String c = course_event.substring(i-4,i);
                                    if((c.equals(pEvent_id))) {
                                        b=false;
                                        break;
                                    }
                                }
                                if(b)
                                    final_pending += pEvent_id;
                            }

                            for(int j=4;j<=cur.length();j+=4){
                                String event_id = cur.substring(j-4,j);
                                boolean b = true;
                                for(int i=8;i<=course_event.length();i+=4){
                                    String c = course_event.substring(i-4,i);
                                    if((c.equals(event_id))) {
                                        b=false;
                                        break;
                                    }
                                }
                                if(b)
                                    final_cur += event_id;
                            }

                            Toast.makeText(getBaseContext(),final_cur,Toast.LENGTH_SHORT).show();
                            Toast.makeText(getBaseContext(),final_pending,Toast.LENGTH_SHORT).show();
                            dummy_event.setText(final_cur);
                            dummy_pEvent.setText(final_pending);
                        }
                        else {
                            Toast.makeText(getBaseContext(), "Are you kidding me?", Toast.LENGTH_SHORT).show();
                        }
                        dummy.setText(updated);}
                    else {
                        Toast.makeText(getBaseContext(), "You're not registered for this course", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };



        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String course = AddC.getText().toString();
                AddC.setText("");
                CourseRequest courseRequest = new CourseRequest(course,  Listener);
                RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);
                queue.add(courseRequest);
            }
        });

        Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String course = DelC.getText().toString();
                DelC.setText("");

                CourseRequest courseRequest = new CourseRequest(course,  dListener);
                RequestQueue queue = Volley.newRequestQueue(ProfileActivity.this);
                queue.add(courseRequest);

            }
        });

    }
}