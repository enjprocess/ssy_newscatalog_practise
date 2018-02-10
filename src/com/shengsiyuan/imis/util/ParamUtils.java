package com.shengsiyuan.imis.util;

import javax.servlet.ServletRequest;

public class ParamUtils {

    public static long getParameter(ServletRequest request, String name, long defaultValue) {
        String value = request.getParameter(name);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    public static long getParameter(ServletRequest request, String name) {
        return getParameter(request, name, 0);
    }
    
}
