package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 *Class used to update events associated with all corresponding users, when a user adds event in a course
 */
public class CourseEventRequest extends StringRequest {
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/CourseEvent.php";//URL for php file which updates events of all corresponding users
    private Map<String, String> params;

    /**
     * Constructor for this class
     * @param category Courseid or department id of the event
     * @param event_id Eventid of the event
     * @param listener Response listener for the request
     */
    public CourseEventRequest(String category, String event_id ,Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("category", category);
        params.put("event_id", event_id);
    }

    /**
     Function to get the parameters to be posted to php
     * @return parameters to be posted to php
     */
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

