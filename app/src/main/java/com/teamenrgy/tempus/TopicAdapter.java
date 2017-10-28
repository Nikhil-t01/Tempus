package com.teamenrgy.tempus;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;



public class TopicAdapter extends ArrayAdapter<Topic> {

    public TopicAdapter(Activity context, ArrayList<Topic> topics)
    {
        super(context, 0, topics);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Topic currentTopic = getItem(position);
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.topic_item, parent, false);
        }
        TextView subject = listItemView.findViewById(R.id.topic_subject);
        TextView date = listItemView.findViewById(R.id.topic_date);
        TextView cat = listItemView.findViewById(R.id.topic_cat);
        TextView name = listItemView.findViewById(R.id.topic_name);
        assert currentTopic != null;
        subject.setText(currentTopic.getSubject());
        name.setText("Thread Started by: "+currentTopic.getUser_name());
        date.setText(currentTopic.getDate());
        cat.setText(currentTopic.getCat());
        if(position%2 == 0) {
            listItemView.setBackgroundColor(Color.parseColor("#DCEDC8"));
        } else {
            listItemView.setBackgroundColor(Color.parseColor("#F0F4C3"));
        }
        return listItemView;
    }
}
