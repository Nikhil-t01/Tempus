package com.teamenrgy.tempus;

/**
 * Created by nikhil-t on 12/10/17.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class ForumRequest extends StringRequest {
    private static final String FORUM_REQUEST_URL = "https://flyingtempus.000webhostapp.com/ForumRequest.php";
    private Map<String, String> params;

    public ForumRequest(String topicid, String ldap, String message, Response.Listener<String> listener) {
        super(Method.POST, FORUM_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("topic_id", topicid);
        params.put("ldap",ldap);
        params.put("post_content",message);
    }

    @Override
    public Map<String, String> getParams() {
        //Log.v("RegisterRequest", "getParams() called");
        return params;
    }
}