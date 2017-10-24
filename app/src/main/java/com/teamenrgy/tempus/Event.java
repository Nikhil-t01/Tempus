package com.teamenrgy.tempus;

/**
 * Created by nikhil-t on 24/10/17.
 */

public class Event {
    String id, description, day, start_time, end_time, user;
    public Event(String id){
        this.id = id;
    }

    public Event(String id, String description, String day, String start_time, String end_time, String user){
        this.id = id;
        this.description = description;
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
        this.user = user;
    }

    public String getId(){
        return id;
    }
}
