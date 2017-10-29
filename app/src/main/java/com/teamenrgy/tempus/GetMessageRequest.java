package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to get all posts from a particular topic from database
 */
public class GetMessageRequest extends StringRequest{
    //URL for php file which gets all posts from a particular topic from database
    private static final String GETMESSAGE_REQUEST_URL = "https://flyingtempus.000webhostapp.com/GetMessages.php";
    private Map<String, String> params;

    /**
     * Constructor for this class
     * @param topicid ID of the topic for which we are getting posts
     * @param listener Response listener for the request
     */
    public GetMessageRequest( String topicid, Response.Listener<String> listener) {
        super(Method.POST, GETMESSAGE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("topic_id",topicid);
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