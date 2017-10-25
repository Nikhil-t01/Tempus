package com.teamenrgy.tempus;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.teamenrgy.tempus.CourseRequest;
import com.teamenrgy.tempus.DeptIDRequest;
import com.teamenrgy.tempus.Homepage;
import com.teamenrgy.tempus.LoginActivity;
import com.teamenrgy.tempus.R;
import com.teamenrgy.tempus.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final TextView spinner_dept_dummy = (TextView) findViewById(R.id.dummydept2);
        Spinner Departments = (Spinner) findViewById(R.id.Depts);
        final String[] Depts = { "Change Department", "Computer Science and Engineering",
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
                spinner_dept_dummy.setText(Depts[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinner_dept_dummy.setText("");
            }
        });

        Intent intent = getIntent();
        final String ldap = intent.getStringExtra("ldap");
        final String Password = intent.getStringExtra("password");
        final EditText etCourses = (EditText) findViewById(R.id.etCourses);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etPasswordC = (EditText) findViewById(R.id.etPasswordC);
        final TextView final_courses = (TextView) findViewById(R.id.final_courses);
        final TextView pEvents = (TextView) findViewById(R.id.pEvents);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final Button bCourse = (Button) findViewById(R.id.course_button);

        bCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String temp_c = etCourses.getText().toString();
                final String temp_1 = final_courses.getText().toString();
                //        Toast.makeText(getBaseContext(), temp_c, Toast.LENGTH_SHORT).show();
                etCourses.setText("");

                Response.Listener<String> Listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int courseid = Integer.parseInt(jsonResponse.getString("courseid"));
                            //                  Toast.makeText(getBaseContext(), courseid+"", Toast.LENGTH_SHORT).show();
                            if(courseid!=0) {
                                //                    Toast.makeText(getBaseContext(), "In", Toast.LENGTH_SHORT).show();
                                String cid = "";
                                if (1 <= courseid && courseid < 10) {
                                    cid = "00" + courseid;
                                } else if (9 < courseid && courseid < 100) {
                                    cid = "0" + courseid;
                                } else {
                                    cid = "" + courseid;
                                }

                                for(int i=3;i<=temp_1.length();i+=3){
                                    String c = temp_1.substring(i-3,i);
                                    if(c.equals(cid)){
                                        Toast.makeText(getBaseContext(), "You're already registered for this course", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }

                                //                  Toast.makeText(getBaseContext(), "Out", Toast.LENGTH_SHORT).show();
                                final_courses.setText(temp_1+cid);
                                String course_events = jsonResponse.getString("pEvents");
                                pEvents.setText(pEvents.getText().toString() + course_events.substring(4));
                                Toast.makeText(getBaseContext(), "Course Added", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getBaseContext(), "Course doesn't exist in the database", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                CourseRequest courseRequest = new CourseRequest(temp_c,  Listener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(courseRequest);
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String name = etName.getText().toString();
                final String passwordC = etPasswordC.getText().toString();
                //final String dept = etDept.getText().toString();
                final String dept = spinner_dept_dummy.getText().toString();
                final String courses = final_courses.getText().toString();
                final TextView dummy = (TextView) findViewById(R.id.dummydept);

                Response.Listener<String> deptListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String dept_id = jsonResponse.getString("dept_id");
                            String dept_events = jsonResponse.getString("dept_events");
                            dummy.setText(dept_id);
                            pEvents.setText(pEvents.getText().toString() + dept_events.substring(4));

                            Response.Listener<String> responseListener = new Response.Listener<String>() {

                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);
                                        boolean success = jsonResponse.getBoolean("success");

                                        if(success && passwordC.equals(Password)) {
                                            int dept_id = Integer.parseInt(dummy.getText().toString());
                                            Intent intent2 = new Intent(RegisterActivity.this, Homepage.class);
                                            intent2.putExtra("name", name);
                                            intent2.putExtra("ldap", ldap);
                                            intent2.putExtra("courses", courses);
                                            intent2.putExtra("dept_id", dept_id);
                                            intent2.putExtra("dept", dept);
                                            intent2.putExtra("events", "0000");
                                            intent2.putExtra("pending_events", pEvents.getText().toString());
                                            RegisterActivity.this.startActivity(intent2);
                                        }
                                        else {
                                            final Intent intent3 = new Intent(RegisterActivity.this, LoginActivity.class);
                                            DialogInterface.OnClickListener temp = new DialogInterface.OnClickListener(){
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    RegisterActivity.this.startActivity(intent3);
                                                }
                                            };
                                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                            builder.setMessage("Registration Failed")
                                                    .setNegativeButton("Retry", temp)
                                                    .create()
                                                    .show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };

                            RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                            RegisterRequest registerRequest = new RegisterRequest(name, ldap, courses, Password, passwordC, dept, pEvents.getText().toString(), responseListener);
                            queue.add(registerRequest);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                DeptIDRequest deptIDRequest = new DeptIDRequest(dept, deptListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(deptIDRequest);
            }
        });
    }
}
