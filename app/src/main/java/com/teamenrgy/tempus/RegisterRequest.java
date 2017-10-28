package com.teamenrgy.tempus;



import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @class
 * Class used to send request to database for registering user in database
 */
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://flyingtempus.000webhostapp.com/Register.php"; //URL for php file which registers user in database
    private Map<String, String> params;

    /**
     * Constructor for this class
     * @param name Name of the user
     * @param ldap LDAP ID of the user
     * @param courses Courses for which user is registered
     * @param password Password typed in login activity
     * @param passwordC Password typed in register activity
     * @param dept Department of user
     * @param listener Response Listener
     */
    public RegisterRequest(String name, String ldap, String courses, String password, String passwordC, String dept,String pending_events, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("ldap", ldap);
        params.put("password", password);
        params.put("courses", courses);
        params.put("passwordC", passwordC);
        params.put("dept", dept);
        params.put("pending_events", pending_events);
    }

    /**
     * Function to get the parameters to be posted to php
     * @return
     */
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
