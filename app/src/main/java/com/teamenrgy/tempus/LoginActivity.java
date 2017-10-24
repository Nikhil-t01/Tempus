package com.teamenrgy.tempus;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is Login Page,
 * If user is already registered, takes to Home page.
 * Else takes to Register Activity for registration.
 * -------------------------
 * Also checks if password entered is correct or not.
 */

public class LoginActivity extends AppCompatActivity {

    public static Toast transitionToast;
    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.ldap);
        final EditText etPassword = (EditText) findViewById(R.id.password);
        final Button bSignIn = (Button) findViewById(R.id.sign_in_button);

        bSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 setContentView(R.layout.progess_bar);

                final String Username = etUsername.getText().toString();
                final String Password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    /**
                     * Checking Logic of Login
                     * @param response
                     */
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int success = jsonObject.getInt("success");
                            /**
                             * If success ==1 , user exists and password is right.
                             * Takes to home page
                             */
                            if (success == 1) {
                                String name = jsonObject.getString("name");
                                String username = jsonObject.getString("ldap");
                                String courses = jsonObject.getString("courses");
                                String dept = jsonObject.getString("dept");
                                Intent intent= new Intent(LoginActivity.this, Homepage.class);
                                intent.putExtra("name", name);
                                intent.putExtra("ldap", username);
                                intent.putExtra("courses", courses);
                                intent.putExtra("dept", dept);
                              /*  transitionToast.Toast.makeText(this, "loading", Toast.LENGTH_LONG);
                                transitionToast.show();*/
                                LoginActivity.this.startActivity(intent);
                            }
                            /**
                             * If success ==2 , user exists but password is wrong
                             * Stays on login activity
                             */
                            else if (success == 2) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                            /**
                             * Else user does not exists, or LDAP is wrong
                             * Takes to Registration page
                             * If LDAP is wrong, user can come back to Login Page by clicking on back button
                             */
                            else {
                                Intent intent4 = new Intent(LoginActivity.this, RegisterActivity.class);
                                intent4.putExtra("ldap", Username);
                                intent4.putExtra("password", Password);
                                LoginActivity.this.startActivity(intent4);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(Username, Password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

    }


}
