package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rajat Rathi on 11-10-2017.
 */

public class AddTopicRequest extends StringRequest {
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/AddTopic.php";
    private Map<String, String> params;

    public AddTopicRequest(String subject, String cat, String name, Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("subject" , subject);
        params.put("cat", cat);
        params.put("name", name);
    }

    @Override
    public Map<String, String> getParams() {
        //Log.v("RegisterRequest", "getParams() called");
        return params;
    }
}