package com.teamenrgy.tempus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 *This is the second and default fragment, which contains pending events of user
 * This uses values from the parent, i.e., EventsActivity.java to update
 */
public class PendingEventsFragment extends Fragment {

    LayoutInflater inflater;
    ViewGroup container;
    Bundle savedInstanceState;

    /**
     * This method is called when the this Fragment gets created.
     * All things in it are updated by getting values from the parent.
     * @param inflater to fill the fragment with activity_pending_events.xml
     * @param container to get the view
     * @param savedInstanceState Saved state of the instance
     * @return updated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        View v = inflater.inflate(R.layout.activity_pending_events, container, false);

        EventsActivity eventsActivity = (EventsActivity) getActivity();

        String events = ((TextView) getActivity().findViewById(R.id.events)).getText().toString();
        String pending_events = ((TextView) getActivity().findViewById(R.id.pending_events)).getText().toString();
        final String ldap = ((TextView) getActivity().findViewById(R.id.ldap)).getText().toString();

        int len = pending_events.length();
        final ArrayList<Event> pEvents_list = new ArrayList<>();

        for(int i=8;i<=len;i+=4){
            Event temp = eventsActivity.event_details.get(Integer.parseInt(pending_events.substring(i-4,i)));
            pEvents_list.add(temp);
        }

        final EventAdapter eventAdapter =
                new EventAdapter(getActivity(), pEvents_list, events, pending_events);
        final ListView listView = v.findViewById(R.id.list);
        listView.setAdapter(eventAdapter);

        final OnSwipeTouchListener ostl = new OnSwipeTouchListener(getActivity(),
                listView, eventAdapter, events);
        listView.setOnTouchListener(ostl);

        Button done = v.findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            /**
             * On clicking Done button , updates pending events and accepted events
             * @param view  view to be updated
             */
            @Override
            public void onClick(View view) {

                String pending_events = "0000";
                for(int i=0;i<eventAdapter.getCount();i++)
                    pending_events += eventAdapter.getItem(i).getId();

                final String pending_events_temp = pending_events;

                Response.Listener<String> Listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
    public static Fragment newInstance() {

        PendingEventsFragment f = new PendingEventsFragment();
        return f;
    }
}