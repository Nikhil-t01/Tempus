package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to get details of events of a user from database
 */
public class GetEventsDetailRequest extends StringRequest{
    //URL for php file which is used to get details of event from database
    private static final String EVENTS_DETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/GetEventsDetails.php";
    private Map<String, String> params;

    /**
     * Constructor for this class
     * @param listener Response listener for the request
     */
    public GetEventsDetailRequest(Response.Listener<String> listener) {
        super(Method.POST, EVENTS_DETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
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