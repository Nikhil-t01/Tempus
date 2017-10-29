package com.teamenrgy.tempus;



import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to get details of a particular course
 */
public class CourseDetailRequest extends StringRequest {
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/CourseDetail.php";//URL for php file which gets details of a course from database
    private Map<String, String> params;

    /**
     * Constructor for this class
     * @param courseid ID of the course
     * @param listener Response listener for the request
     */
    public CourseDetailRequest(String courseid, Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("course_id", courseid);

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