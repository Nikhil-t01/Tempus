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
 * Class used to create a event specific adapter to display all events the user has accepted.
 */
public class EventAdapter extends ArrayAdapter<Event> {
    String events, pending_events;

    /**
     *
     * @param context Context that the adapter will refer to
     * @param pEvents_list ArrayList of pending events of the user
     * @param events Encoded string of event id's of all events accepted by the user
     * @param pending_events Encoded string of event id's of all pending events for the user
     */
    public EventAdapter(Activity context, ArrayList<Event> pEvents_list, String events, String pending_events){
        super(context, 0, pEvents_list);
        this.events = events;
        this.pending_events = pending_events;
    }

    /**
     * Function for providing data to the layout file for representation
     * @param position Indicates position at which user has clicked
     * @param convertView Current view to be added to the list view
     * @param parent Parent ViewGroup of the adapter
     * @return ListItemView with required data
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        Event currEvent = getItem(position);
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.pending_item, parent, false);
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