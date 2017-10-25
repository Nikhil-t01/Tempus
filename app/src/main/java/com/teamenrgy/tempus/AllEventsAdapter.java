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

public class AllEventsAdapter extends ArrayAdapter<Event> {
    String events, pending_events;
    public AllEventsAdapter(Activity context, ArrayList<Event> pEvents_list, String events, String pending_events){
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