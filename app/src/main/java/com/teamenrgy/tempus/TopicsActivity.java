package com.teamenrgy.tempus;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

public class TopicsActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_topics);

        final String cat_title = getIntent().getStringExtra("cat_title");
        final String cat_name = getIntent().getStringExtra("cat_name");
        final String ldap = getIntent().getStringExtra("ldap");
        final String name = getIntent().getStringExtra("name");
        final EditText topic_name = (EditText) findViewById(R.id.topic_name);
        final ImageView start_topic = (ImageView) findViewById(R.id.start_topic);
        Typeface title_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-BlackItalic.ttf");

        final ArrayList<Topic> topics_list = new ArrayList<>();
        final TopicAdapter topicAdapter =
                new TopicAdapter(this, topics_list);
        final ListView listView = (ListView) findViewById(R.id.topics_list);
        final TextView title = (TextView) findViewById(R.id.topics_title);
        title.setText(cat_title + " Forum");
        title.setTypeface(title_font);


        Response.Listener<String> deflistener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    int count = jsonResponse.getInt("count");

                    for (int i = 0; i < count; i++) {
                        topics_list.add(new Topic(jsonResponse.getString("subject" + i), jsonResponse.getString("date" + i)
                                , jsonResponse.getString("cat" + i), jsonResponse.getInt("id" + i) + "", jsonResponse.getString("name" + i)));
                    }
                    listView.setAdapter(topicAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                setContentView(R.layout.progess_bar);
                final Topic currentTopic = topics_list.get(position);
                Intent intent = new Intent(TopicsActivity.this, ForumActivity.class);
                intent.putExtra("subject", currentTopic.getSubject());
                intent.putExtra("name", name);
                intent.putExtra("ldap", ldap);
                intent.putExtra("topic_id", currentTopic.id);
                TopicsActivity.this.startActivity(intent);
            }
        });

        start_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> addTopicListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                       // Toast.makeText(getBaseContext(), response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            topics_list.add(new Topic(topic_name.getText().toString(), jsonResponse.getString("date"), cat_name, jsonResponse.getInt("id")+"", name));
                            listView.setAdapter(topicAdapter);
                            topic_name.setText("");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                AddTopicRequest addtpcRequest = new AddTopicRequest(topic_name.getText().toString(), cat_name, ldap, addTopicListener);
                RequestQueue queue = Volley.newRequestQueue(TopicsActivity.this);
                queue.add(addtpcRequest);
            }
        });

        GetTopicRequest gettpcRequest = new GetTopicRequest(cat_name, deflistener);
        RequestQueue queue = Volley.newRequestQueue(TopicsActivity.this);
        queue.add(gettpcRequest);
    }
}