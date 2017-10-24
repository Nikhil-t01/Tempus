package com.teamenrgy.tempus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyEventsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);
        Intent intent = getIntent();
        String events = intent.getStringExtra("events");
        String pending_events = intent.getStringExtra("pending_events");
        final String ldap = intent.getStringExtra("ldap");
        Toast.makeText(getBaseContext(), "Events: "+events+" Pending Events: "+pending_events, Toast.LENGTH_SHORT).show();
        int len = events.length();
        final ArrayList<Event> events_list = new ArrayList<>();

        for(int i=8;i<=len;i+=4){
            events_list.add(new Event(events.substring(i-4,i)));
        }

        EventAdapter eventAdapter = new EventAdapter(this, events_list, events, pending_events);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(eventAdapter);
    }
}
