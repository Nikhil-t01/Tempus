package com.teamenrgy.tempus;

import android.support.v4.util.Pair;

/**
 * Created by nikhil-t on 12/10/17.
 */


/**
 * @class This is the Course class which contains the index of the Course as it is in the online Database.
 */
public class Course {
    private String code;

    public Course(String code){this.code = code;}

    public String getCourse(){return code;}

    /**
     * @brief Used to get the course name and Course code of a given course.
     * @return Pair of type String, String which contain Course Codee and Course Name respectively.
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
                return new Pair("CS152","Abstractions and Paradigms in Programming");
            case "005":
                return new Pair("CS251","Software Systems Lab");
            case "006":
                return new Pair("BB101","Biology");
            case "007":
                return new Pair("MA106","Linear Algebra");
        }
        return new Pair("000","Invalid Course");
    }
}
