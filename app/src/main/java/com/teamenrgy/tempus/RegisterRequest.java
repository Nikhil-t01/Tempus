package com.teamenrgy.tempus;

/**
 * Created by nikhil-t on 12/10/17.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class RegisterRequest extends StringRequest {
    /**
     * URL for registrating user in database
     */
    private static final String REGISTER_REQUEST_URL = "https://flyingtempus.000webhostapp.com/Register.php";
    private Map<String, String> params;

    /**
     * Posting parameters for registration to PHP file on web server for registration
     * @param name
     * @param ldap
     * @param courses
     * @param password
     * Password typed in login activity
     * @param passwordC
     * Password typed in register activity
     * @param dept
     * @param listener
     */
    public RegisterRequest(String name, String ldap, String courses, String password, String passwordC, String dept, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("ldap", ldap);
        params.put("password", password);
        params.put("courses", courses);
        params.put("passwordC", passwordC);
        params.put("dept", dept);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
