package com.teamenrgy.tempus;



import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class CourseRequest extends StringRequest {
    private static final String COURSE_REQUEST_URL = "https://flyingtempus.000webhostapp.com/Course.php";
    private Map<String, String> params;

    public CourseRequest(String name, Response.Listener<String> listener) {
        super(Method.POST, COURSE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);

    }

    @Override
    public Map<String, String> getParams() {
        //Log.v("RegisterRequest", "getParams() called");
        return params;
    }
}