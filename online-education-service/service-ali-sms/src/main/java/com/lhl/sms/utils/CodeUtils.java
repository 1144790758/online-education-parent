package com.lhl.sms.utils;

/**
 * @athor:lhl
 */
public class CodeUtils {

    public static String randomCode(int length){
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < length; i++) {
            int v = (int) (Math.random() * 10);
            sb.append(v);
        }
        return sb.toString();
    }
}
