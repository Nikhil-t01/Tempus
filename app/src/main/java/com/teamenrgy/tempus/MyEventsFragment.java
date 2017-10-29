package com.teamenrgy.tempus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *This is the first fragment, which contains accepted events of user
 * This uses values from the parent, i.e., EventsActivity.java to update
 */
public class MyEventsFragment extends Fragment {

    String events, pending_events;
    LayoutInflater inflater;
    ViewGroup container;
    Bundle savedInstanceState;

    /**
     * This method is called when the this Fragment gets created.
     * All things in it are updated by getting values from the parent.
     * @param inflater to fill the fragment with activity_my_events.xml
     * @param container to get the view
     * @param savedInstanceState Saved state of the instance
     * @return updated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        View v = inflater.inflate(R.layout.activity_my_events, container, false);

        EventsActivity eventsActivity = (EventsActivity) getActivity();


        events = ((TextView) getActivity().findViewById(R.id.events)).getText().toString();
        pending_events = ((TextView) getActivity().findViewById(R.id.pending_events)).getText().toString();

        int len = events.length();
        final ArrayList<Event> events_list = new ArrayList<>();

        for(int i=8;i<=len;i+=4){
            Event temp = eventsActivity.event_details.get(Integer.parseInt(events.substring(i-4,i)));
            events_list.add(temp);
        }

        AllEventsAdapter eventAdapter = new AllEventsAdapter(getActivity(), events_list, events, pending_events);
        ListView listView = v.findViewById(R.id.list);
        listView.setAdapter(eventAdapter);

        return v;
    }

    /**
     * Setting fragment to visible or invisible
     * @param visible (boolean)
     */
    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible && isResumed())
        {
            onCreateView(inflater, container, savedInstanceState);
        }
    }

    /**
     * Returninig updated instance of a fragment
     * @return instance of a fragment
     */
    public static MyEventsFragment newInstance() {

        MyEventsFragment f = new MyEventsFragment();
        return f;
    }
}