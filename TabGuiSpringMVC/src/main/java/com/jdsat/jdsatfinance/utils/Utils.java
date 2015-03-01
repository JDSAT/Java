package com.jdsat.jdsatfinance.utils;

public class Utils {
    
    public static boolean isNumericPattern(String s){
        String pattern= "^[0-9]*$";
        return s.matches(pattern);  
    }
}
