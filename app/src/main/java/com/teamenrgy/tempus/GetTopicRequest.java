package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikhil_t on 11-10-2017.
 */
public class GetTopicRequest extends StringRequest {
    private static final String GETMESSAGE_REQUEST_URL = "https://flyingtempus.000webhostapp.com/GetTopics.php";
    private Map<String, String> params;

    public GetTopicRequest( String cat, Response.Listener<String> listener) {
        super(Method.POST, GETMESSAGE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("cat",cat);

    }

    @Override
    public Map<String, String> getParams() {
        //Log.v("RegisterRequest", "getParams() called");
        return params;
    }

}
