package com.teamenrgy.tempus;



import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used for getting events of a course from database
 */
public class CourseRequest extends StringRequest {
    private static final String COURSE_REQUEST_URL = "https://flyingtempus.000webhostapp.com/Course.php";//URL for php file which gets events of a course from database
    private Map<String, String> params;

    /**
     * Constructor for this class
     * @param name name of course
     * @param listener response listener for the request
     */
    public CourseRequest(String name, Response.Listener<String> listener) {
        super(Method.POST, COURSE_REQUEST_URL, listener, null);
        params = new HashMap<>();
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