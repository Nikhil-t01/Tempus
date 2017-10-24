package com.teamenrgy.tempus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * Created by Rajat Rathi on 20-10-2017.
 */

public class PendingEventsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_events);

        Intent intent = getIntent();
        final String events = intent.getStringExtra("events");
        final String pending_events = intent.getStringExtra("pending_events");
        final String ldap = intent.getStringExtra("ldap");


        Toast.makeText(getBaseContext(), "Pending Events: "+pending_events, Toast.LENGTH_SHORT).show();
        Toast.makeText(getBaseContext(), "Events: "+events, Toast.LENGTH_SHORT).show();


        int len = pending_events.length();
        final ArrayList<Event> pEvents_list = new ArrayList<>();

        for(int i=8;i<=len;i+=4){
            pEvents_list.add(new Event(pending_events.substring(i-4,i)));
        }

        final EventAdapter eventAdapter =
                new EventAdapter(this, pEvents_list, events, pending_events);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(eventAdapter);

        final OnSwipeTouchListener ostl = new OnSwipeTouchListener(this, listView, eventAdapter, events);
        listView.setOnTouchListener(ostl);

        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PendingEventsActivity.this, EventsActivity.class);
                String pending_events = "0000";
                for(int i=0;i<eventAdapter.getCount();i++)
                    pending_events += eventAdapter.getItem(i).getId();

                Response.Listener<String> Listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getBaseContext(), "Updated in Database!", Toast.LENGTH_SHORT).show();
                    }
                };

                EventsRequest eventsRequest = new EventsRequest(ldap, ostl.events, pending_events, Listener);
                RequestQueue queue = Volley.newRequestQueue(PendingEventsActivity.this);
                queue.add(eventsRequest);
                intent.putExtra("ldap", ldap);
                intent.putExtra("events", ostl.events);
                intent.putExtra("pending_events", pending_events);
                PendingEventsActivity.this.startActivity(intent);
            }
        });
    }
}
