package com.teamenrgy.tempus;



import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to send request to database for updating posts in a topic of discussion forum
 */
public class ForumRequest extends StringRequest {
    private static final String FORUM_REQUEST_URL = "https://flyingtempus.000webhostapp.com/ForumRequest.php";
    private Map<String, String> params;

    /**
     * Constructor for this class
     * @param topicid ID of the topic of post
     * @param ldap LDAP ID of the user who posted it
     * @param message Description of  post
     * @param listener Response listener for the request
     */
    public ForumRequest(String topicid, String ldap, String message, Response.Listener<String> listener) {
        super(Method.POST, FORUM_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("topic_id", topicid);
        params.put("ldap",ldap);
        params.put("post_content",message);
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