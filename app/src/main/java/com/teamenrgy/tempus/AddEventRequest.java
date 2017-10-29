package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to send request to database for updating events
 */
public class AddEventRequest extends StringRequest {
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/AddEvent.php"; //URL for php file which adds event in database
    private Map<String, String> params;

    /**
     * Constructor for this class
     * @param category Category under which event is to be added
     * @param name Name of user adding the event
     * @param stime Start time of event
     * @param etime End time of event
     * @param listener Response listener for the request
     */

    public AddEventRequest(String category, String description, String name, String stime, String etime ,Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("category", category);
        params.put("description", description);
        params.put("name", name);
        params.put("stime", stime);
        params.put("etime", etime);
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

