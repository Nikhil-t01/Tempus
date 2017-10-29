package com.teamenrgy.tempus;

import android.support.v4.util.Pair;


/**
 * This is the Course class which contains the index of the Course as it is in the online Database.
 */
public class Course {
    private String code;

    public Course(String code){this.code = code;}

    /**
     * Getter for code of the course
     * @return code of the course
     */
    public String getCourse(){return code;}

    /**
     * Used to get the course name and Course code of a given course.
     * @return Pair of type String, String which contain Course Code and Course Name respectively.
     */
    public Pair<String,String> getCourseName() {
        switch (code) {
            case "001":
                return new Pair("CS207", "Discrete Structures");
            case "002":
                return new Pair("CS213","Data Structures and Algorithms");
            case "003":
                return new Pair("CS215","Data Analysis and Interpretation");
            case "004":
                return new Pair("CS293","Data Structures and Algorithms Lab");
            case "005":
                return new Pair("CS251","Software Systems Lab");
            case "006":
                return new Pair("EE101","Electrical");
            case "007":
                return new Pair("MA403","Real Analysis");
        }
        return new Pair("000","Invalid Course");
    }
}
