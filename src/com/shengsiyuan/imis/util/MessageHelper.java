package com.shengsiyuan.imis.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageHelper {

    public static String getExceptionInfo(int errorCode) {
        ResourceBundle rb = ResourceBundle.getBundle(
                FileNameString.EXCEPTION_INFO_NAME, Locale.getDefault());
        String exceptionMessage = rb.getString(String.valueOf(errorCode));
        return exceptionMessage;
    }
}
