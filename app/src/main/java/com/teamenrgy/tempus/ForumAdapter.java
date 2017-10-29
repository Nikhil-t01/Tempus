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
 * Class used to create a post specific adapter to display all posts of a topic
 */
public class ForumAdapter extends ArrayAdapter<Message> {
    /**
     * Constructor for this class
     * @param context Context the adapter will refer to
     * @param messages ArrayList of posts of that topic
     */
    public ForumAdapter(Activity context, ArrayList<Message> messages)
    {
        super(context, 0, messages);
    }

    /**
     *  Function for providing data to the layout file for representation
     * @param position Indicates position at which user has clicked
     * @param convertView current view to be added to the listview
     * @param parent  Parent ViewGroup for the current view
     * @return List item view with required data
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Message currentMessage = getItem(position);
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.forum_item, parent, false);
        }
        TextView forum_message = listItemView.findViewById(R.id.forum_post);
        forum_message.setText(currentMessage.getMessage());
        TextView forum_user = listItemView.findViewById(R.id.forum_user);
        forum_user.setText(currentMessage.getName());
        TextView forum_date = listItemView.findViewById(R.id.forum_date);
        forum_date.setText(currentMessage.getDate());
        return listItemView;
    }
}