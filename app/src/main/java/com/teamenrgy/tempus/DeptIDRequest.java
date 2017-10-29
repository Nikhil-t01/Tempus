package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to get events and ID of a particular department
 */
public class DeptIDRequest extends StringRequest {
    //URL for php file which gets events and ID of a particular department
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/DeptID.php";
    private Map<String, String> params;

    /**
     * Constructor for this class
     * @param dept Name of the department
     * @param listener Response listener for the request
     */
    public DeptIDRequest(String dept, Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("dept", dept);
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