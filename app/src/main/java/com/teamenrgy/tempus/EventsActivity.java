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

/**
 * This Activity is a swipe-page activity.
 * There are 3 fragments displayed under this activity namely
 * <ul>
 *     <li>MyEvents</li>
 *     <li>Pendong Events</li>
 *     <li>AddEvent</li>
 * </ul>
 */
public class EventsActivity extends FragmentActivity {

    String courses, name, events, pending_events, ldap, dept;
    public HashMap<Integer, Event> event_details;
    TextView events_tv, pending_events_tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        ViewPager pager = findViewById(R.id.viewPager);//!< The {@link ViewPager} that will host the section contents.
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));


        Intent intent = getIntent(); //!< Old intent from which we are getting department, courses, name, events, pending_events, ldap, event_details of every event

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


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private String tabTitles[] = new String[]{"My Events", "Pending Events", "Add Event"};

        /**
         * getItem is called to instantiate the fragment for the given page.
         * @param pos represents the position of user in one of the 3 tabs
         * @return  Fragment (Fragment at ith position)
         */
        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                case 0: return MyEventsFragment.newInstance();
                case 1: return PendingEventsFragment.newInstance();
                case 2: return AddEventFragment.newInstance();
                default: return MyEventsFragment.newInstance();
            }
        }

        /**
         * gets the number of pages to be displayed under this activity.
         * @return 3 (as the requirement is only 3 fragments)
         */
        @Override
        public int getCount() {
            return 3;
        }

        /**
         *
         * @param position position of tab
         * @return CharSequence which represents the name of the tab.
         */
        @Override
        public CharSequence getPageTitle(int position) {

            return tabTitles[position];
        }
    }

    /**
     * On back pressing, takes user to homepage activity
     * Returns to homepage activity
     */
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