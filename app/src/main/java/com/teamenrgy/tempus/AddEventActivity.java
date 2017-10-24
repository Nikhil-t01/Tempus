package com.teamenrgy.tempus;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Rajat Rathi on 20-10-2017.
 */

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnDatePicker, btnStartTimePicker, btnEndTimePicker;
    EditText txtDate, txtStartTime, txtEndTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toast.makeText(getBaseContext(), "Add Event!", Toast.LENGTH_SHORT).show();
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnStartTimePicker = (Button) findViewById(R.id.btn_stime);
        btnEndTimePicker = (Button) findViewById(R.id.btn_etime);
        txtDate = (EditText)findViewById(R.id.in_date);
        txtStartTime = (EditText) findViewById(R.id.in_stime);
        txtEndTime = (EditText) findViewById(R.id.in_etime);
        btnDatePicker.setOnClickListener(this);
        btnStartTimePicker.setOnClickListener(this);
        btnEndTimePicker.setOnClickListener(this);
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

                            txtDate.setText(year + "-" + mm + "-" + dd);
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
    }
}

