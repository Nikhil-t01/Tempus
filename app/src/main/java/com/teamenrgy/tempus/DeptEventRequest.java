package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Class used to get events of a particular department
 * 
 */
public class DeptEventRequest extends StringRequest {
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/DeptEvent.php";
    private Map<String, String> params;


    /**
     * Constructor for this class
     * @param dept Department of the user and the one associated with the event
     * @param event_id ID of the event
     * @param listener Response listener for the request
     */
    public DeptEventRequest(String dept, String event_id ,Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("dept", dept);
        params.put("event_id", event_id);
    }


    /**
     * Function to get the parameters to be posted to php
     * @return parameters to be posted to php
     */
    @Override
    public Map<String, String> getParams() {
        //Log.v("RegisterRequest", "getParams() called");
        return params;
    }
}