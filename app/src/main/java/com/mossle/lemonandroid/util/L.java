package com.mossle.lemonandroid.util;

import android.util.Log;

/**
 * @author qule
 * @CreateDate 2015-12-15
 * 日志工具类
 */
public class L {
    private final static boolean debug = true;

    private static String TAG = "TAG";

    public static void v(String tag, String msg) {
        if (debug) {
            Log.v(tag, msg);
        }
    }

    public static void v(String msg) {
        if (debug) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (debug) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg) {
        if (debug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (debug) {
            Log.e(tag, msg);
        }
    }

    public static void e(String msg) {
        if (debug) {
            Log.e(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (debug) {
            Log.i(tag, msg);
        }
    }

    public static void i(String msg) {
        if (debug) {
            Log.i(TAG, msg);
        }
    }


    /**
     * 如果日志界面有显示，就会打印在界面
     * @param c
     */
    public static void showLog(CharSequence c) {
        L.v(c.toString());
    }
}
