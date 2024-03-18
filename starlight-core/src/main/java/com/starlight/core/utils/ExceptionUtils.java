package com.starlight.core.utils;

public class ExceptionUtils  {
    public static String getStackMsg(Throwable e) {

        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            sb.append(stackArray[i].toString() + "\n");
        }
        return sb.toString();
    }
}
