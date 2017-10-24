package com.teamenrgy.tempus;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gurparkash on 1/10/17.
 */

public class CourseAdapter extends ArrayAdapter<Course> {

    public CourseAdapter(Activity context, ArrayList<Course> courses_list)
    {
        super(context, 0, courses_list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Course currentCourse = getItem(position);
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Pair<String, String> course_code_name = currentCourse.getCourseName();
        TextView course_code = listItemView.findViewById(R.id.course_code);
        TextView course_name = listItemView.findViewById(R.id.course_name);
        course_code.setText(course_code_name.first);
        course_name.setText(course_code_name.second);

        return listItemView;
    }
}
