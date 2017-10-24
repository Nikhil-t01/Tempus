package com.teamenrgy.tempus;

/**
 * Created by nikhil-t on 12/10/17.
 */

public class Message {
    private String message;
    private String name;
    private String date;

    public Message(String message, String name, String date){this.message = message; this.name = name; this.date = date;}

    public String getMessage(){return message;}
    public String getName(){return name;}
    public String getDate(){return date;}
}