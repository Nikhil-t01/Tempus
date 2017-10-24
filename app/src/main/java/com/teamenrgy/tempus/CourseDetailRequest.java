package com.teamenrgy.tempus;

/**
 * Created by nikhil-t on 12/10/17.
 */

import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class CourseDetailRequest extends StringRequest {
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/CourseDetail.php";
    private Map<String, String> params;

    public CourseDetailRequest(String courseid, Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("course_id", courseid);

    }

    @Override
    public Map<String, String> getParams() {
        //Log.v("RegisterRequest", "getParams() called");
        return params;
    }
}