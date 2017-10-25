package com.teamenrgy.tempus;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import static com.teamenrgy.tempus.R.id.btnAddEvent;
import static com.teamenrgy.tempus.R.id.topic_name;
import static com.teamenrgy.tempus.R.id.topics_list;

/**
 * Created by Rajat Rathi on 20-10-2017.
 */

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnDatePicker, btnStartTimePicker, btnEndTimePicker, btnAddEvent;
    EditText txtDate, txtStartTime, txtEndTime, Category, Description;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String name, events, pending_events;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toast.makeText(getBaseContext(), "Add Event!", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        events = intent.getStringExtra("events");
        pending_events = intent.getStringExtra("pending_events");
        name = intent.getStringExtra("name");
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnStartTimePicker = (Button) findViewById(R.id.btn_stime);
        btnEndTimePicker = (Button) findViewById(R.id.btn_etime);
        btnAddEvent = (Button) findViewById(R.id.btnAddEvent);
        txtDate = (EditText)findViewById(R.id.in_date);
        txtStartTime = (EditText) findViewById(R.id.in_stime);
        txtEndTime = (EditText) findViewById(R.id.in_etime);
        Category = (EditText) findViewById(R.id.category);
        Description = (EditText) findViewById(R.id.description);
        btnDatePicker.setOnClickListener(this);
        btnStartTimePicker.setOnClickListener(this);
        btnEndTimePicker.setOnClickListener(this);
        btnAddEvent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            String mm = ""+(monthOfYear+1);
                            String dd = ""+dayOfMonth;
                            if(dayOfMonth<10)
                                dd = "0" + dd;
                            if(monthOfYear<9)
                                mm = "0" + mm;

                            txtDate.setText(year + "-" + mm + "-" + dd + " ");
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnStartTimePicker) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String mm = ""+minute, hh = ""+hourOfDay;
                            if(minute<10)
                                mm = "0"+mm;
                            if(hourOfDay<10)
                                hh = "0"+hh;
                            txtStartTime.setText(hh + ":" + mm + ":00");
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == btnEndTimePicker) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String mm = ""+minute, hh = ""+hourOfDay;
                            if(minute<10)
                                mm = "0"+mm;
                            if(hourOfDay<10)
                                hh = "0"+hh;
                            txtEndTime.setText(hh + ":" + mm + ":00");
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == btnAddEvent) {
            Response.Listener<String> addEventListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        String id = jsonResponse.getString("id");
                        int len = 4-id.length();
                        for(int i=0;i<len;i++)
                            id = "0"+id;
                        events += id;
                        Intent intent = new Intent(AddEventActivity.this, EventsActivity.class);
                        intent.putExtra("events", events);
                        intent.putExtra("pending_events", pending_events);
                        AddEventActivity.this.startActivity(intent);
                        Toast.makeText(getBaseContext(), "Success", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        Toast.makeText(getBaseContext(), "Fail", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            };

            AddEventRequest addEventRequest = new AddEventRequest(
                    Category.getText().toString(),
                    Description.getText().toString(),
                    name,
                    txtDate.getText().toString() + txtStartTime.getText().toString(),
                    txtDate.getText().toString() + txtEndTime.getText().toString(),
                    addEventListener);
            RequestQueue queue = Volley.newRequestQueue(AddEventActivity.this);
            queue.add(addEventRequest);
        }
    }
}

