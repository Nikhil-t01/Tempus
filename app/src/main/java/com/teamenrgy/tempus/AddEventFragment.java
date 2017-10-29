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
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 *This is the third fragment, which contains things to add events
 * This uses values from the parent, i.e., EventsActivity.java to update
 */
public class AddEventFragment extends Fragment {

    Button btnDatePicker, btnStartTimePicker, btnEndTimePicker, btnAddEvent;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String name, events, pending_events, event_id;

    EventsActivity eventsActivity = (EventsActivity) getActivity();

    LayoutInflater inflater;
    ViewGroup container;
    Bundle savedInstanceState;


    /**
     * This method is called when the this Fragment gets created.
     * All things in it are updated by getting values from the parent
     * @param inflater to fill the fragment with activity_add_event.xml
     * @param container to get the view
     * @param savedInstanceState Saved state of the instance
     * @return updated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;

        View v = inflater.inflate(R.layout.activity_add_event, container, false);

        events = ((TextView) getActivity().findViewById(R.id.pending_events)).getText().toString();
        name = ((TextView) getActivity().findViewById(R.id.name)).getText().toString();

        btnDatePicker = (Button) v.findViewById(R.id.btn_date);
        btnStartTimePicker = (Button) v.findViewById(R.id.btn_stime);
        btnEndTimePicker = (Button) v.findViewById(R.id.btn_etime);
        btnAddEvent = (Button) v.findViewById(R.id.btnAddEvent);
        final EditText txtDate = (EditText) v.findViewById(R.id.in_date);
        final EditText txtStartTime = (EditText) v.findViewById(R.id.in_stime);
        final EditText txtEndTime = (EditText) v.findViewById(R.id.in_etime);
        final EditText Category = (EditText) v.findViewById(R.id.category);
        final EditText Description = (EditText) v.findViewById(R.id.description);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            /**
             * Date Picker using calendar like thing
             * @param view view to be clicked for date
             */

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
                                txtDate.setText(year + "-" + mm + "-" + dd + " ");
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnStartTimePicker.setOnClickListener(new View.OnClickListener() {
            /**
             * Time Picker for start time using clock like thing
             * @param view view to be clicked for  start time
             */
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            /**
                             *  Over riding time
                             * @param view view from which getting
                             * @param hourOfDay hour
                             * @param minute minute
                             */
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
            /**
             * Time Picker for end time using clock like thing
             * @param view view to be clicked for  end time
             */
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            /**
                             *  Over riding time
                             * @param view view from which getting
                             * @param hourOfDay hour
                             * @param minute minute
                             */
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
                    /**
                     * Add event click button
                     * @param view view
                     */
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String id = jsonResponse.getString("id");
                            int len = 4-id.length();
                            for(int i=0;i<len;i++)
                                id = "0"+id;
                            events += id;
                            ((TextView) getActivity().findViewById(R.id.pending_events)).setText(events);
                            event_id = id;
                            if(cat_name.length() == 5){
                                Response.Listener<String> courseEventListener = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonResponse = new JSONObject(response);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };

                                CourseEventRequest courseEventRequest = new CourseEventRequest(cat_name, event_id, courseEventListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                                queue.add(courseEventRequest);
                            }
                            else{
                                //Toast.makeText(getContext(), "Dept: "+cat_name, Toast.LENGTH_SHORT).show();
                                Response.Listener<String> deptEventListener = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                    }
                                };

                                EventsActivity eventsActivity = (EventsActivity) getActivity();
                                DeptEventRequest deptEventRequest = new DeptEventRequest(eventsActivity.dept, event_id, deptEventListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                                queue.add(deptEventRequest);
                            }
                        } catch (JSONException e) {
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
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                queue.add(addEventRequest);
            }
        });

        return v;
    }

    /**
     * Setting fragment to visible or invisible
     * @param visible (boolean)
     */
    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible && isResumed())
        {
            onCreateView(inflater, container, savedInstanceState);
        }
    }

    /**
     * Returninig updated instance of a fragment
     * @return instance of a fragment
     */
    public static Fragment newInstance() {

        AddEventFragment f = new AddEventFragment();
        return f;
    }
}