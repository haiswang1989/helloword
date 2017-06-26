package com.hiworld.joda.time;


import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;

public class SimpleDate {

    public static void main(String[] args) {
        
        DateTime datetime = new DateTime(2017, 6, 26, 10, 28, 0);
        
        Property prop = datetime.dayOfMonth();
        System.out.println("Day of month : " + prop.get()); 
        
        DateTime newDateTime = datetime.plusDays(2);
        System.out.println(newDateTime.toDate());
    }
}
