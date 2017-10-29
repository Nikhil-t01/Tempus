package com.teamenrgy.tempus;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Class used to enable 'swiping' to accept or discard an event from pending events of the user. Left to Right swiping discards an event, while swiping an event Right to Left adds it to the users' list of accepted events.
 */
public class OnSwipeTouchListener implements View.OnTouchListener {
    private ListView list;
    /**
     * An instance of the GestureDetector class to detect touches made by the user
     */
    private GestureDetector gestureDetector;
    private Context context;
    private EventAdapter eventAdapter;
    String events;

    /**
     * Constructor for this class
     * @param ctx Context for this class
     * @param list ListView containing a list of events of the user
     * @param eventAdapter The EventAdapter for the list of events of the user
     * @param events Encoded list of event id's of accepted events of the user
     */
    public OnSwipeTouchListener(Context ctx, ListView list, EventAdapter eventAdapter, String events) {
        gestureDetector = new GestureDetector(ctx, new GestureListener());
        context = ctx;
        this.list = list;
        this.eventAdapter = eventAdapter;
        this.events = events;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        v.getParent().requestDisallowInterceptTouchEvent(true);
        return gestureDetector.onTouchEvent(event);
    }

    /**
     * Function which discards a pending event when the user right-swipes the list item denoting the event.
     * Event simply removed from pending events
     * @param pos Position of the event in the adapter which needs to be discarded
     */
    public void onSwipeRight(int pos) {
        eventAdapter.remove(eventAdapter.getItem(pos));
    }

    /**
     * Function which accepts a pending event when the user left-swipes the list item denoting the event.
     * Event removed from pending events and added into accepted events
     * @param pos Position of the event in the adapter which needs to be accepted
     */
    public void onSwipeLeft(int pos) {
        events += eventAdapter.getItem(pos).getId();
        eventAdapter.remove(eventAdapter.getItem(pos));
    }

    /**
     * Class for interpreting the gestures made by the user, when interacting with the interface.
     */
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
