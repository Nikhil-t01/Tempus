package com.teamenrgy.tempus;

import java.io.Serializable;

/**
 * Created by nikhil-t on 24/10/17.
 */

public class Event implements Serializable {
    int id;
    String id2, description, start_time, end_time, user, category;
    public Event(int id){
        this.id = id;
    }

    public Event(int id, String description, String start_time, String end_time, String user, String category){
        this.id = id;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.user = user;
        this.category = category;

        id2 = ""+id;
        if(id < 10) {
            id2 = "0"+id2;
        }
        if(id < 100) {
            id2 = "0"+id2;
        }
        if(id < 1000) {
            id2 = "0"+id2;
        }
    }

    public String getId(){
        return id2;
    }
}
