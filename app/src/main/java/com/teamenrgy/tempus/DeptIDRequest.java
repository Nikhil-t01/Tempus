package com.teamenrgy.tempus;

/**
 * Created by nikhil-t on 12/10/17.
 */

import com.android.volley.toolbox.StringRequest;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rajat Rathi on 10-10-2017.
 */


public class DeptIDRequest extends StringRequest {
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/DeptID.php";
    private Map<String, String> params;

    public DeptIDRequest(String dept, Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("dept", dept);
    }

    @Override
    public Map<String, String> getParams() {
        //Log.v("RegisterRequest", "getParams() called");
        return params;
    }
}