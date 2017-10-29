package com.teamenrgy.tempus;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

/**
 * Class used  to put the typeWriter activity on Welcome Page
 */
public class TypeWriter extends android.support.v7.widget.AppCompatTextView {

    private CharSequence mText;
    private int mIndex;
    private long mDelay = 150; //Default 150ms delay

    /**
     * Constructor1 for this class
     * @param context  context the typewriter refer to
     */
    public TypeWriter(Context context) {
        super(context);
    }

    /**
     * Constructor2 for this class
     * @param context context the typewriter refer to
     * @param attrs Attributes like speed, delay between two characters to be shown
     */
    public TypeWriter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Handler mHandler = new Handler();
    /**
     * Runnable for showing characters
     */
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(mText.subSequence(0, mIndex++));
            if(mIndex <= mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);
            }
        }
    };

    /**
     * Animation of text
     * @param text Text to be animated
     */
    public void animateText(CharSequence text) {
        mText = text;
        mIndex = 0;

        setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    /**
     * Delay between two characters
     * @param millis Delay in milliseconds
     */
    public void setCharacterDelay(long millis) {
        mDelay = millis;
    }
}