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
 * Class used to create a course specific adapter to display all courses for which the user has registered
 */
public class CourseAdapter extends ArrayAdapter<Course> {

    /**
     * Constructor for this class
     * @param context Context the adapter will refer to
     * @param courses_list ArrayList of courses undertaken by the user
     */
    public CourseAdapter(Activity context, ArrayList<Course> courses_list)
    {
        super(context, 0, courses_list);
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
