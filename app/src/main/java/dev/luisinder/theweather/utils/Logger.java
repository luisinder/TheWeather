package dev.luisinder.theweather.utils;

/**
 * Created by Luis on 02/09/2015.
 */

import dev.luisinder.theweather.Constants;

public class Logger {

    public static void i(String msg) {
            android.util.Log.i(Constants.APP_NAME, msg);
    }

    public static void i(String tag, String msg) {
            android.util.Log.i(Constants.APP_NAME, "[" + tag + "] " + msg);
    }

    public static void e(String msg) {
            android.util.Log.e(Constants.APP_NAME, msg);
    }

    public static void e(String tag, String msg) {
            android.util.Log.e(Constants.APP_NAME, "[" + tag + "] " + msg);
    }

    public static void d(String msg) {
            android.util.Log.d(Constants.APP_NAME, msg);
    }

    public static void d(String tag, String msg) {
            android.util.Log.d(Constants.APP_NAME, "[" + tag + "] " + msg);
    }

    public static void v(String msg) {
            android.util.Log.v(Constants.APP_NAME, msg);
    }

    public static void v(String tag, String msg) {
            android.util.Log.v(Constants.APP_NAME, "[" + tag + "] " + msg);
    }

    public static void w(String msg) {
            android.util.Log.w(Constants.APP_NAME, msg);
    }

    public static void w(String tag, String msg) {
            android.util.Log.w(Constants.APP_NAME, "[" + tag + "] " + msg);
    }

    public static void wtf(String msg) {
            android.util.Log.wtf(Constants.APP_NAME, msg);
    }

    public static void wtf(String tag, String msg) {
            android.util.Log.wtf(tag, "[" + tag + "] " + msg);
    }
}
