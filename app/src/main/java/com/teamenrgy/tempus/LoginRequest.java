package com.teamenrgy.tempus;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikhil-t on 12/10/17.
 */

public class LoginRequest extends StringRequest {
    /**
     * URL for PHP code of getting user from database
     */
    private static final String LOGIN_REQUEST_URL = "https://flyingtempus.000webhostapp.com/Login.php";
    private Map<String, String> params;

    /**
     * Sends LDAP and password to database to check for user
     * @param ldap
     * @param password
     * @param listener
     * It is a response listener from login activity
     */
    public LoginRequest(String ldap, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("ldap", ldap);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
