package com.teamenrgy.tempus;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by nikhil-t on 24/10/17.
 */

public class OnSwipeTouchListener implements View.OnTouchListener {

    ListView list;
    private GestureDetector gestureDetector;
    private Context context;
    EventAdapter eventAdapter;
    String events;

    public OnSwipeTouchListener(Context ctx, ListView list, EventAdapter eventAdapter, String events) {
        gestureDetector = new GestureDetector(ctx, new GestureListener());
        context = ctx;
        this.list = list;
        this.eventAdapter = eventAdapter;
        this.events = events;
    }

    public OnSwipeTouchListener() {
        super();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void onSwipeRight(int pos) {
        //Do what you want after swiping left to right
        eventAdapter.remove(eventAdapter.getItem(pos));
    }

    public void onSwipeLeft(int pos) {
        events += eventAdapter.getItem(pos).id;
        eventAdapter.remove(eventAdapter.getItem(pos));
        //Do what you want after swiping right to left
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        private int getPostion(MotionEvent e1) {
            return list.pointToPosition((int) e1.getX(), (int) e1.getY());
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY)
                    && Math.abs(distanceX) > SWIPE_THRESHOLD
                    && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0)
                    onSwipeRight(getPostion(e1));
                else
                    onSwipeLeft(getPostion(e1));
                return true;
            }
            return false;
        }
    }
}
