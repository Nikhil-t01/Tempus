package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Class used to send request to database for updating the accepted events and pending events of the user
 */
public class EventsRequest extends StringRequest {
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/UpdateEvents.php";
    private Map<String, String> params;

    /**
     * Constructor for this class
     * @param ldap LDAP ID of the user
     * @param events Encoded string of event id's of accepted events of the user
     * @param pending_events Encoded string of event id's of pending events of the user
     * @param listener Response listener
     */
    public EventsRequest(String ldap, String events, String pending_events, Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("events", events);
        params.put("pending_events", pending_events);
        params.put("ldap", ldap);
    }

    /**
     * Function to get parameters to be posted to php
     * @return parameters to be posted to php
     */
    @Override
    public Map<String, String> getParams() {
        //Log.v("RegisterRequest", "getParams() called");
        return params;
    }
}
