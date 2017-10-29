package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to get all topics from a particular course or department
 */
public class GetTopicRequest extends StringRequest {
    //URL for php file which gets all topics from a particular course or department from database
    private static final String GETMESSAGE_REQUEST_URL = "https://flyingtempus.000webhostapp.com/GetTopics.php";
    private Map<String, String> params;

    /**
     * Constructor of this class
     * @param cat Category to which this topic belongs to, i.e., particular course or department
     * @param listener Response listener for the request
     */
    public GetTopicRequest( String cat, Response.Listener<String> listener) {
        super(Method.POST, GETMESSAGE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("cat",cat);

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
