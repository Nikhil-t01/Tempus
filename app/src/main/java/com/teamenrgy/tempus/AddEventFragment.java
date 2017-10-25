package com.teamenrgy.tempus;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class AddEventFragment extends Fragment {

    Button btnDatePicker, btnStartTimePicker, btnEndTimePicker, btnAddEvent;
    EditText txtDate, txtStartTime, txtEndTime, Category, Description;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String name, events, pending_events, event_id;

    LayoutInflater inflater;
    ViewGroup container;
    Bundle savedInstanceState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;

        View v = inflater.inflate(R.layout.activity_add_event, container, false);

        btnDatePicker = (Button) v.findViewById(R.id.btn_date);
        btnStartTimePicker = (Button) v.findViewById(R.id.btn_stime);
        btnEndTimePicker = (Button) v.findViewById(R.id.btn_etime);
        btnAddEvent = (Button) v.findViewById(R.id.btnAddEvent);
        txtDate = (EditText) v.findViewById(R.id.in_date);
        txtStartTime = (EditText) v.findViewById(R.id.in_stime);
        txtEndTime = (EditText) v.findViewById(R.id.in_etime);
        Category = (EditText) v.findViewById(R.id.category);
        Description = (EditText) v.findViewById(R.id.description);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
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
                                Toast.makeText(getContext(),year + "-" + mm + "-" + dd + " ", Toast.LENGTH_SHORT).show();
                                txtDate.setText(year + "-" + mm + "-" + dd + " ");
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnStartTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
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
        });
        btnEndTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
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
        });

        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String cat_name = Category.getText().toString();
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
                            event_id = id;
                            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                            if(cat_name.length() == 5){
                                Toast.makeText(getContext(), "Course: "+cat_name, Toast.LENGTH_SHORT).show();
                                Response.Listener<String> courseEventListener = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonResponse = new JSONObject(response);
                                            Toast.makeText(getContext(), event_id, Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getContext(), jsonResponse.getString("cid"), Toast.LENGTH_SHORT).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };

                                CourseEventRequest courseEventRequest = new CourseEventRequest(cat_name, event_id, courseEventListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(courseEventRequest);
                            }
                            else{
                                Toast.makeText(getContext(), "Dept: "+cat_name, Toast.LENGTH_SHORT).show();
                                Response.Listener<String> deptEventListener = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                    }
                                };

                                DeptEventRequest deptEventRequest = new DeptEventRequest(cat_name, event_id, deptEventListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(deptEventRequest);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                };

                AddEventRequest addEventRequest = new AddEventRequest(
                        cat_name,
                        Description.getText().toString(),
                        name,
                        txtDate.getText().toString() + txtStartTime.getText().toString(),
                        txtDate.getText().toString() + txtEndTime.getText().toString(),
                        addEventListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(addEventRequest);


            }
        });

        return v;
    }

    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible && isResumed())
        {
            onCreateView(inflater, container, savedInstanceState);
        }
    }

    public static Fragment newInstance() {

        AddEventFragment f = new AddEventFragment();
        return f;
    }
}