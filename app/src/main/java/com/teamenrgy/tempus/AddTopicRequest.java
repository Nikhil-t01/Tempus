package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Class used to send request to database for updating topics in the discussion forums
 */
public class AddTopicRequest extends StringRequest {
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/AddTopic.php"; //URL for php file which adds topic in database
    private Map<String, String> params;

    /**
     * Constructor for this class
     * @param subject Subject of the topic to be added
     * @param cat Category under which the topic is added (Course/Department name)
     * @param name Name of user creating the topic
     * @param listener Response listener  for the request
     */
    public AddTopicRequest(String subject, String cat, String name, Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("subject" , subject);
        params.put("cat", cat);
        params.put("name", name);
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