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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_my_events, container, false);

        String events = ((TextView) getActivity().findViewById(R.id.events)).getText().toString();
        String pending_events = ((TextView) getActivity().findViewById(R.id.pending_events)).getText().toString();
        final String ldap = ((TextView) getActivity().findViewById(R.id.ldap)).getText().toString();

        Toast.makeText(getContext(), "Events: "+events+" Pending Events: "+pending_events, Toast.LENGTH_SHORT).show();
        int len = events.length();
        final ArrayList<Event> events_list = new ArrayList<>();

        for(int i=8;i<=len;i+=4){
            events_list.add(new Event(events.substring(i-4,i)));
        }

        EventAdapter eventAdapter = new EventAdapter(getActivity(), events_list, events, pending_events);
        ListView listView = (ListView) v.findViewById(R.id.list);
        listView.setAdapter(eventAdapter);

        return v;
    }

    public static MyEventsFragment newInstance() {

        MyEventsFragment f = new MyEventsFragment();
        return f;
    }
}