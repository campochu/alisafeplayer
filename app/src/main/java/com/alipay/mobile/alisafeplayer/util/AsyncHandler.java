package com.alipay.mobile.alisafeplayer.util;

import android.os.Handler;
import android.os.HandlerThread;

/**
 * ckb on 16/4/29.
 */
public final class AsyncHandler {

    private static final HandlerThread sHandlerThread = new HandlerThread("AsyncHandler");
    private static final Handler sHandler;

    static {
        sHandlerThread.start();
        sHandler = new Handler(sHandlerThread.getLooper());
    }

    public static void post(Runnable r) {
        sHandler.post(r);
    }

    public static void postDelayed(Runnable r, int delayMillis) {
        sHandler.postDelayed(r, delayMillis);
    }

    public static void remove(Runnable r) {
        sHandler.removeCallbacks(r);
    }

    public static void clear() {
        sHandler.removeCallbacksAndMessages(null);
    }

    private AsyncHandler() {
    }

}