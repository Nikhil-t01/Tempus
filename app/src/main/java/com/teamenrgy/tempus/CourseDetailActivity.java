package com.teamenrgy.tempus;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_course_detail);

        TextView title = (TextView) findViewById(R.id.course_title);
        Typeface title_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-BlackItalic.ttf");
        title.setTypeface(title_font);

        Intent oldintent = getIntent();
        String m = oldintent.getStringExtra("mon");
        String tu = oldintent.getStringExtra("tue");
        String w = oldintent.getStringExtra("wed");
        String th = oldintent.getStringExtra("thu");
        String f = oldintent.getStringExtra("fri");
        String sa = oldintent.getStringExtra("sat");
        String su = oldintent.getStringExtra("sun");
        String na = oldintent.getStringExtra("name");
        String ve = oldintent.getStringExtra("venue");
        final String ldap = getIntent().getStringExtra("ldap");
        final String name = getIntent().getStringExtra("name_p");
        final String course_id = getIntent().getStringExtra("courseid");
        final String course_code = getIntent().getStringExtra("course_code");
        final String course_name = getIntent().getStringExtra("course_name");

        FloatingActionButton openForum = findViewById(R.id.material_design_floating_action_menu_item2);
        openForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setContentView(R.layout.progess_bar);

                Intent intent = new Intent(CourseDetailActivity.this, TopicsActivity.class);
                intent.putExtra("cat_name", course_id);
                intent.putExtra("ldap", ldap);
                intent.putExtra("name", name);
                intent.putExtra("cat_title", course_code);
                CourseDetailActivity.this.startActivity(intent);
            }
        });

        title.setText(course_code);

        TextView CourseName = findViewById(R.id.course_name);
        TextView Venue = findViewById(R.id.venue);
        TextView Monday = findViewById(R.id.mon_time);
        TextView Tuesday = findViewById(R.id.tue_time);
        TextView Wednesday = findViewById(R.id.wed_time);
        TextView Thursday = findViewById(R.id.thurs_time);
        TextView Friday = findViewById(R.id.fri_time);
        TextView Saturday = findViewById(R.id.sat_time);
        TextView Sunday = findViewById(R.id.sun_time);

        CourseName.setText(course_name);
        Venue.setText(ve);
        Monday.setText(m);
        Tuesday.setText(tu);
        Wednesday.setText(w);
        Thursday.setText(th);
        Friday.setText(f);
        Saturday.setText(sa);
        Sunday.setText(su);

    }
}
