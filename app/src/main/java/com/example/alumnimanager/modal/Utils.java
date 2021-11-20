package com.example.alumnimanager.modal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static User user;
    public static String PREF_NAME = "ALUMINI_PREF";

    public static void setUser(User u) {
        user = u;
    }

    public static User getUser() {
        return user;
    }


    public static String toTime(String mills) {
        Long currentTime = Long.parseLong(mills);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date(currentTime);
        String time = simpleDateFormat.format(date);
        return time;
    }


    public static String toDate(String mills) {
        long currentDateTime =  Long.parseLong(mills);

        //creating Date from millisecond
        Date currentDate = new Date(currentDateTime);

        return currentDate + "";


    }

}
