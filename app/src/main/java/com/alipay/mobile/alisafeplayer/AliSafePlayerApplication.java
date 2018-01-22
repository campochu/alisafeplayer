package com.alipay.mobile.alisafeplayer;

import android.app.Application;

import com.alivc.player.VcPlayerLog;

/**
 * Created by ckb on 18/1/21.
 */

public class AliSafePlayerApplication extends Application {

    static {
        VcPlayerLog.enableLog();
    }

}
