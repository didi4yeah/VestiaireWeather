package com.didi.vestiaireweather.utils;

/**
 * Created by didi on 11/12/2016.
 */

public class StringUtils {

    /**
     * Check if string received nor null nor empty
     * @param s origin
     * @return result
     */
    public static boolean isNotNullOrEmpty(String s){
        return s != null && !s.isEmpty();
    }

    /**
     * Set upper case to first letter of a specific string
     * @param s origin
     * @return result
     */
    public static String upperCaseFirstLetter(String s){
        if(StringUtils.isNotNullOrEmpty(s)){
            return String.valueOf(Character.toUpperCase(s.charAt(0))) + s.substring(1);
        } else return "";
    }
}
