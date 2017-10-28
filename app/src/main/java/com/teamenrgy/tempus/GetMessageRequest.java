package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class GetMessageRequest extends StringRequest{
    private static final String GETMESSAGE_REQUEST_URL = "https://flyingtempus.000webhostapp.com/GetMessages.php";
    private Map<String, String> params;

    public GetMessageRequest( String topicid, Response.Listener<String> listener) {
        super(Method.POST, GETMESSAGE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("topic_id",topicid);
    }

    @Override
    public Map<String, String> getParams() {
        //Log.v("RegisterRequest", "getParams() called");
        return params;
    }

}