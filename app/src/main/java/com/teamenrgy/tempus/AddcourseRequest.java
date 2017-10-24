package com.teamenrgy.tempus;

/**
 * Created by nikhil-t on 12/10/17.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class AddcourseRequest extends StringRequest {
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/ACourse.php";
    private Map<String, String> params;

    public AddcourseRequest(String courseid, String ldap, String events, String pending_events,  Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("courses", courseid);
        params.put("ldap",ldap);
        params.put("events", events);
        params.put("pending_events", pending_events);

    }

    @Override
    public Map<String, String> getParams() {
        //Log.v("RegisterRequest", "getParams() called");
        return params;
    }
}