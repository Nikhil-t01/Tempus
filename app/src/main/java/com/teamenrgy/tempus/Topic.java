package com.teamenrgy.tempus;

public class Topic {
    private String subject;
    private String date;
    private String cat;
    private String user_name;
    public String id;
    public Topic(String subject, String date, String cat, String id, String user_name){this.subject = subject; this.date = date; this.cat = cat; this.id = id; this.user_name = user_name;}
    public String getSubject(){return subject;}
    public String getDate() {return date;}
    public String getCat() {return cat; }

    public String getUser_name() {return user_name;}
}