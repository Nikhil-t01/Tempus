package com.teamenrgy.tempus;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Homepage extends AppCompatActivity {

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
        final String dept = intent.getStringExtra("dept");

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

        final String courses = intent.getStringExtra("courses");
//        Toast.makeText(getBaseContext(), courses, Toast.LENGTH_SHORT).show();
        final String ldap = intent.getStringExtra("ldap");
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
                Intent intent = new Intent(Homepage.this, TimeTableActivity.class);
                intent.putExtra("courses", courses);
                //Toast.makeText(getBaseContext(), courses, Toast.LENGTH_SHORT).show();
                Homepage.this.startActivity(intent);
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
    }
}