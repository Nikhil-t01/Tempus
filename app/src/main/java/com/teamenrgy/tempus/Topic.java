package com.teamenrgy.tempus;

/**
 * Class used to store topics in discussion forum, both for courses and department efficiently
 */
public class Topic {
    private String subject;
    private String date;
    private String cat;
    private String user_name;
    public String id;

    /**
     * Constructor for this class
     * @param subject Subject of the topic
     * @param date Date on which topic is posted
     * @param cat Category, i.e., to which course or department that topic belongs to
     * @param id ID of the topic
     * @param user_name User who posted the topic
     */
    public Topic(String subject, String date, String cat, String id, String user_name){this.subject = subject; this.date = date; this.cat = cat; this.id = id; this.user_name = user_name;}

    /**
     * Getter for subject of the topic
     * @return Subject of the topic
     */
    public String getSubject(){return subject;}

    /**
     * Getter for date of the topic
     * @return Date on which topic is posted
     */
    public String getDate() {return date;}

    /**
     * Getter for category of the topic
     * @return Category, i.e., to which course or department that topic belongs to
     */
    public String getCat() {return cat; }

    /**
     * Getter for user who posted the topic
     * @return User who posted the topic
     */
    public String getUser_name() {return user_name;}
}