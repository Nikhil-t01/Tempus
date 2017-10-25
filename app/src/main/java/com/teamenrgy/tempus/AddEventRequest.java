package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rajat Rathi on 24-10-2017.
 */

public class AddEventRequest extends StringRequest {
    private static final String COURSEDETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/AddEvent.php";
    private Map<String, String> params;

    public AddEventRequest(String category, String description, String name, String stime, String etime ,Response.Listener<String> listener) {
        super(Method.POST, COURSEDETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("category", category);
        params.put("description", description);
        params.put("name", name);
        params.put("stime", stime);
        params.put("etime", etime);
    }

    @Override
    public Map<String, String> getParams() {
        //Log.v("RegisterRequest", "getParams() called");
        return params;
    }
}
