package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @class
 * Class used to send request to database for checking Login credentials of user
 */
public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "https://flyingtempus.000webhostapp.com/Login.php"; //URL for php file which checks login credentials from database
    private Map<String, String> params;

    /**
     * Constructor for this class
     * @param ldap LDAP ID of user
     * @param password Password of user
     * @param listener Response listener
     */
    public LoginRequest(String ldap, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("ldap", ldap);
        params.put("password", password);
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
