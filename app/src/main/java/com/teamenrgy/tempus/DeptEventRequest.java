package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Class used to get events of a particular department
 */
public class DeptEventRequest extends StringRequest {
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/DeptEvent.php";//URL of php file which gets events of a department
    private Map<String, String> params;
    /**
     * Constructor for this class
     * @param category category, i.e., to which course or department that event belongs to
     * @param event_id ID of the event
     * @param listener Response listener for the request
     */
    public DeptEventRequest(String category, String event_id ,Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("category", category);
        params.put("event_id", event_id);
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