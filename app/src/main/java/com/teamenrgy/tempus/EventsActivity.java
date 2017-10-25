package com.teamenrgy.tempus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.HashMap;

public class EventsActivity extends FragmentActivity {

    String courses, name, events, pending_events, ldap, dept;
    public HashMap<Integer, Event> event_details;
    TextView events_tv, pending_events_tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        ViewPager pager = findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        Intent intent = getIntent();

        dept = intent.getStringExtra("dept");

        courses = intent.getStringExtra("courses");
        name = intent.getStringExtra("name");
        events = intent.getStringExtra("events");
        pending_events = intent.getStringExtra("pending_events");
        ldap = intent.getStringExtra("ldap");
        event_details = (HashMap<Integer, Event>) intent.getSerializableExtra("event_details");

        TextView courses_tv = findViewById(R.id.courses);
        TextView name_tv = findViewById(R.id.name);
        events_tv = findViewById(R.id.events);
        pending_events_tv = findViewById(R.id.pending_events);
        TextView ldap_tv = findViewById(R.id.ldap);

        courses_tv.setText(courses);
        name_tv.setText(name);
        events_tv.setText(events);
        pending_events_tv.setText(pending_events);
        ldap_tv.setText(ldap);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                case 0: return MyEventsFragment.newInstance();
                case 1: return PendingEventsFragment.newInstance();
                case 2: return AddEventFragment.newInstance();
                default: return MyEventsFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        events = events_tv.getText().toString();
        pending_events = pending_events_tv.getText().toString();
        Intent intent = new Intent(this, Homepage.class);
        intent.putExtra("name", name);
        intent.putExtra("ldap", ldap);
        intent.putExtra("events", events);
        intent.putExtra("pending_events", pending_events);
        intent.putExtra("dept", dept);
        intent.putExtra("courses", courses);
        this.startActivity(intent);
    }
}
