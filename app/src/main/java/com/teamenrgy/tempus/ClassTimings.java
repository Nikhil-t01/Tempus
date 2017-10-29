package com.teamenrgy.tempus;

import java.io.Serializable;

/**
 * Class for storing all lecture timings of a course efficiently
 */
public class ClassTimings implements Serializable {
    String name ,venue;
    String[] days = new String[7];

    /**
     * Constructor for this class
     * @param mon Lecture timings for the course on Mondays
     * @param tue Lecture timings for the course on Tuesdays
     * @param wed Lecture timings for the course on Wednesdays
     * @param thu Lecture timings for the course on Thursdays
     * @param fri Lecture timings for the course on Fridays
     * @param sat Lecture timings for the course on Saturdays
     * @param sun Lecture timings for the course on Sundays
     * @param name Name of the course
     * @param venue Venue where classes take place
     */
    public ClassTimings(String mon, String tue, String wed, String thu, String fri, String sat, String sun, String name ,String venue){
        this.days[0] = mon;
        this.days[1] = tue;
        this.days[2] = wed;
        this.days[3] = thu;
        this.days[4] = fri;
        this.days[5] = sat;
        this.days[6] = sun;
        this.name = name;
        this.venue = venue;
    }

}
