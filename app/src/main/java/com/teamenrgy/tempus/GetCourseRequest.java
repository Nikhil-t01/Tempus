package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikhil-t on 3/10/17.
 */

public class GetCourseRequest extends StringRequest{
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/GetCourses.php";
    private Map<String, String> params;

    public GetCourseRequest( String ldap, Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("ldap",ldap);

    }

    @Override
    public Map<String, String> getParams() {
        //Log.v("RegisterRequest", "getParams() called");
        return params;
    }

}