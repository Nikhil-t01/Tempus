package com.teamenrgy.tempus;

/**
 * Created by nikhil-t on 12/10/17.
 */

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gurparkash on 1/10/17.
 */

public class DayAdapter extends ArrayAdapter<String> {

    public DayAdapter(Activity context, ArrayList<String> courses_list)
    {
        super(context, 0, courses_list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String currentCourse = getItem(position);
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView course_code = listItemView.findViewById(R.id.course_code);
        course_code.setText(currentCourse);
        return listItemView;
    }
}
