package com.teamenrgy.tempus;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ForumActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_forum);
        TextView title = (TextView) findViewById(R.id.forumTitle);
        final ImageView bPost = (ImageView) findViewById(R.id.forumPost);
        final EditText etMessage = (EditText) findViewById(R.id.etMessage);
        final String subject = getIntent().getStringExtra("subject");
        final String topic_id = getIntent().getStringExtra("topic_id");
        title.setText("Topic: " + subject);
        Typeface title_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-BlackItalic.ttf");
        title.setTypeface(title_font);

        final String name = getIntent().getStringExtra("name");
        final String ldap = getIntent().getStringExtra("ldap");
        final ArrayList<Message> messages_list = new ArrayList<>();
        final ForumAdapter messageAdapter =
                new ForumAdapter(this, messages_list);
        final ListView listView = (ListView) findViewById(R.id.forum_list);

        Response.Listener<String> deflistener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(getBaseContext(), response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    int count = jsonResponse.getInt("count");

                    for(int i = 0; i < count; i++) {
                        messages_list.add(new Message(jsonResponse.getString("message"+i), jsonResponse.getString("ldap"+i), jsonResponse.getString("date"+i))); // Replace LDAP by name
                    }
                    listView.setAdapter(messageAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        GetMessageRequest getmsgRequest = new GetMessageRequest(topic_id,  deflistener);
        RequestQueue queue = Volley.newRequestQueue(ForumActivity.this);
        queue.add(getmsgRequest);

        bPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String message = etMessage.getText().toString();
                Response.Listener<String> Listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                   //     Toast.makeText(getBaseContext(), response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            messages_list.add(new Message(etMessage.getText().toString(), name, jsonResponse.getString("date")));
                            listView.setAdapter(messageAdapter);
                            etMessage.setText("");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                ForumRequest forumRequest = new ForumRequest(topic_id, ldap, message, Listener);
                RequestQueue queue = Volley.newRequestQueue(ForumActivity.this);
                queue.add(forumRequest);
            }
        });
    }
}
