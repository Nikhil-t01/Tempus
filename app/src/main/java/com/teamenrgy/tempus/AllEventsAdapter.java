package com.teamenrgy.tempus;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Class used to create an event specific adapter to display pending events of a user
 */
public class AllEventsAdapter extends ArrayAdapter<Event> {
    String events, pending_events;

    /**
     * Constructor for this adapter
     * @param context Context the adapter will refer to
     * @param pEvents_list List of pending events for the adapter to be initialised with
     * @param events Encoded string of events of a user
     * @param pending_events Encoded string of pending events of a user
     */
    public AllEventsAdapter(Activity context, ArrayList<Event> pEvents_list, String events, String pending_events){
        super(context, 0, pEvents_list);
        this.events = events;
        this.pending_events = pending_events;
    }

    /**
     * Function for providing data to the layout file for representation
     * @param position Indicates position at which user has clicked
     * @param convertView current view to be added to the listview
     * @param parent Parent ViewGroup for the current view
     * @return List item view with required data
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        Event currEvent = getItem(position);
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.event_item, parent, false);
        }

        TextView id = listItemView.findViewById(R.id.id);
        TextView desc = listItemView.findViewById(R.id.desc);
        TextView start = listItemView.findViewById(R.id.start);
        TextView end = listItemView.findViewById(R.id.end);
        TextView user = listItemView.findViewById(R.id.user);
        TextView cat = listItemView.findViewById(R.id.cat);

        assert currEvent != null;
        id.setText(currEvent.getId());
        desc.setText(currEvent.description);
        start.setText(currEvent.start_time);
        end.setText(currEvent.end_time);
        user.setText(currEvent.user);
        cat.setText(currEvent.category);

        return listItemView;
    }
}