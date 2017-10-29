package com.teamenrgy.tempus;

import java.io.Serializable;

/**
 * Class used for storing all events of a user efficiently
 */
public class Event implements Serializable {
    int id;
    String id2, description, start_time, end_time, user, category;

    /**
     * Constructor1 for this class
     * @param id id of event
     */
    public Event(int id){
        this.id = id;
    }

    /**
     * Constructor2 for this class
     * @param id id of the event
     * @param description Description of the event
     * @param start_time Start time of event
     * @param end_time End time of event
     * @param user user who  added event
     * @param category category, i.e., to which course or department that event belongs to
     */
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

    /**
     * Getter for the id of event
     * @return id of the event
     */
    public String getId(){
        return id2;
    }
}
