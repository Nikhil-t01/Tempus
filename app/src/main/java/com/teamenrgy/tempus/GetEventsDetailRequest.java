package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikhil-t on 3/10/17.
 */

public class GetEventsDetailRequest extends StringRequest{
    private static final String EVENTS_DETAIL_REQUEST_URL = "https://flyingtempus.000webhostapp.com/GetEventsDetails.php";
    private Map<String, String> params;

    public GetEventsDetailRequest(Response.Listener<String> listener) {
        super(Method.POST, EVENTS_DETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}