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

public class EventsActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        Intent intent = getIntent();
        final String courses = intent.getStringExtra("courses");
        final String name = intent.getStringExtra("name");
        final String events = intent.getStringExtra("events");
        final String pending_events = intent.getStringExtra("pending_events");
        final String ldap = intent.getStringExtra("ldap");

        TextView courses_tv = findViewById(R.id.courses);
        TextView name_tv = findViewById(R.id.name);
        TextView events_tv = findViewById(R.id.events);
        TextView pending_events_tv = findViewById(R.id.pending_events);
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
}
