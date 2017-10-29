package com.teamenrgy.tempus;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * This is the starting page of the app.
 * Displays an animation of a motivational quote
 * On clicking button, user goes to Login page.
 */
public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AllTheme);
        setContentView(R.layout.activity_main1);

        //Fonts of quote and title
        Typeface font_title = Typeface.createFromAsset(getAssets(), "fonts/FFF_Tusj.ttf");
        Typeface font_quote = Typeface.createFromAsset(getAssets(), "fonts/GrandHotel-Regular.ttf");

        TypeWriter title = (TypeWriter) findViewById(R.id.title);
        title.setText("Tempus");    //App name

        title.setTypeface(font_title);

        TypeWriter quote = (TypeWriter) findViewById(R.id.quote);

        quote.setTypeface(font_quote);

        Button gtstart = (Button) findViewById(R.id.getstart);
        Animation buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_1_animation);
        gtstart.startAnimation(buttonAnimation);


        Random random = new Random();
        int rand = random.nextInt(24); //Change this according to number of quotes available.
        int cnt = 0;
        InputStream in = getResources().openRawResource(R.raw.quotes);


         // Buffered Reader for selecting random quote from "quotes.txt" and displaying it.
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        try {
            String ln = br.readLine();
            while (cnt < rand && ln != null) {
                ln = br.readLine();
                cnt = cnt + 1;
            }

            quote.setCharacterDelay(25); // Time delay between appearance of 2 characters.
            quote.animateText(ln);
            br.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * OnClickListener for "Get Started!" button
         * @return Takes the user to Login page
         */
        gtstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity1.this, LoginActivity.class);
                MainActivity1.this.startActivity(intent);
            }
        });
    }
}