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

/**
 * Class used to implement the discussion forum for a particular topic under a particular category
 */
public class ForumActivity extends AppCompatActivity {

    /**
     * Function called everytime this activity is opened.
     */
    @Override
    protected void onResume() {
        super.onResume();
        /**
         * Setting layout of the activity as 'activity_forum.xml'
         */
        setContentView(R.layout.activity_forum);
        TextView title = (TextView) findViewById(R.id.forumTitle);  //!< Title for the forum
        final ImageView bPost = (ImageView) findViewById(R.id.forumPost); //!< ImageView to be clicked for posting a message
        final EditText etMessage = (EditText) findViewById(R.id.etMessage); //!< EditText for the user to write messages they want to post
        final String subject = getIntent().getStringExtra("subject"); //!< Subject for the discussion forum
        final String topic_id = getIntent().getStringExtra("topic_id"); //!< Topic ID of the topic in the database to allow easy access
        title.setText("Topic: " + subject);
        Typeface title_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-BlackItalic.ttf");
        title.setTypeface(title_font);

        final String name = getIntent().getStringExtra("name"); //!< Name of the current user
        final String ldap = getIntent().getStringExtra("ldap"); //!< LDAP ID of the current user
        final ArrayList<Message> messages_list = new ArrayList<>(); //!< ArrayList containing the list of messages/posts presently posted on the forum
        final ForumAdapter messageAdapter =
                new ForumAdapter(this, messages_list); //!< Adapter for the list of messages
        final ListView listView = (ListView) findViewById(R.id.forum_list);

        /**
         * Response Listener which gets all messages for a topic as response from the database and initialises the ForumAdapter with them
         */
        Response.Listener<String> deflistener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(getBaseContext(), response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    int count = jsonResponse.getInt("count");

                    for(int i = 0; i < count; i++) {
                        messages_list.add(new Message(jsonResponse.getString("message"+i), jsonResponse.getString("name"+i), jsonResponse.getString("date"+i))); // Replace LDAP by name
                    }
                    listView.setAdapter(messageAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        /**
         * Procedure for adding request to queue
         */
        GetMessageRequest getmsgRequest = new GetMessageRequest(topic_id,  deflistener);
        RequestQueue queue = Volley.newRequestQueue(ForumActivity.this);
        queue.add(getmsgRequest);

        /**
         * OnClickListener to keep track of whether or not the user has clicked
         */
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
