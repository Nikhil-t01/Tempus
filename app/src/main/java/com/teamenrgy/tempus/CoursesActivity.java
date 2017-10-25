package com.teamenrgy.tempus;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_courses);

        final String courses = getIntent().getStringExtra("courses");
        final String ldap = getIntent().getStringExtra("ldap");
        final String name_p = getIntent().getStringExtra("name");

        TextView title = findViewById(R.id.courses_title);
        Typeface title_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-BlackItalic.ttf");
        title.setTypeface(title_font);

        int l = courses.length();
        final ArrayList<Course> courses_list = new ArrayList<>();
        for(int i = 6; i<=l; i=i+3) {
            courses_list.add(new Course(courses.substring(i-3, i)));
        }

        CourseAdapter wordAdapter =
                new CourseAdapter(this, courses_list);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                setContentView(R.layout.progess_bar);
                final String cid = courses_list.get(position).getCourse();

                final Pair<String, String> course_code_name = courses_list.get(position).getCourseName();
                final String course_name = course_code_name.second;
                final String course_code = course_code_name.first;

   //             Toast.makeText(getBaseContext(), "working!", Toast.LENGTH_LONG).show();
                Response.Listener<String> Listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
     //                   Toast.makeText(getBaseContext(), response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String mon = jsonResponse.getString("mon");
                            String tue = jsonResponse.getString("tue");
                            String wed = jsonResponse.getString("wed");
                            String thu = jsonResponse.getString("thu");
                            String fri = jsonResponse.getString("fri");
                            String sat = jsonResponse.getString("sat");
                            String sun = jsonResponse.getString("sun");
                            String name = jsonResponse.getString("name");
                            String venue = jsonResponse.getString("venue");

                            //Toast.makeText(getBaseContext(), response, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(CoursesActivity.this, CourseDetailActivity.class);
                            intent.putExtra("name", name); //name of course (code, actually)
                            intent.putExtra("course_name", course_name);
                            intent.putExtra("course_code", course_code);
                            intent.putExtra("mon", mon);
                            intent.putExtra("tue", tue);
                            intent.putExtra("wed", wed);
                            intent.putExtra("thu", thu);
                            intent.putExtra("fri", fri);
                            intent.putExtra("sat", sat);
                            intent.putExtra("sun", sun);
                            intent.putExtra("venue", venue);
                            intent.putExtra("ldap", ldap);
                            intent.putExtra("courseid", cid);
                            intent.putExtra("name_p", name_p); //name of person
                            CoursesActivity.this.startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                CourseDetailRequest courseRequest = new CourseDetailRequest(cid,  Listener);
                RequestQueue queue = Volley.newRequestQueue(CoursesActivity.this);
                queue.add(courseRequest);
            }
        });

    }
}