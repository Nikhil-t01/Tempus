package com.teamenrgy.tempus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * Created by nikhil-t on 24/10/17.
 */

public class PendingEventsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_pending_events, container, false);

        String events = ((TextView) getActivity().findViewById(R.id.events)).getText().toString();
        String pending_events = ((TextView) getActivity().findViewById(R.id.pending_events)).getText().toString();
        final String ldap = ((TextView) getActivity().findViewById(R.id.ldap)).getText().toString();

        int len = pending_events.length();
        final ArrayList<Event> pEvents_list = new ArrayList<>();

        for(int i=8;i<=len;i+=4){
            pEvents_list.add(new Event(pending_events.substring(i-4,i)));
        }

        final EventAdapter eventAdapter =
                new EventAdapter(getActivity(), pEvents_list, events, pending_events);
        final ListView listView = (ListView) v.findViewById(R.id.list);
        listView.setAdapter(eventAdapter);

        final OnSwipeTouchListener ostl = new OnSwipeTouchListener(getActivity(), listView, eventAdapter, events);
        listView.setOnTouchListener(ostl);

        Button done = (Button) v.findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pending_events = "0000";
                for(int i=0;i<eventAdapter.getCount();i++)
                    pending_events += eventAdapter.getItem(i).getId();

                final String pending_events_temp = pending_events;

                Response.Listener<String> Listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), "Updated in Database!", Toast.LENGTH_SHORT).show();

                        ((TextView) getActivity().findViewById(R.id.pending_events)).setText(pending_events_temp);
                        ((TextView) getActivity().findViewById(R.id.events)).setText(ostl.events);
                    }
                };

                EventsRequest eventsRequest = new EventsRequest(ldap, ostl.events, pending_events, Listener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(eventsRequest);

            }
        });

        return v;
    }

    public static Fragment newInstance() {

        PendingEventsFragment f = new PendingEventsFragment();
        return f;
    }
}