package com.teamenrgy.tempus;

/**
 * Class used to store details of a message eficiently
 */
public class Message {
    private String message;
    private String name;
    private String date;

    /**
     * Constructor for this class
     * @param message Content of the message
     * @param name User who posted the message
     * @param date Date on which message is posted
     */
    public Message(String message, String name, String date){this.message = message; this.name = name; this.date = date;}

    /**
     * Getter for content of the message
     * @return Content of the message
     */
    public String getMessage(){return message;}

    /**
     * Getter for user who posted the message
     * @return User who posted the message
     */
    public String getName(){return name;}

    /**
     * Getter for date on which message is posted
     * @return Date on which message is posted
     */
    public String getDate(){return date;}
}