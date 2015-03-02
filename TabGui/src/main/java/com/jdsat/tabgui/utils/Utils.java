package com.jdsat.tabgui.utils;

public class Utils {
    
    public static boolean isNumericPattern(String s){
        String pattern= "^[0-9]*$";
        return s.matches(pattern);  
    }
}
