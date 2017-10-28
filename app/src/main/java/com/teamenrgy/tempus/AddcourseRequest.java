package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Team eNRGy
 * Class used to send request to database for updating courses of user
 */
public class AddcourseRequest extends StringRequest {
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/ACourse.php"; //URL for php file which updates courses
    private Map<String, String> params;

    /**
     * Constructor for this class
     * @param courseid Courseid of user
     * @param ldap LDAP ID of user
     * @param events Events accepted by the user
     * @param pending_events Pending events for the user
     * @param listener Response listener
     */
    public AddcourseRequest(String courseid, String ldap, String events, String pending_events,  Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("courses", courseid);
        params.put("ldap",ldap);
        params.put("events", events);
        params.put("pending_events", pending_events);

    }

    /**
     * Function to get the parameters to be posted to php
     * @return parameters to be posted to php
     */
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}