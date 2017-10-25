package com.teamenrgy.tempus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyEventsFragment extends Fragment {

    String events, pending_events;
    LayoutInflater inflater;
    ViewGroup container;
    Bundle savedInstanceState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        Toast.makeText(getContext(), "OnCreateView called", Toast.LENGTH_SHORT).show();
        View v = inflater.inflate(R.layout.activity_my_events, container, false);

        EventsActivity eventsActivity = (EventsActivity) getActivity();
        

        events = ((TextView) getActivity().findViewById(R.id.events)).getText().toString();
        pending_events = ((TextView) getActivity().findViewById(R.id.pending_events)).getText().toString();

        Toast.makeText(getContext(), "Events: "+events+" Pending Events: "+pending_events, Toast.LENGTH_SHORT).show();
        int len = events.length();
        final ArrayList<Event> events_list = new ArrayList<>();

        for(int i=8;i<=len;i+=4){
            Event temp = eventsActivity.event_details.get(Integer.parseInt(events.substring(i-4,i)));
            events_list.add(temp);
        }

        EventAdapter eventAdapter = new EventAdapter(getActivity(), events_list, events, pending_events);
        ListView listView = v.findViewById(R.id.list);
        listView.setAdapter(eventAdapter);

        return v;
    }

    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible && isResumed())
        {
            onCreateView(inflater, container, savedInstanceState);
        }
    }

    public static MyEventsFragment newInstance() {

        MyEventsFragment f = new MyEventsFragment();
        return f;
    }
}