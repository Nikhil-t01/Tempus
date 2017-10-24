package com.teamenrgy.tempus;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nikhil-t on 24/10/17.
 */

public class EventAdapter extends ArrayAdapter<Event> {
    String events, pending_events;
    public EventAdapter(Activity context, ArrayList<Event> pEvents_list, String events, String pending_events){
        super(context, 0, pEvents_list);
        this.events = events;
        this.pending_events = pending_events;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        Event currEvent = getItem(position);
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.event_item, parent, false);
        }
        final TextView event_id = listItemView.findViewById(R.id.event_id);
        assert currEvent != null;
        event_id.setText(currEvent.getId());

        Button Accept = listItemView.findViewById(R.id.accept);
        Button Decline = listItemView.findViewById(R.id.decline);

        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                
            }
        });


        return listItemView;
    }
}
