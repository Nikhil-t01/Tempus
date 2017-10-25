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

import java.util.Calendar;

public class AddEventFragment extends Fragment {

    Button btnDatePicker, btnStartTimePicker, btnEndTimePicker;
    EditText txtDate, txtStartTime, txtEndTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

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
        txtDate = (EditText) v.findViewById(R.id.in_date);
        txtStartTime = (EditText) v.findViewById(R.id.in_stime);
        txtEndTime = (EditText) v.findViewById(R.id.in_etime);
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

                                txtDate.setText(year + "-" + mm + "-" + dd);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnStartTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Time
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
                // Get Current Time
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