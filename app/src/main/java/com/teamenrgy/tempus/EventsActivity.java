package com.teamenrgy.tempus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EventsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Intent intent = getIntent();
        final String courses = intent.getStringExtra("courses");
        final String name = intent.getStringExtra("name");
        final String events = intent.getStringExtra("events");
        final String pending_events = intent.getStringExtra("pending_events");
        final String ldap = intent.getStringExtra("ldap");

        Button myEvents = (Button) findViewById(R.id.MyEvents);
        Button pendingEvents = (Button) findViewById(R.id.PendingEvents);
        Button addEvents = (Button) findViewById(R.id.AddEvent);

        myEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventsActivity.this, MyEventsActivity.class);
                intent.putExtra("ldap", ldap);
                intent.putExtra("events", events);
                intent.putExtra("pending_events", pending_events);
                EventsActivity.this.startActivity(intent);
            }
        });

        pendingEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventsActivity.this, PendingEventsActivity.class);
                intent.putExtra("ldap", ldap);
                intent.putExtra("events", events);
                intent.putExtra("pending_events", pending_events);
                EventsActivity.this.startActivity(intent);
            }
        });

        addEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventsActivity.this, AddEventActivity.class);
                intent.putExtra("ldap", ldap);
                intent.putExtra("events", events);
                intent.putExtra("pending_events", pending_events);
                EventsActivity.this.startActivity(intent);
            }
        });
    }
}
