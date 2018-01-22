package com.alipay.mobile.alisafeplayer.util;

import android.os.Build;

/**
 * Created by ckb on 18/1/9.
 */

public final class ApiHelper {

    public static final boolean preM() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
    }

}
